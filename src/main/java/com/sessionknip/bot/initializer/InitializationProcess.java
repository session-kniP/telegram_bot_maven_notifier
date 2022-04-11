package com.sessionknip.bot.initializer;

import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import com.sessionknip.bot.configuration.validation.ValidationResult;

public interface InitializationProcess {

    void initialize() throws InitializationProcessException;

    AbstractConfigurationKey getConfigurationKey();

}
