package com.example.adaanahmed.westerosatwar.UIWidget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.adaanahmed.westerosatwar.R;

/**
 * Author  : Adnaan 'Zohran' Ahmed
 * Date    : 08 Jan 2017.
 * Email   : adnaan.1703@gmail.com
 */


public class ProximaTextView extends AppCompatTextView implements fontWidget{

    public ProximaTextView(Context context) {
        super(context);
        init(null);
    }

    public ProximaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ProximaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Resources.Theme theme = getContext().getTheme();
        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.ProximaTextView, 0, 0);
        int fontIndex = a.getInt(R.styleable.ProximaTextView_proxima_font, 0);
        a.recycle();
        setFont(fontIndex);
    }

    private void setFont(int fontIndex) {
        if (fontIndex < 0 || fontIndex >= FONT_NAMES.length) {
            return;
        }
        setCustomFont(FONT_NAMES[fontIndex]);
    }

    public void setCustomFont(String fontName) {
        Typeface assetFont = Typeface.createFromAsset(getContext().getAssets(), fontName);
        setTypeface(assetFont);
    }


}
