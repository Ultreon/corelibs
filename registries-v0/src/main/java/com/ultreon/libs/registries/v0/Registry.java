package com.ultreon.libs.registries.v0;

import com.ultreon.libs.collections.v0.maps.OrderedHashMap;
import com.ultreon.libs.commons.v0.Identifier;
import com.ultreon.libs.commons.v0.Logger;
import com.ultreon.libs.registries.v0.event.RegistryEvents;
import com.ultreon.libs.registries.v0.exception.RegistryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public class Registry<T> {
    public static Logger dumpLogger = (level, msg, t) -> {};
    private static final OrderedHashMap<Class<?>, Registry<?>> REGISTRIES = new OrderedHashMap<>();
    private static boolean frozen;
    private final OrderedHashMap<Identifier, T> keyMap = new OrderedHashMap<>();
    private final OrderedHashMap<T, Identifier> valueMap = new OrderedHashMap<>();
    private final Class<T> type;
    private final Identifier id;

    protected Registry(Class<T> clazz, Identifier id) throws IllegalStateException {
        this.id = id;
        this.type = clazz;

        RegistryEvents.REGISTRY_DUMP.listen(this::dumpRegistry);
    }

    public static Collection<Registry<?>> getRegistries() {
        return REGISTRIES.values();
    }

    public static void freeze() {
        frozen = true;
    }

    public Identifier id() {
        return id;
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T> Registry<T> create(Identifier registryName, @NotNull T... type) {
        Class<T> componentType = (Class<T>) type.getClass().getComponentType();
        if (REGISTRIES.containsKey(componentType)) {
            throw new IllegalStateException();
        }

        Registry<T> registry = new Registry<>(componentType, registryName);
        REGISTRIES.put(componentType, registry);

        return registry;
    }

    /**
     * Returns the identifier of the given registered instance.
     *
     * @param obj the registered instance.
     * @return the identifier of it.
     */
    @Nullable
    public Identifier getKey(T obj) {
        return valueMap.get(obj);
    }

    /**
     * Returns the registered instance from the given {@link Identifier}
     *
     * @param key the namespaced key.
     * @return a registered instance create the type {@link T}.
     * @throws ClassCastException if the type is invalid.
     */
    public T getValue(@Nullable Identifier key) {
        if (!keyMap.containsKey(key)) {
            throw new RegistryException("Cannot find object for: " + key + " | type: " + type.getSimpleName());
        }
        return keyMap.get(key);
    }

    public boolean contains(Identifier rl) {
        return keyMap.containsKey(rl);
    }

    public void dumpRegistry() {
        dumpLogger.log("Registry dump: " + type.getSimpleName());
        for (Map.Entry<Identifier, T> entry : entries()) {
            T object = entry.getValue();
            Identifier rl = entry.getKey();

            dumpLogger.log("  (" + rl + ") -> " + object);
        }
    }

    /**
     * Register an object.
     *
     * @param rl  the resource location.
     * @param val the register item value.
     */
    public void register(Identifier rl, T val) {
        if (!type.isAssignableFrom(val.getClass())) {
            throw new IllegalArgumentException("Not allowed type detected, got " + val.getClass() + " expected assignable to " + type);
        }

        keyMap.put(rl, val);
        valueMap.put(val, rl);
    }

    public Collection<T> values() {
        return Collections.unmodifiableCollection(keyMap.values());
    }

    public Set<Identifier> keys() {
        return Collections.unmodifiableSet(keyMap.keySet());
    }

    public Set<Map.Entry<Identifier, T>> entries() {
        // I do this because IDE won's accept dynamic values and keys.
        ArrayList<T> values = new ArrayList<>(values());
        ArrayList<Identifier> keys = new ArrayList<>(keys());

        if (keys.size() != values.size()) throw new IllegalStateException("Keys and values have different lengths.");

        Set<Map.Entry<Identifier, T>> entrySet = new HashSet<>();

        for (int i = 0; i < keys.size(); i++) {
            entrySet.add(new AbstractMap.SimpleEntry<>(keys.get(i), values.get(i)));
        }

        return Collections.unmodifiableSet(entrySet);
    }

    public Class<T> getType() {
        return type;
    }

    public static void dump() {
        for (Registry<?> registry : REGISTRIES.values()) {
            dumpLogger.log("Registry: (" + registry.id() + ") -> {");
            dumpLogger.log("  Type: " + registry.getType().getName() + ";");
            for (Map.Entry<Identifier, ?> entry : registry.entries()) {
                Object o = null;
                String className = null;
                try {
                    o = entry.getValue();
                    className = o.getClass().getName();
                } catch (Throwable ignored) {

                }

                dumpLogger.log("  (" + entry.getKey() + ") -> {");
                dumpLogger.log("    Class : " + className + ";");
                dumpLogger.log("    Object: " + o + ";");
                dumpLogger.log("  }");
            }
            dumpLogger.log("}");
        }
    }

    public boolean isFrozen() {
        return frozen;
    }
}
