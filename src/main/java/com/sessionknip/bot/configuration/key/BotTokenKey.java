package com.sessionknip.bot.configuration.key;

import org.codehaus.plexus.component.annotations.Component;

@Component(role = AbstractConfigurationKey.class, hint = "token")
public class BotTokenKey extends AbstractConfigurationKey {
    @Override
    public String getString() {
        return "bot.token";
    }
}
