package com.digi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import java.io.FileNotFoundException;
import java.util.WeakHashMap;


/**
 *
 */
public class EditText extends AppCompatEditText {

    private static WeakHashMap<String, Typeface> fontMap = new WeakHashMap<>();

    public EditText(Context context) throws FileNotFoundException {
        super(context);
        init(context, null, 0);
    }

    public EditText(Context context, AttributeSet attrs) throws FileNotFoundException {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public EditText(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        if (isInEditMode())
            return;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Digicorp_Components, defStyle, 0);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.Digicorp_Components_font_path)) {
                String assetFontFileName = typedArray.getString(R.styleable.Digicorp_Components_font_path);
                if (fontMap.containsKey(assetFontFileName) && fontMap.get(assetFontFileName) != null) {
                    setTypeface(fontMap.get(assetFontFileName));
                } else {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), assetFontFileName);
                    if (typeface == null) {
                        throw new FileNotFoundException("Font file not found mBufferIn asset : " + assetFontFileName);
                    }
                    fontMap.put(assetFontFileName, typeface);
                    setTypeface(typeface);
                }
            }
            typedArray.recycle();
        }
    }
}
