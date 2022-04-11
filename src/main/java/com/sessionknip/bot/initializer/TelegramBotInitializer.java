package com.sessionknip.bot.initializer;

import com.sessionknip.bot.BotInitializerException;
import com.sessionknip.bot.BuildNotificationBot;
import com.sessionknip.bot.configuration.BotConfiguration;
import com.sessionknip.bot.configuration.BotConfigurationService;
import com.sessionknip.bot.configuration.BotConfigurationServiceException;
import com.sessionknip.bot.configuration.validation.ConfigurationPostValidationService;
import com.sessionknip.bot.configuration.validation.ConfigurationPreValidationService;
import com.sessionknip.bot.configuration.validation.ConfigurationValidationService;
import com.sessionknip.bot.configuration.validation.ValidationResult;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component(role = TelegramBotInitializer.class)
public class TelegramBotInitializer {

    @Requirement
    private Logger logger;

    @Requirement
    private ConfigurationPreValidationService validationService;

    @Requirement
    private ConfigurationPostValidationService postValidationService;

    @Requirement
    private BotConfigurationService configurationService;

    @Requirement
    private PreInitializationService preInitializationService;

    @Requirement
    private PostInitializationService postInitializationService;

    public BuildNotificationBot initialize() throws BotInitializerException {
        BuildNotificationBot bot = null;

        try {
            if (!performInitializationForValidationService(validationService, preInitializationService)) {
                return BuildNotificationBot.notInitializedInstance();
            }

            BotConfiguration configuration = configurationService.getConfiguration();
            bot = BuildNotificationBot.fromConfig(configuration);

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);

            if (!performInitializationForValidationService(postValidationService, postInitializationService)) {
                return BuildNotificationBot.notInitializedInstance();
            }

            return BuildNotificationBot.fromConfig(configurationService.getConfiguration());
        } catch (BotConfigurationServiceException | TelegramApiException e) {
            throw new BotInitializerException("Unable to initialize bot", e);
        } finally {
            if (bot != null) {
                bot.onClosing();
            }
        }
    }

    private boolean performInitializationForValidationService(ConfigurationValidationService validationService, InitializationService initializationService) throws BotInitializerException {
        try {
            BotConfiguration configuration = configurationService.getConfiguration();
            ValidationResult result = validationService.validate(configuration);

            while (result.hasErrors()) {
                logger.warn("The following key is not specified: " + result.getConfigurationKey().getString());
                logger.warn("Config message: " + result.getMessage());
                boolean initialize = initializationService.initialize(result);

                if (!initialize) {
                    return false;
                }
                configuration = configurationService.getConfiguration();
                result = validationService.validate(configuration);
            }

            return true;
        } catch (InitializationServiceException | BotConfigurationServiceException e) {
            throw new BotInitializerException("Unable to perform initialization for validations", e);
        }
    }

}
