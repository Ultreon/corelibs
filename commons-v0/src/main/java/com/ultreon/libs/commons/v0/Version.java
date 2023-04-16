package com.ultreon.libs.commons.v0;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public record Version(int major, int minor, int build, VersionType type, int release) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    @Override
    public String toString() {
        return major + "." + minor + "." + build + "-" + type.getName() + release;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return major == version.major &&
                minor == version.minor &&
                release == version.release &&
                type == version.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, type, release);
    }

}
