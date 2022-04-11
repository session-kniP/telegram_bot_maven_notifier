package com.sessionknip.bot.configuration.validation;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

@Component(role = ConfigurationPostValidator.class, hint = "chatId")
public class ChatIdValidator implements ConfigurationPostValidator {

    @Requirement(hint = "chatId")
    private AbstractConfigurationKey key;

    @Override
    public ValidationResult validate(BotConfiguration configuration) {
        String chatId = configuration.getChatId();
        if (chatId.isEmpty()) {
            return new ValidationResult("Chat ID is not configured or contains errors", key);
        }

        return ValidationResult.ok();
    }

    @Override
    public AbstractConfigurationKey getConfigurationKey() {
        return key;
    }
}
