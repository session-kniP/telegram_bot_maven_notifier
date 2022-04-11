package com.sessionknip.bot.configuration.validation;

import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationResult {

    private String message;
    private AbstractConfigurationKey configurationKey;

    public boolean hasErrors() {
        return message != null || configurationKey != null;
    }

    public static ValidationResult ok() {
        return new ValidationResult(null, null);
    }

}
