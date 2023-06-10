package com.ultreon.libs.registries.v0;

import com.ultreon.libs.commons.v0.Identifier;

import java.util.function.Supplier;

@SuppressWarnings({"unchecked"})
public class RegistrySupplier<B> {
    private final Registry<? super B> registry;
    private final Supplier<B> supplier;
    private final Identifier identifier;

    public <T extends B> RegistrySupplier(Registry<? super B> registry, Supplier<B> supplier, Identifier identifier) {
        this.registry = registry;
        this.supplier = supplier;
        this.identifier = identifier;
    }

    public void register() {
        this.registry.register(this.identifier, this.supplier.get());
    }

    @SuppressWarnings("unchecked")
    public B get() {
        return (B) this.registry.getValue(this.identifier);
    }

    public Identifier id() {
        return this.identifier;
    }
}
