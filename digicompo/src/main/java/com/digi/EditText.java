package com.digi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import java.io.FileNotFoundException;

/**
 *
 */
public class EditText extends AppCompatEditText {

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

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditText, defStyle, 0);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.EditText_font_path)) {
                String assetFontFileName = typedArray.getString(R.styleable.EditText_font_path);
                Typeface typeface = FontCache.get(context.getAssets(), assetFontFileName);
                setTypeface(typeface);
            }
            typedArray.recycle();
        }
    }
}
