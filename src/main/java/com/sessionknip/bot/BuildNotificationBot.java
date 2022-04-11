package com.sessionknip.bot;

import com.sessionknip.bot.configuration.BotConfiguration;
import lombok.Builder;
import lombok.Getter;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Builder
@Getter
public class BuildNotificationBot extends TelegramLongPollingCommandBot {

    private BotUpdateHandler updateHandler;

    @Builder.Default
    private String username = "";

    @Builder.Default
    private String token = "";

    @Builder.Default
    private String chatId = "";

    private boolean enabled;

    private boolean initialized;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    public boolean isInitialized() {
        return true;
    }

    public static BuildNotificationBot notInitializedInstance() {
        return new BuildNotificationBot.BuildNotificationBotBuilder().initialized(false).build();
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
        updateHandler.handleUpdates(updates);
    }

    public static BuildNotificationBot fromConfig(BotConfiguration configuration) {
        return new BuildNotificationBotBuilder()
                .username(configuration.getBotName())
                .token(configuration.getBotToken())
                .chatId(configuration.getChatId())
                .enabled(configuration.isEnabled())
                .updateHandler(new BotUpdateHandler())
                .initialized(true)
                .build();
    }

}
