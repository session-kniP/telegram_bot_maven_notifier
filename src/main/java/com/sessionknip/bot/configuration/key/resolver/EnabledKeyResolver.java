package com.sessionknip.bot.configuration.key.resolver;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import javax.annotation.Nonnull;

@Component(role = ConfigKeyResolver.class, hint = "enabled")
public class EnabledKeyResolver extends BooleanKeyResolver {

    @Requirement(hint = "enabled")
    private AbstractConfigurationKey key;

    @Override
    public AbstractConfigurationKey getConfigurationKey() {
        return key;
    }

    @Override
    protected void fillBuilder(BotConfiguration.ConfigurationBuilder builder, Boolean value) {
        builder.enabled(value);
    }

    @Override
    @Nonnull
    protected Boolean parseStringValue(String stringValue) {
        return stringValue == null || stringValue.isEmpty() || super.parseStringValue(stringValue);
    }
}
