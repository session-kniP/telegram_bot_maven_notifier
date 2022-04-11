package com.sessionknip.bot.configuration.key;

import org.codehaus.plexus.component.annotations.Component;

@Component(role = AbstractConfigurationKey.class, hint = "enabled")
public class EnabledKey extends AbstractConfigurationKey {
    @Override
    public String getString() {
        return "bot.enabled";
    }
}
