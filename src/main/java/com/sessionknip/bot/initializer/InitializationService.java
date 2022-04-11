package com.sessionknip.bot.initializer;

import com.sessionknip.bot.configuration.validation.ValidationResult;

public interface InitializationService {

    /**
     * This method is used for running runtime initialization processes
     * @param result validation result for which initialization process should run
     * @return true if initialization process found for validation result, false otherwise
     */
    boolean initialize(ValidationResult result) throws InitializationServiceException;
}
