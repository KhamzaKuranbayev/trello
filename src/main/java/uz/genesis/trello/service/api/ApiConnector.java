package uz.genesis.trello.service.api;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.reflect.misc.FieldUtil;

import javax.net.ssl.SSLContext;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApiConnector {
    private static final Logger log = LogManager.getLogger(ApiConnector.class);

    private static Environment environment;

    static {
//        environment = ApplicationContextProvider.applicationContext.getBean(Environment.class);
    }

    public static Builder newBuilder(Class<?> responseType) {
        return new ApiConnector().new Builder(responseType);
    }

    public SSLContext sslContext() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        return org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(acceptingTrustStrategy)
                .build();
    }

    public SSLConnectionSocketFactory socketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new SSLConnectionSocketFactory(sslContext(), NoopHostnameVerifier.INSTANCE);
    }

    public Registry<ConnectionSocketFactory> registry() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", socketFactory())
                .build();
    }

    public PoolingHttpClientConnectionManager connectionManager() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new PoolingHttpClientConnectionManager(registry()) {{
            setMaxTotal(300);
            setDefaultMaxPerRoute(300);
        }};
    }

    public SocketConfig socketConfig() {
        return SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).build();
    }

    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setCircularRedirectsAllowed(true)
                .setRedirectsEnabled(true)
                .setMaxRedirects(10000)
                .setRelativeRedirectsAllowed(true)
                .setContentCompressionEnabled(true)
                .build();
    }

    public class Builder<T> {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        private HttpComponentsClientHttpRequestFactory req = new HttpComponentsClientHttpRequestFactory() {{
            setConnectTimeout(40000);
            setConnectionRequestTimeout(40000);
            setReadTimeout(40000);
//            setHttpClient(Boolean.parseBoolean(environment.getProperty("proxy.enabled"))
//                    ? HttpClientBuilder.create().setProxy(new HttpHost(environment.getProperty("proxy.host"), Integer.parseInt(environment.getProperty("proxy.port")))).build()
//                    : getHttpClient());

            try {
                setHttpClient(HttpClients.custom()
                        .setRedirectStrategy(new LaxRedirectStrategy())
                        .setSSLSocketFactory(socketFactory())
                        .setConnectionManager(connectionManager())
                        .setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                        .setDefaultRequestConfig(requestConfig())
                        .setDefaultSocketConfig(socketConfig())
                        .build());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            mergeRequestConfig(requestConfig());
        }};
        RestTemplate restTemplate = new RestTemplate(req);
        private String url;
        private Map params = new ConcurrentHashMap();
        private ResponseEntity<T> result;
        private Class<T> responseType;

        public Builder(Class<T> responseType) {
            this.responseType = responseType;
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter() {
                @Override
                public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
                    ArrayList<MediaType> temp = new ArrayList<MediaType>(supportedMediaTypes);
                    temp.add(new MediaType("text", "html", DEFAULT_CHARSET));
                    temp.add(new MediaType("application", "x-www-form-urlencoded", DEFAULT_CHARSET));
                    super.setSupportedMediaTypes(temp);
                }
            });
//            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//            mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM,MediaType.ALL));
//            restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);


            /*List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            //Add the Jackson OtpMessage converter
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            // Note: here we are making this converter to process any kind of response,
            // not only application/*json, which is the default behaviour
            converter.setSupportedMediaTypes(Arrays.asList(new MediaType[]{MediaType.ALL}));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);*/
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder clearParams() {
            params.clear();
            return this;
        }

        public Builder addParam(String key, Object value) {
            this.params.put(key, value);
            return this;
        }

        public Builder addHeader(String key, String value) {
            headers.add(key, value);
            return this;
        }

        public Builder post() {
            return exchange(HttpMethod.POST);
        }

        public Builder post(Object data) {
//            result = restTemplate.postForEntity(url, data, responseType);
            HttpEntity<Map> request = getMapHttpEntity(data);
            result = restTemplate.exchange(url, HttpMethod.POST, request, responseType);
            return this;
        }

        public Builder put(Object data) {
            HttpEntity<Map> request = getMapHttpEntity(data);
            result = restTemplate.exchange(url, HttpMethod.PUT, request, responseType);
            return this;
        }

        private HttpEntity<Map> getMapHttpEntity(Object data) {
            Map<String, Object> params = new HashMap<>();
            if (data != null)
                for (Field field : FieldUtil.getDeclaredFields(data.getClass())) {
                    field.setAccessible(true);
                    try {
                        params.put(field.getName(), field.get(data));
                    } catch (IllegalAccessException e) {
                        //e.printStackTrace();
                        log.error(e.getStackTrace(), e.getCause());
                    }
                }
            return new HttpEntity<Map>(params, headers);
        }

        public Builder exchange(HttpMethod httpMethod) {
            HttpEntity<Map> request = new HttpEntity<Map>(params, headers);
            result = restTemplate.exchange(url, httpMethod, request, responseType);
            return this;
        }

        public Builder get() {
            return exchange(HttpMethod.GET);
        }

        public T build() {
            return result.getBody();
        }
    }
}