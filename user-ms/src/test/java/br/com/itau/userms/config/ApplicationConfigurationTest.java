package br.com.itau.userms.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationConfigurationTest {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @Test
    public void messageSourceMustNotBeNull() {
        assertNotNull(messageSource);
    }

    @Test
    public void validatorBeanMustNotBeNull() {
        assertNotNull(localValidatorFactoryBean);
    }

}
