package mappingobjects.automappingobjects.config;

import mappingobjects.automappingobjects.utils.ValidatorUtil;
import mappingobjects.automappingobjects.utils.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl(validator());
    }
}
