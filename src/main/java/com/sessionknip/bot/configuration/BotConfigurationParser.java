package com.sessionknip.bot.configuration;

import com.sessionknip.bot.configuration.key.resolver.ConfigKeyResolver;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Parses the configuration map from specific URL
 */
@Component(role = BotConfigurationParser.class, hint = "SK configuration parser")
public class BotConfigurationParser {

    @Requirement
    private List<ConfigKeyResolver> keyResolvers;

    public BotConfiguration parse(URL configURL) throws BotConfigurationParserException {
        return createConfigurationFromMap(parseConfigurationMap(configURL));
    }

    public Map<String, String> parseConfigurationMap(URL configURL) throws BotConfigurationParserException {
        try (InputStream stream = configURL.openStream()) {
            Map<String, String> configurationMap = new HashMap<>();
            Scanner scanner = new Scanner(stream);

            if (!scanner.hasNext()) {
                return configurationMap;
            }

            while (scanner.hasNext()) {
                String confLine = scanner.next();
                String[] keyValueArray = confLine.trim().replace(" ", "").split("=");

                if (keyValueArray.length != 2) {
                    throw new BotConfigurationParserException("Wrong configuration key-value format in row \"" + confLine + "\"");
                }

                configurationMap.put(keyValueArray[0], keyValueArray[1]);
            }

            return configurationMap;

        } catch (IOException e) {
            throw new BotConfigurationParserException("Unable to parse configuration url", e);
        }
    }

    private BotConfiguration createConfigurationFromMap(Map<String, String> configurationMap) {
        BotConfiguration.ConfigurationBuilder builder = BotConfiguration.builder();
        for (ConfigKeyResolver resolver : keyResolvers) {
            resolver.resolve(builder, configurationMap);
        }
        return builder.build();
    }
}
