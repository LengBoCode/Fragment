package com.boleng.fragment.event;

/**
 * Created by boleng on 8/29/17.
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
