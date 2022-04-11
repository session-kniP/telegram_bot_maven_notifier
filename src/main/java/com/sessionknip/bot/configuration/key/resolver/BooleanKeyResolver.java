package com.sessionknip.bot.configuration.key.resolver;

import javax.annotation.Nonnull;

public abstract class BooleanKeyResolver extends AbstractConfigKeyResolver<Boolean> {

    @Override
    @Nonnull
    protected Boolean parseStringValue(String stringValue) {
        return Boolean.parseBoolean(stringValue);
    }
}
