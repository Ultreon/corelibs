package com.ultreon.libs.events.v0;

import com.ultreon.libs.commons.v0.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Deprecated
@SuppressWarnings("unchecked")
public abstract class AbstractEventBus<T extends AbstractEvent> {
    @Deprecated()
    protected static final Predicate<Method> classPredicate;

    @Deprecated()
    protected static final Predicate<Method> instancePredicate;

    static {
        Predicate<Method> isHandler = AbstractEventBus::isSubscriber;
        Predicate<Method> isSubscriber = AbstractEventBus::isSubscribing;
        classPredicate = isHandler.and(isSubscriber).and((method) -> Modifier.isStatic(method.getModifiers()));
        instancePredicate = isHandler.and(isSubscriber).and((method) -> !Modifier.isStatic(method.getModifiers()));
    }

    @Deprecated()
    public List<AbstractSubscription> subscriptions = new ArrayList<>();
    @Deprecated()
    public Map<Long, Class<? extends AbstractEvent>> classMap = new HashMap<>();

    @Deprecated()
    public final Map<Class<? extends AbstractEvent>, CopyOnWriteArraySet<Pair<Object, Method>>> eventToMethod = new HashMap<>();
    @Deprecated()
    public final Map<Pair<Object, Method>, CopyOnWriteArraySet<Class<? extends AbstractEvent>>> methodToEvent = new HashMap<>();

    @Deprecated()
    public AbstractEventBus() {

    }

    @Deprecated()
    private static boolean isSubscribing(Method method) {
//        LogManager.getLogger("Subscribe-Check").info(method.getDeclaringClass().getName() + "." + method.getName());

        return method.isAnnotationPresent(SubscribeEvent.class);
    }

