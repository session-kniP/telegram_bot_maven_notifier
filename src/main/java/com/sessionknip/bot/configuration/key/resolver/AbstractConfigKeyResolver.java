package com.sessionknip.bot.configuration.key.resolver;

import com.sessionknip.bot.configuration.BotConfiguration;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Resolves configuration value from configuration map and puts resolved value to configuration builder
 */
public abstract class AbstractConfigKeyResolver<T> implements ConfigKeyResolver {

    @Nonnull
    protected abstract T parseStringValue(String stringValue);

    @Override
    public final void resolve(BotConfiguration.ConfigurationBuilder builder, Map<String, String> configurationMap) {
        String stringValue = getStringValue(configurationMap);
        T value = parseStringValue(stringValue);
        fillBuilder(builder, value);
    }

    protected abstract void fillBuilder(BotConfiguration.ConfigurationBuilder builder, T value);

    protected final String getStringValue(@Nonnull Map<String, String> configurationMap) {
        return configurationMap.get(getConfigurationKey().getString());
    }

}
