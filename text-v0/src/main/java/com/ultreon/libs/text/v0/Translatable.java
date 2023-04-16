package com.ultreon.libs.text.v0;

import com.ultreon.libs.translations.v0.Language;

public interface Translatable {
    String getTranslationPath();

    default MutableText getTranslation() {
        return TextObject.translation(getTranslationPath());
    }

    default String getTranslationText() {
        return Language.translate(getTranslationPath());
    }
}