    @Deprecated()
    private static boolean isSubscriber(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 1) {
            Class<?> clazz1 = parameterTypes[0];
            return AbstractEvent.class.isAssignableFrom(clazz1);
        }
        return false;
    }

    @Deprecated()
    public <E extends T> boolean publish(E event) {
        if (!this.eventToMethod.containsKey(event.getClass())) {
            return false;
        }

        CopyOnWriteArraySet<Pair<Object, Method>> methods = this.eventToMethod.get(event.getClass());
        for (Pair<Object, Method> method : methods) {
//            logger.info("Sending " + event.getClass().getName() + " to " + method.getSecond().getName());
            try {
                method.getSecond().invoke(method.getFirst(), event);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        }

        return event instanceof ICancellable && ((ICancellable) event).isCancelled();
    }

    @Deprecated()
    public void subscribe(Class<?> clazz) {
        this.loopDeclaredMethods(clazz, (method) -> {
            // Get types and values.
            Class<? extends AbstractEvent> event = (Class<? extends AbstractEvent>) method.getParameterTypes()[0];
            this.addHandlers(event, null, method);
        });
    }

    @Deprecated()
    public void subscribe(Object o) {
        this.loopMethods(o, (method) -> {
            // Get types and values.
            Class<? extends AbstractEvent> event = (Class<? extends AbstractEvent>) method.getParameterTypes()[0];
            this.addHandlers(event, o, method);
        });
    }

    @Deprecated()
    public void unsubscribe(Class<? extends T> event, Class<?> clazz) {
        this.loopDeclaredMethods(clazz, (method) -> {
            // Get and check event.
            Class<? extends AbstractEvent> evt = (Class<? extends AbstractEvent>) method.getParameterTypes()[0];
            if (event == evt) {
                // Remove handler.
                try {
                    this.removeHandlers(event, null, method);
                } catch (IllegalStateException ignored) {

                }
            }
        });
    }

    @Deprecated()
    public void unsubscribe(Class<? extends T> event, Object o) {
        this.loopMethods(o, (method) -> {
            // Get types and values.
            Class<? extends AbstractEvent> evt = (Class<? extends AbstractEvent>) method.getParameterTypes()[0];
            if (event == evt) {
                // Remove handler.
                try {
                    this.removeHandlers(event, o, method);
                } catch (IllegalStateException ignored) {

                }
            }
        });
    }

    @Deprecated()
    public void unsubscribe(Class<?> clazz) {
        this.loopDeclaredMethods(clazz, (method) -> {
            // Get and check event.
            Class<? extends AbstractEvent> evt = (Class<? extends AbstractEvent>) method.getParameterTypes()[0];

            // Remove handler.
            try {
                this.removeHandlers(evt, null, method);
            } catch (IllegalStateException ignored) {

            }
        });
    }

    @Deprecated()
    public void unsubscribe(Object o) {
        this.loopMethods(o, (method) -> {
            // Get types and values.
            Class<? extends AbstractEvent> evt = (Class<? extends AbstractEvent>) method.getParameterTypes()[0];

            // Remove handler.
            try {
                this.removeHandlers(evt, o, method);
            } catch (IllegalStateException ignored) {

            }
        });
    }

    @Deprecated()
    private void loopDeclaredMethods(Class<?> clazz, Consumer<Method> consumer) {
        // Loop declared methods.
        this.loopMethods0(clazz.getDeclaredMethods(), classPredicate, consumer);
    }

    @Deprecated()
    private void loopMethods(Object o, Consumer<Method> consumer) {
        // Loop methods.
        this.loopMethods0(o.getClass().getMethods(), instancePredicate, consumer);
    }

    @Deprecated()
    private void loopMethods0(Method[] methods, Predicate<Method> predicate, Consumer<Method> consumer) {
        // Check all methods for event subscribers.
        for (Method method : methods) {
            // Check is instance method.
            if (predicate.test(method)) {
                // Set accessible.
                method.setAccessible(true);
                consumer.accept(method);
            }
        }
    }

    @Deprecated()
    private void removeHandlers(Class<? extends AbstractEvent> event, @Nullable Object obj, Method method) {
        Pair<Object, Method> pair = new Pair<>(obj, method);
        if (!this.eventToMethod.containsKey(event)) {
            throw new IllegalStateException("Cannot unregister method for a non-registered event.");
        } else if (!this.eventToMethod.get(event).contains(pair)) {
            throw new IllegalStateException("Cannot unregister an unregistered method.");
        }

        if (!this.methodToEvent.containsKey(pair)) {
            throw new IllegalStateException("Cannot unregister an unregistered method.");
        } else if (!this.methodToEvent.get(pair).contains(event)) {
            throw new IllegalStateException("Cannot unregister method for a non-registered event.");
        }

        this.methodToEvent.get(pair).remove(event);
        this.eventToMethod.get(event).remove(pair);
    }

    @Deprecated()
    private void removeAllEvents(@Nullable Object obj, Method method) {
        Pair<Object, Method> pair = new Pair<>(obj, method);
        if (!this.methodToEvent.containsKey(pair)) {
            throw new IllegalStateException("Cannot unregister an unregistered method.");
        }

        for (Class<?> event : this.methodToEvent.get(pair)) {
            this.eventToMethod.get(event).remove(pair);
        }

        this.methodToEvent.remove(pair);
    }

    @Deprecated()
    protected void addHandlers(Class<? extends AbstractEvent> event, @Nullable Object obj, Method method) {
        Pair<Object, Method> pair = new Pair<>(obj, method);
        if (!this.eventToMethod.containsKey(event)) {
            this.eventToMethod.put(event, new CopyOnWriteArraySet<>());
        }
        if (!this.methodToEvent.containsKey(pair)) {
            this.methodToEvent.put(pair, new CopyOnWriteArraySet<>());
        }
        this.eventToMethod.get(event).add(pair);
        this.methodToEvent.get(pair).add(event);
    }

    @Deprecated
    public static abstract class AbstractSubscription {
        @Deprecated
        protected abstract void onRemove();

        @Deprecated
        public abstract <T extends AbstractEvent> Collection<Subscriber<T>> getSubscribers(Class<T> clazz);

        @Deprecated
        public abstract long id();

        @Deprecated
        public void unsubscribe() {
            this.onRemove();
        }

        @Deprecated
        @SuppressWarnings("unchecked")
        <T extends AbstractEvent> void onPublish(T event) {
            Collection<Subscriber<T>> handlers = this.getSubscribers((Class<T>) event.getClass());
            if (handlers == null) {
                return;
            }

            for (Subscriber<T> handler : handlers) {
                handler.handle(event);
            }
        }
    }
}
