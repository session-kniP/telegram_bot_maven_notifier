package com.sessionknip.bot.configuration.validation;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.util.List;

@Component(role = ConfigurationPostValidationService.class, hint = "postValidation")
public class ConfigurationPostValidationService extends AbstractConfigurationValidationService {

    @Requirement
    private List<ConfigurationPostValidator> validators;

    @Override
    protected List<ConfigurationPostValidator> getValidators() {
        return validators;
    }
}
