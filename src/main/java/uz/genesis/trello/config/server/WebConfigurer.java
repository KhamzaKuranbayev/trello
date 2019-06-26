package uz.genesis.trello.config.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 11/8/18.
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/v1/ispring/**")
                .addResourceLocations(
                        "file:///nfs/academy/uploads/")
                .setCachePeriod(0);
        registry.addResourceHandler("/api/v1/pdf/**")
                .addResourceLocations("file:///nfs/academy/uploads/")
                .setCachePeriod(0);
    }
}
