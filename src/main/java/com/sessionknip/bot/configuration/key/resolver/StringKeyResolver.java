package com.sessionknip.bot.configuration.key.resolver;

import javax.annotation.Nonnull;

public abstract class StringKeyResolver extends AbstractConfigKeyResolver<String> {
    @Override
    @Nonnull
    protected final String parseStringValue(String stringValue) {
        return stringValue == null ? "" : stringValue;
    }
}
