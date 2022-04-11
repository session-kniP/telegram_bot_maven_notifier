package com.sessionknip.bot.configuration.key;

import org.codehaus.plexus.component.annotations.Component;

@Component(role = AbstractConfigurationKey.class, hint = "chatId")
public class ChatIdKey extends AbstractConfigurationKey {
    @Override
    public String getString() {
        return "bot.chat.id";
    }
}
