package com.sessionknip.bot.configuration.validation;

import com.sessionknip.bot.configuration.BotConfiguration;

/**
 * Validates the configuration
 */
public interface ConfigurationValidationService {
    ValidationResult validate(BotConfiguration configuration);
}
