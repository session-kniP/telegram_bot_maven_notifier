package com.sessionknip.bot.configuration.key.resolver;

import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;

import java.util.Map;

public interface ConfigKeyResolver {

    AbstractConfigurationKey getConfigurationKey();
    void resolve(BotConfiguration.ConfigurationBuilder builder, Map<String, String> configurationMap);

}
