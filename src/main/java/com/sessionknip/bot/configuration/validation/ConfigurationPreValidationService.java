package com.sessionknip.bot.configuration.validation;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.util.List;

@Component(role = ConfigurationPreValidationService.class, hint = "preValidation")
public class ConfigurationPreValidationService extends AbstractConfigurationValidationService {

    @Requirement
    private List<ConfigurationValidator> validators;

    @Override
    protected List<ConfigurationValidator> getValidators() {
        return validators;
    }
}
