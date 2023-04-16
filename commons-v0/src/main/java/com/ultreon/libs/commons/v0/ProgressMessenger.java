package com.ultreon.libs.commons.v0;

/**
 * @author Qboi
 */
public class ProgressMessenger extends Progress {
    private final MessengerImpl messenger;

    public ProgressMessenger(MessengerImpl messenger, int progress, int max) {
        super(progress, max);
        this.messenger = messenger;
    }

    public ProgressMessenger(MessengerImpl messenger, int max) {
        super(max);
        this.messenger = messenger;
    }

    public void send(String text) {
        messenger.send(text);
    }

    public void sendNext(String text) {
        messenger.send(text);
        increment();
    }
}
