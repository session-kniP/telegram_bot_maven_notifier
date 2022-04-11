package com.sessionknip.bot.configuration.validation;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;

/**
 * Validates specific configuration value
 */
public interface ConfigurationValidator {

    ValidationResult validate(BotConfiguration configuration);

    AbstractConfigurationKey getConfigurationKey();

}
