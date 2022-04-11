package com.sessionknip.bot.initializer;

import com.sessionknip.bot.configuration.BotConfigurationService;
import com.sessionknip.bot.configuration.BotConfigurationServiceException;
import com.sessionknip.bot.configuration.key.AbstractConfigurationKey;
import com.sessionknip.bot.configuration.key.ChatIdKey;
import com.sessionknip.event.ChatIdInitializedEvent;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component(role = PostInitializationProcess.class, hint = "chatId")
public class ChatIdInitializationProcess implements PostInitializationProcess {

    @Requirement
    private Logger logger;

    @Requirement
    private BotConfigurationService configurationService;

    @Requirement(hint = "chatId")
    private AbstractConfigurationKey key;

    @PostConstruct
    private void init() {
        EventBus.getDefault().register(this);
    }

    @PreDestroy
    private void destroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public synchronized void initialize() throws InitializationProcessException {
        try {
            String botName = configurationService.getConfiguration().getChatId();

            logger.warn("Chat ID is not initialized.");
            logger.warn("Please, find telegram bot with name " + botName + " and press Start button or send any message to it.");
            this.wait();
        } catch (BotConfigurationServiceException | InterruptedException e) {
            throw new InitializationProcessException("Unable to initialize bot name", e);
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public synchronized void onMessage(ChatIdInitializedEvent event) throws BotConfigurationServiceException {
        configurationService.addConfiguration(new ChatIdKey().getString(), event.getChatId());
        this.notify();
    }

    @Override
    public AbstractConfigurationKey getConfigurationKey() {
        return key;
    }
}
