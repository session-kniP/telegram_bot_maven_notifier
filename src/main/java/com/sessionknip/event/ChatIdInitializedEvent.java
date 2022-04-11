package com.sessionknip.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatIdInitializedEvent {
    private final String chatId;
}
