package com.sessionknip.bot.configuration.key.resolver;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

@Component(role = ConfigKeyResolver.class, hint = "dawda")
public class BotTokenKeyResolver extends StringKeyResolver {

    @Requirement(hint = "token")
    private AbstractConfigurationKey key;

    @Override
    public AbstractConfigurationKey getConfigurationKey() {
        return key;
    }

    @Override
    protected void fillBuilder(BotConfiguration.ConfigurationBuilder builder, String value) {
        builder.botToken(value);
    }
}
