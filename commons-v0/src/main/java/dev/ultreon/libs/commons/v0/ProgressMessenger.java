package dev.ultreon.libs.commons.v0;

/**
 * @author Qboi
 */
public class ProgressMessenger extends Progress {
    private final Messenger messenger;

    public ProgressMessenger(Messenger messenger, int max) {
        super(max);
        this.messenger = messenger;
    }

    public ProgressMessenger(Messenger messenger, int progress, int max) {
        super(progress, max);
        this.messenger = messenger;
    }

    public void send(String text) {
        this.messenger.send(text);
    }

    public void sendNext(String text) {
        this.messenger.send(text);
        this.increment();
    }
}
