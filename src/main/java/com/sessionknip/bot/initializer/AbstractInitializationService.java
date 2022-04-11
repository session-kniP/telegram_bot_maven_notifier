package com.sessionknip.bot.initializer;

import com.sessionknip.bot.configuration.validation.ValidationResult;

import java.util.List;
import java.util.Optional;

public abstract class AbstractInitializationService implements InitializationService {

    abstract List<? extends InitializationProcess> getInitializationProcesses();

    @Override
    public boolean initialize(ValidationResult result) throws InitializationServiceException {
        try {

            List<? extends InitializationProcess> initializationProcesses = getInitializationProcesses();

            Optional<? extends InitializationProcess> processOptional =
                    initializationProcesses
                            .stream()
                            .filter(p -> p.getConfigurationKey().equals(result.getConfigurationKey()))
                            .findFirst();

            if (!processOptional.isPresent()) {
                return false;
            }

            processOptional.get().initialize();
            return true;
        } catch (InitializationProcessException e) {
            throw new InitializationServiceException("Unable to execute initialization", e);
        }
    }

}
