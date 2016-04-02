package com.mobileappscompany.training.dailyphoto.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Author mpamplona
 * created on 3/19/2016
 * <p/>
 * Custom TextView with Roboto
 */
public class TextViewRoboto extends TextView {

    public static final String FONT_ROBOTO = "fonts/RobotoCondensed-Regular.ttf";

    protected void custom() {
        Typeface typeface = Typeface.createFromAsset(this.getContext().getAssets(), FONT_ROBOTO);
        this.setTypeface(typeface);
    }

    public TextViewRoboto(Context context) {
        super(context);
        custom();
    }

    public TextViewRoboto(Context context, AttributeSet attrs) {
        super(context, attrs);
        custom();
    }

    public TextViewRoboto(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        custom();
    }

    @TargetApi(21)
    public TextViewRoboto(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        custom();
    }
}
