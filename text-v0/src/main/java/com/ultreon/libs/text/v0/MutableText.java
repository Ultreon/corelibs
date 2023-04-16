package com.ultreon.libs.text.v0;

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
        var builder = new AttributedStringBuilder();
        builder.append(new AttributedString(createString(), getAttrs()));
        for (TextObject extra : extras) {
            builder.append(extra.getAttrString());
        }

        return builder.build();
    }

    @Override
    public final String getText() {
        var builder = new StringBuilder();
        builder.append(createString());
        for (TextObject extra : extras) {
            builder.append(extra.getText());
        }
        return builder.toString();
    }

    public Color getColor() {
        return (Color) attrs.get(TextAttribute.FOREGROUND);
    }

    public void setColor(Color color) {
        this.attrs.put(TextAttribute.FOREGROUND, color);
    }

    public boolean isUnderlined() {
        return attrs.get(TextAttribute.UNDERLINE) != null;
    }

    public void setUnderlined(boolean underlined) {
        this.attrs.put(TextAttribute.FOREGROUND, underlined ? TextAttribute.UNDERLINE_LOW_ONE_PIXEL : null);
    }

    public boolean isStrikethrough() {
        return (boolean) attrs.get(TextAttribute.STRIKETHROUGH);
    }

    public void setStrikethrough(boolean strikethrough) {
        this.attrs.put(TextAttribute.STRIKETHROUGH, strikethrough);
    }

    public boolean isLigaturesEnabled() {
        return Objects.equals(attrs.get(TextAttribute.LIGATURES), 1);
    }

    public void setLigaturesEnabled(boolean ligaturesEnabled) {
        this.attrs.put(TextAttribute.LIGATURES, ligaturesEnabled ? 1 : 0);
    }

    public double getSize() {
        return ((Number)attrs.get(TextAttribute.SIZE)).doubleValue();
    }

    public void setSize(double size) {
        this.attrs.put(TextAttribute.SIZE, size);
    }

    public float getWidth() {
        return (float)attrs.get(TextAttribute.SIZE);
    }

    public void setWidth(float size) {
        this.attrs.put(TextAttribute.WIDTH, size);
    }

    public FontWidth getFontWidth() {
        return FontWidth.closestTo((float)attrs.get(TextAttribute.SIZE));
    }

    public void setFontWidth(FontWidth width) {
        this.attrs.put(TextAttribute.WIDTH, width.getWidth());
    }

    public float getWeight() {
        return (float) attrs.get(TextAttribute.WEIGHT);
    }

    public void setWeight(float weight) {
        this.attrs.put(TextAttribute.WEIGHT, weight);
    }

    public @NotNull FontWeight getFontWeight() {
        return FontWeight.closestTo((float) attrs.get(TextAttribute.WEIGHT));
    }

    public void setFontWeight(@NotNull FontWeight weight) {
        this.attrs.put(TextAttribute.WEIGHT, weight.getWeight());
    }

    @Range(from = -7, to = 7)
    public int getSuperscript() {
        return (int) attrs.get(TextAttribute.SUPERSCRIPT);
    }

    public void setFontWeight(@Range(from = -7, to = 7) int superscript) {
        this.attrs.put(TextAttribute.SUPERSCRIPT, superscript);
    }

    public Map<? extends Attribute, ?> getAttrs() {
        return attrs;
    }

    public MutableText append(TextObject textObject) {
        extras.add(textObject);
        return this;
    }
}
