package com.digi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import java.io.FileNotFoundException;
import java.util.WeakHashMap;

/**
 * Created by kevin.adesara on 07/07/14.
 */
public class Button extends AppCompatButton {

    public Button(Context context) throws FileNotFoundException {
        super(context);
        init(context, null, 0);
    }

    public Button(Context context, AttributeSet attrs) throws FileNotFoundException {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public Button(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        if (isInEditMode())
            return;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Button, defStyle, 0);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.Button_font_path)) {
                String assetFontFileName = typedArray.getString(R.styleable.Button_font_path);
                Typeface typeface = FontCache.get(context.getAssets(), assetFontFileName);
                setTypeface(typeface);
            }
            typedArray.recycle();
        }
    }
}
