package uz.genesis.trello.service.organization;

import freemarker.template.TemplateException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.organization.OrganizationCriteria;
import uz.genesis.trello.domain.organization.Organization;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.organization.OrganizationCreateOtpDto;
import uz.genesis.trello.dto.organization.OrganizationOtpConfirmDto;
import uz.genesis.trello.dto.organization.OrganizationUserDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.GenericRuntimeException;
import uz.genesis.trello.mapper.organization.OrganizationUserMapper;
import uz.genesis.trello.repository.organization.IOrganizationRepository;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.service.message.IOtpHelperService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.UserSession;
import uz.genesis.trello.utils.validators.organization.OrganizationValidator;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Types;
import java.util.List;

@Service

public class OrganizationService extends AbstractService<OrganizationUserDto, OrganizationCriteria, IOrganizationRepository> implements IOrganizationService {
    private final OrganizationUserMapper mapper;
    private final IOtpHelperService otpHelperService;
    private final OrganizationValidator validator;
    @Autowired
    protected UserSession userSession;

    public OrganizationService(IOrganizationRepository repository, BaseUtils utils, IErrorRepository errorRepository, OrganizationUserMapper mapper, IOtpHelperService otpHelperService, OrganizationValidator validator) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.otpHelperService = otpHelperService;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> checkByEmail(OrganizationCreateOtpDto dto, HttpServletResponse response) {
        Organization organization = repository.find(OrganizationCriteria.childBuilder().email(dto.getEmail()).build());
        if (utils.isEmpty(organization)) {
            String emailMessage = otpHelperService.generateAuthOtp(dto.getEmail());
            try {
                otpHelperService.sendEmailAuth(dto.getEmail(), emailMessage);
            } catch (MessagingException | IOException | TemplateException e) {
                e.printStackTrace();
            }
        } else{
            String url = "https://kun.uz/";
            response.setHeader("Location", url);
            response.setStatus(302);
            return null;
        }

        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> authOtpConfirm(OrganizationOtpConfirmDto dto) {
        boolean isConfirmed = otpHelperService.confirmOtp(dto.getEmail(), dto.getOtpCode(), "checkauthotpcode");

        return new ResponseEntity<>(new DataDto<>(isConfirmed), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<OrganizationUserDto>> updateOrganizationUser(OrganizationUserDto dto) {
        validator.validateOnAuth(dto);
        Organization organization = repository.find(OrganizationCriteria.childBuilder().email(dto.getOrganizationEmail()).build());

        if (!utils.isEmpty(organization)) {
            dto.setId(organization.getId());
            if (repository.call(dto, "updateorganizationotp", Types.BOOLEAN)) {
                return new ResponseEntity<>(new DataDto<>(dto), HttpStatus.OK);
            } else {
                throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Organization.class)));
            }
        } else
            throw new GenericRuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND, utils.toErrorParams(Organization.class)));

    }

    @Override
    public ResponseEntity<DataDto<List<OrganizationUserDto>>> getAll(OrganizationCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
