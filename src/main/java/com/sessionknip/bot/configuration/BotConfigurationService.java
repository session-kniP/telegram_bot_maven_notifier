package com.sessionknip.bot.configuration;

import fr.jcgay.maven.notifier.ConfigurationParser;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * Wrapper over the configuration: allows to add and read values from config
 */
@Component(role = BotConfigurationService.class)
public class BotConfigurationService {

    @Requirement
    private BotConfigurationParser configurationParser;

    @Requirement
    private ConfigurationParser parser;

    public void addConfiguration(String key, String value) throws BotConfigurationServiceException {
        if (configurationHasKey(key)) {
            throw new BotConfigurationServiceException("Unable to add configuration. Key " + key + " already exists");
        }
        writeConfiguration(key, value);
    }

    private void writeConfiguration(String key, String value) throws BotConfigurationServiceException {
        try {
            URL url = parser.userConfiguration();

            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(url.toURI()), true))) {
                writer.write("\n");
                writer.write(key + "=" + value);
            }
        } catch (IOException | URISyntaxException e) {
            throw new BotConfigurationServiceException("Unable to write configuration", e);
        }

    }

    public BotConfiguration getConfiguration() throws BotConfigurationServiceException {
        try {
            return configurationParser.parse(parser.userConfiguration());
        } catch (BotConfigurationParserException e) {
            throw new BotConfigurationServiceException("Unable to get configuration", e);
        }
    }

    private boolean configurationHasKey(String key) throws BotConfigurationServiceException {
        try {
            Map<String, String> configurationMap = configurationParser.parseConfigurationMap(parser.userConfiguration());
            return configurationMap.containsKey(key);
        } catch (BotConfigurationParserException e) {
            throw new BotConfigurationServiceException("Unable to check configuration on configuration key existence", e);
        }
    }

}
