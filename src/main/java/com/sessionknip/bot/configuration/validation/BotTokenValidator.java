package com.sessionknip.bot.configuration.validation;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

@Component(role = ConfigurationValidator.class, hint = "token")
public class BotTokenValidator implements ConfigurationValidator {

    @Requirement(hint = "token")
    private AbstractConfigurationKey key;

    @Override
    public ValidationResult validate(BotConfiguration configuration) {
        String botToken = configuration.getBotToken();
        if (botToken == null || botToken.isEmpty()) {
            return new ValidationResult("Bot token is not configured or contains errors", key);
        }

        return ValidationResult.ok();
    }

    @Override
    public AbstractConfigurationKey getConfigurationKey() {
        return key;
    }
}
