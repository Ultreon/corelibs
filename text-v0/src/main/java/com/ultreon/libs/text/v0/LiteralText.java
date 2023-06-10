package com.ultreon.libs.text.v0;

public class LiteralText extends MutableText {
    private final String text;

    LiteralText(String text) {
        this.text = text;
    }

    @Override
    protected String createString() {
        return this.text;
    }
}
