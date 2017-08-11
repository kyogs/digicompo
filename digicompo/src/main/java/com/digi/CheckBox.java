package com.digi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;


import java.io.FileNotFoundException;
import java.util.WeakHashMap;

/**
 *
 */
public class CheckBox extends AppCompatCheckBox {

    public CheckBox(Context context) throws FileNotFoundException {
        super(context);
        init(context, null, 0);
    }

    public CheckBox(Context context, AttributeSet attrs) throws FileNotFoundException {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        if (isInEditMode())
            return;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckBox, defStyle, 0);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.CheckBox_font_path)) {
                String assetFontFileName = typedArray.getString(R.styleable.CheckBox_font_path);
                Typeface typeface = FontCache.get(context.getAssets(), assetFontFileName);
                setTypeface(typeface);
            }
            typedArray.recycle();
        }
    }
}