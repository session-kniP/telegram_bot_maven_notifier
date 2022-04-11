package com.sessionknip.bot.configuration.validation;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

@Component(role = ConfigurationValidator.class, hint = "name")
public class BotNameValidator implements ConfigurationValidator {

    @Requirement(hint = "name")
    private AbstractConfigurationKey key;

    @Override
    public ValidationResult validate(BotConfiguration configuration) {
        String botName = configuration.getBotName();
        if (botName == null || botName.isEmpty()) {
            return new ValidationResult("Bot name is not configured or contains errors. " +
                    "Please, add your notification bot name to configuration file: " + key.getString() + "={bot name}", key);
        }

        return ValidationResult.ok();
    }

    @Override
    public AbstractConfigurationKey getConfigurationKey() {
        return key;
    }
}
