package com.ultreon.libs.commons.v0;

import com.ultreon.libs.commons.v0.tuple.Pair;
import com.ultreon.libs.commons.v0.exceptions.SyntaxException;
import org.jetbrains.annotations.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;

public final class Identifier {
    private final @NotNull String location;
    private final @NotNull String path;
    private static String defaultNamespace = "default";
    private static boolean defaultSet = false;

    public synchronized static void setDefaultNamespace(String defaultNamespace) {
        if (defaultSet) {
            throw new IllegalStateException("Default namespace already set.");
        }
        Identifier.defaultSet = true;
        Identifier.defaultNamespace = defaultNamespace;
    }

    public static String getDefaultNamespace() {
        return Identifier.defaultNamespace;
    }

    public Identifier(@NotNull String location, @NotNull String path) {
        testLocation(location);
        testPath(path);

        this.location = location;
        this.path = path;
    }

    public Identifier(@NotNull String name) {
        String[] split = name.split(":", 2);
        if (split.length == 2) {
            this.location = testLocation(split[0]);
            this.path = testPath(split[1]);
        } else {
            this.location = defaultNamespace;
            this.path = testPath(name);
        }
    }

    @NotNull
    @Contract("_ -> new")
    public static Identifier parse(
            @NotNull String name) {
        return new Identifier(name);
    }

    @Nullable
    @Contract("null -> null")
    public static Identifier tryParse(@Nullable String name) {
        if (name == null) return null;

        try {
            return new Identifier(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Contract("_ -> param1")
    public static String testLocation(String location) {
        if (!Pattern.matches("([a-z\\d_]+)(\\.[a-z][a-z\\d_]+)*", location)) {
            throw new SyntaxException("Location is invalid: " + location);
        }
        return location;
    }

    @Contract("_ -> param1")
    public static @NotNull String testPath(String path) {
        if (!Pattern.matches("([a-z_.\\d]+)(/[a-z_.\\d]+)*", path)) {
            throw new SyntaxException("Path is invalid: " + path);
        }
        return path;
    }

    @Override
    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return location.equals(that.location) && path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, path);
    }

    @NotNull
    @Override
    @Contract(pure = true)
    public String toString() {
        return location + ":" + path;
    }

    /**
     * @return object location (the mod id / namespace).
     */
    @NotNull
    @Contract(pure = true)
    public String location() {
        return location;
    }

    /**
     * @return object path.
     */
    @NotNull
    @Contract(pure = true)
    public String path() {
        return path;
    }

    @Contract("_ -> new")
    public Identifier withLocation(String location) {
        return new Identifier(location, path);
    }

    @Contract("_ -> new")
    public Identifier withPath(String path) {
        return new Identifier(this.location, path);
    }

    @Contract("_ -> new")
    public Identifier mapLocation(Function<String, String> location) {
        return new Identifier(location.apply(this.location), this.path);
    }

    @Contract("_ -> new")
    public Identifier mapPath(Function<String, String> path) {
        return new Identifier(this.location, path.apply(this.path));
    }

    @Contract("_, _ -> new")
    public Identifier map(Function<String, String> path, Function<String, String> location) {
        return new Identifier(location.apply(this.location), path.apply(this.path));
    }

    public <T> T reduce(BiFunction<String, String, T> func) {
        return func.apply(location, path);
    }

    @NotNull
    @Unmodifiable
    @Contract(value = "-> new", pure = true)
    public List<String> toList() {
        return List.of(location, path);
    }

    @NotNull
    @Contract(" -> new")
    public ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(location);
        list.add(path);
        return list;
    }

    @NotNull
    @UnmodifiableView
    @Contract(pure = true)
    public Collection<String> toCollection() {
        return toList();
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public Pair<String, String> toPair() {
        return new Pair<>(location, path);
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public String[] toArray() {
        return new String[]{location, path};
    }
}
