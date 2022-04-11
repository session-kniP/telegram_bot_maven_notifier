package com.sessionknip.bot.configuration.key;

/**
 * Represents configuration key as object
 */
public abstract class AbstractConfigurationKey {
    public abstract String getString();

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractConfigurationKey)) {
            return false;
        }

        return ((AbstractConfigurationKey) obj).getString().equals(getString());
    }

    @Override
    public int hashCode() {
        return getString().hashCode();
    }
}
