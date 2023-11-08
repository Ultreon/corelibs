package com.ultreon.libs.events.v0;

public abstract class Subscriber<T extends AbstractEvent> {
    public abstract void handle(T e);

    public abstract EventPriority getPriority();

    public abstract SubscribeEvent getAnnotation();

    public abstract Class<? extends T> getType();
}
