package com.ultreon.libs.registries.v0.event;

import com.ultreon.libs.events.v1.Event;
import com.ultreon.libs.registries.v0.Registry;

public class RegistryEvents {

    public static final Event<RegistryDump> REGISTRY_DUMP = Event.simple();
    public static final Event<AutoRegister> AUTO_REGISTER = Event.simple();

    @FunctionalInterface
    public interface RegistryDump {
        void onRegistryDump();
    }

    @FunctionalInterface
    public interface AutoRegister {
        void onAutoRegister(Registry<?> registry);
    }
}
