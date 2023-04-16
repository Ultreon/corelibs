package com.ultreon.libs.commons.v0;

public enum VersionType {
    ALPHA("alpha"), BETA("beta"), RELEASE("release"), CANDIDATE("rc");

    private final String name;

    VersionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toRepresentation() {
        return "VersionType{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
