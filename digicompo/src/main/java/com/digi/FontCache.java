package com.digi;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.FileNotFoundException;
import java.util.WeakHashMap;

/**
 * Created by yogesh.kamaliya on 11/08/17.
 */

class FontCache {
    private static WeakHashMap<String, Typeface> fontMap = new WeakHashMap<>();

    static Typeface get(AssetManager am, String name) throws FileNotFoundException {
        if (fontMap.containsKey(name) && fontMap.get(name) != null) {
            return fontMap.get(name);
        }

        Typeface typeface = Typeface.createFromAsset(am, name);
        if (typeface == null) {
            throw new FileNotFoundException("Font file not found mBufferIn asset : " + name);
        }
        fontMap.put(name, typeface);
        return typeface;
    }
}
