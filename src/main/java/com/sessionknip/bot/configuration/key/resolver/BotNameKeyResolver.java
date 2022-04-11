package com.sessionknip.bot.configuration.key.resolver;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

@Component(role = ConfigKeyResolver.class, hint = "name")
public class BotNameKeyResolver extends StringKeyResolver {

    @Requirement(hint = "name")
    private AbstractConfigurationKey key;

    @Override
    protected void fillBuilder(BotConfiguration.ConfigurationBuilder builder, String value) {
        builder.botName(value);
    }

    @Override
    public AbstractConfigurationKey getConfigurationKey() {
        return key;
    }
}
