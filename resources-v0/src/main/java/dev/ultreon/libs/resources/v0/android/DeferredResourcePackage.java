package dev.ultreon.libs.resources.v0.android;

import dev.ultreon.libs.commons.v0.Identifier;
import dev.ultreon.libs.resources.v0.Resource;
import dev.ultreon.libs.resources.v0.ResourcePackage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.HashMap;

public class DeferredResourcePackage extends ResourcePackage {
    private final Class<?> ref;
    private final String root;

    public DeferredResourcePackage(Class<?> ref, String root) {
        super(new HashMap<>());
        this.ref = ref;
        this.root = root;
    }

    @Override
    public boolean has(Identifier entry) {
        return this.getUrl(entry) != null;
    }

    private URL getUrl(Identifier entry) {
        return this.ref.getResource(this.getPath(entry));
    }

    @NotNull
    private String getPath(Identifier entry) {
        return "/" + this.root + "/" + entry.location() + "/" + entry.path();
    }

    @Override
    public Resource get(Identifier entry) {
        if (!this.has(entry)) return null;
        if (this.resources.containsKey(entry)) return this.resources.get(entry);

        Resource resource = new Resource(() -> this.ref.getResourceAsStream(this.getPath(entry)));
        this.resources.put(entry, resource);
        return resource;
    }
}
