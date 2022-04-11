package com.sessionknip.bot.configuration;

import lombok.Builder;
import lombok.Getter;

@Builder(builderClassName = "ConfigurationBuilder")
@Getter
public class BotConfiguration {

    @Builder.Default
    private String botName ="";

    @Builder.Default
    private String botToken = "";

    @Builder.Default
    private String chatId = "";

    @Builder.Default
    private boolean enabled = true;

}
