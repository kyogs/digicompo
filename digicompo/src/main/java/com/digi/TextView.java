package com.digi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import java.io.FileNotFoundException;

/**
 *
 */
public class TextView extends AppCompatTextView {

    private static final int HTML_STYLE_STRIKE_THROUGH = 0;

    public TextView(Context context) throws FileNotFoundException {
        super(context);
        init(context, null, 0);
    }

    public TextView(Context context, AttributeSet attrs) throws FileNotFoundException {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TextView(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) throws FileNotFoundException {
        if (isInEditMode())
            return;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyle, 0);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.TextView_font_path)) {
                String assetFontFileName = typedArray.getString(R.styleable.TextView_font_path);
                Typeface typeface = FontCache.get(context.getAssets(), assetFontFileName);
                setTypeface(typeface);
            }
            if (typedArray.hasValue(R.styleable.TextView_html_style)) {
                int style = typedArray.getInt(R.styleable.TextView_html_style, -1);
                if (style != -1) {
                    switch (style) {
                        case HTML_STYLE_STRIKE_THROUGH:
                            setPaintFlags(getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                }
            }
            typedArray.recycle();
        }
    }


}
