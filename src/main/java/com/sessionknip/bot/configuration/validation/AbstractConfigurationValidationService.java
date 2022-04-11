package com.sessionknip.bot.configuration.validation;

import com.sessionknip.bot.configuration.BotConfiguration;

import java.util.List;

public abstract class AbstractConfigurationValidationService implements ConfigurationValidationService {

    @Override
    public ValidationResult validate(BotConfiguration configuration) {
        List<? extends ConfigurationValidator> validators = getValidators();

        for (ConfigurationValidator validator : validators) {
            ValidationResult validationResult = validator.validate(configuration);
            if (validationResult.hasErrors()) {
                return validationResult;
            }
        }
        return ValidationResult.ok();
    }

    protected abstract List<? extends ConfigurationValidator> getValidators();

}
