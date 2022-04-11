package com.sessionknip.bot;

import com.sessionknip.event.ChatIdInitializedEvent;
import org.greenrobot.eventbus.EventBus;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class BotUpdateHandler {

    public void handleUpdates(List<Update> updates) {
        // todo right now it supports only chat ID initialization, so it isn't necessary to
        //  move this to separate logic
        for (Update update : updates) {
            String chatId = String.valueOf(update.getMessage().getChatId());
            EventBus.getDefault().post(new ChatIdInitializedEvent(chatId));
        }
    }

}
