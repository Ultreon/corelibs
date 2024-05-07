package dev.ultreon.libs.events.v0;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubscribeEvent {
    boolean ignoreCancelled() default false;

    EventPriority priority() default EventPriority.NORMAL;
}
