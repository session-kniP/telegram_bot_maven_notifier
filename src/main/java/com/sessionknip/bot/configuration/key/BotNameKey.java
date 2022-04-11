package com.sessionknip.bot.configuration.key;

import org.codehaus.plexus.component.annotations.Component;

@Component(role = AbstractConfigurationKey.class, hint = "name")
public class BotNameKey extends AbstractConfigurationKey {
    @Override
    public String getString() {
        return "bot.name";
    }
}
