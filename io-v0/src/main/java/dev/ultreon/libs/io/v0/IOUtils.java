package dev.ultreon.libs.io.v0;

class IOUtils {
    public static byte[] concat(byte[] data, byte[] buffer) {
        byte[] result = new byte[data.length + buffer.length];
        System.arraycopy(data, 0, result, 0, data.length);
        System.arraycopy(buffer, 0, result, data.length, buffer.length);
        return result;
    }
}
