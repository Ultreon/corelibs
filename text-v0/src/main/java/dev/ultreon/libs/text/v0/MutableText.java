package dev.ultreon.libs.text.v0;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.*;
import java.util.List;

import static java.text.AttributedCharacterIterator.Attribute;

public abstract class MutableText extends TextObject {
    final List<TextObject> extras = new ArrayList<>();
    private final Map<Attribute, Object> attrs = new HashMap<>();
    private Color color;

    @Override
    public AttributedString getAttrString() {
        AttributedStringBuilder builder = new AttributedStringBuilder();
        String string = this.createString();
        if (!string.isEmpty()) builder.append(new AttributedString(string, this.getAttrs()));
        for (TextObject extra : this.extras) {
            builder.append(extra.getAttrString());
        }

        return builder.build();
    }

    @Override
    public final String getText() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.createString());
        for (TextObject extra : this.extras) {
            builder.append(extra.getText());
        }
        return builder.toString();
    }

    public Color getColor() {
        return (Color) this.attrs.get(TextAttribute.FOREGROUND);
    }

    public MutableText setColor(Color color) {
        this.attrs.put(TextAttribute.FOREGROUND, color);
        return this;
    }

    public boolean isUnderlined() {
        return this.attrs.get(TextAttribute.UNDERLINE) != null;
    }

    public MutableText setUnderlined(boolean underlined) {
        this.attrs.put(TextAttribute.FOREGROUND, underlined ? TextAttribute.UNDERLINE_LOW_ONE_PIXEL : null);
        return this;
    }

    public boolean isStrikethrough() {
        return (boolean) this.attrs.get(TextAttribute.STRIKETHROUGH);
    }

    public MutableText setStrikethrough(boolean strikethrough) {
        this.attrs.put(TextAttribute.STRIKETHROUGH, strikethrough);
        return this;
    }

    public boolean isLigaturesEnabled() {
        return Objects.equals(this.attrs.get(TextAttribute.LIGATURES), 1);
    }

    public MutableText setLigaturesEnabled(boolean ligaturesEnabled) {
        this.attrs.put(TextAttribute.LIGATURES, ligaturesEnabled ? 1 : 0);
        return this;
    }

    public double getSize() {
        return ((Number) this.attrs.get(TextAttribute.SIZE)).doubleValue();
    }

    public MutableText setSize(double size) {
        this.attrs.put(TextAttribute.SIZE, size);
        return this;
    }

    public float getWidth() {
        return (float) this.attrs.get(TextAttribute.SIZE);
    }

    public MutableText setWidth(float size) {
        this.attrs.put(TextAttribute.WIDTH, size);
        return this;
    }

    public FontWidth getFontWidth() {
        return FontWidth.closestTo((float) this.attrs.get(TextAttribute.SIZE));
    }

    public MutableText setFontWidth(FontWidth width) {
        this.attrs.put(TextAttribute.WIDTH, width.getWidth());
        return this;
    }

    public float getWeight() {
        return (float) this.attrs.get(TextAttribute.WEIGHT);
    }

    public MutableText setWeight(float weight) {
        this.attrs.put(TextAttribute.WEIGHT, weight);
        return this;
    }

    public @NotNull FontWeight getFontWeight() {
        return FontWeight.closestTo((float) this.attrs.get(TextAttribute.WEIGHT));
    }

    public MutableText setFontWeight(@NotNull FontWeight weight) {
        this.attrs.put(TextAttribute.WEIGHT, weight.getWeight());
        return this;
    }

    @Range(from = -7, to = 7)
    public int getSuperscript() {
        return (int) this.attrs.get(TextAttribute.SUPERSCRIPT);
    }

    public MutableText setFontWeight(@Range(from = -7, to = 7) int superscript) {
        this.attrs.put(TextAttribute.SUPERSCRIPT, superscript);
        return this;
    }

    public Map<? extends Attribute, ?> getAttrs() {
        return this.attrs;
    }

    public MutableText append(TextObject textObject) {
        this.extras.add(textObject);
        return this;
    }

    public MutableText append(String text) {
        this.extras.add(TextObject.nullToEmpty(text));
        return this;
    }

    public MutableText append(Object o) {
        this.extras.add(TextObject.nullToEmpty(String.valueOf(o)));
        return this;
    }
}
