package com.ultreon.libs.translations.v0;

import com.google.gson.*;
import com.ultreon.libs.commons.v0.Identifier;
import com.ultreon.libs.commons.v0.Logger;
import com.ultreon.libs.registries.v0.Registry;
import com.ultreon.libs.resources.v0.ResourceManager;

import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class LanguageManager {
    public static final LanguageManager INSTANCE = new LanguageManager();
    public static final Registry<Language> REGISTRY = Registry.create(new Identifier("languages"));
    private static Locale currentLanguage;
    private final Map<String, Language> languages = new HashMap<>();
    private final Set<String> locales = new HashSet<>();
    private final Set<String> ids = new HashSet<>();
    private final Map<String, String> locale2id = new HashMap<>();
    private final Map<String, String> id2locale = new HashMap<>();
    public Logger logger = (level, message, t) -> {};

    private LanguageManager() {

    }

    public static Locale getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(Locale currentLanguage) {
        LanguageManager.currentLanguage = currentLanguage;
    }

    public Language load(Locale locale, Identifier id, ResourceManager resourceManager) {
        Gson gson = new Gson();
        String newPath = "languages/" + id.path() + ".json";
        List<byte[]> assets = resourceManager.getAllDataById(id.withPath(newPath));
        JsonObject json = new JsonObject();
        for (byte[] asset : assets) {
            JsonObject object = gson.fromJson(new StringReader(new String(asset, StandardCharsets.UTF_8)), JsonObject.class);
            recurse(json, object);
        }

        Language language = new Language(locale, json, id);
        languages.put(locale.getLanguage(), language);
        REGISTRY.register(id, language);
        return language;
    }

    public Language load(Locale locale, Identifier id, Reader reader) {
        Gson gson = new Gson();
        String s = "languages/" + id + ".json";
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        Language language = new Language(locale, json, id);
        languages.put(locale.getLanguage(), language);
        REGISTRY.register(id, language);
        return language;
    }

    public Language get(Locale locale) {
        return languages.get(locale.getLanguage());
    }

    public void register(Locale locale, String id) {
        if (locales.contains(locale.getLanguage())) {
            logger.warn("Locale overridden: " + locale.getLanguage());
        }
        if (ids.contains(id)) {
            logger.warn("LanguageID overridden: " + id);
        }

        this.locales.add(locale.getLanguage());
        this.ids.add(id);
        this.locale2id.put(locale.getLanguage(), id);
        this.id2locale.put(id, locale.getLanguage());
    }

    public Locale getLocale(String id) {
        return new Locale(id2locale.get(id));
    }

    public String getLanguageID(Locale locale) {
        return locale2id.get(locale.getLanguage());
    }

    private void recurse(JsonObject json, JsonObject object) {
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            JsonElement value = entry.getValue();
            String key = entry.getKey();
            if (value instanceof JsonObject obj) {
                if (json.has(key)) {
                    if (json.get(key) instanceof JsonObject) {
                        recurse(json.getAsJsonObject(key), obj);
                    }
                }
            }
            if (value instanceof JsonArray) {
                throw new JsonParseException("Not allowed to have json arrays in language json.");
            }
            json.add(key, value);
        }
    }

    public Set<Locale> getLocales() {
        return locales.stream().map(Locale::new).collect(Collectors.toSet());
    }

    public Set<String> getLanguageIDs() {
        return ids;
    }

    public List<Language> getLanguages() {
        return new ArrayList<>(languages.values());
    }
}
