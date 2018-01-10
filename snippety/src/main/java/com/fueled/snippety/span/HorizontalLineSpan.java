package com.fueled.snippety.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;

/**
 * source: https://medium.com/@efemoney/i-drew-lines-in-a-textview-using-text-spans-31bce948acaf
 */
import java.lang.ref.WeakReference;

/**
 *  Draws a line with specified Drawable.
 *
 *  source: @link{https://medium.com/@efemoney/i-drew-lines-in-a-textview-using-text-spans-31bce948acaf}
 */

public class HorizontalLineSpan extends ReplacementSpan {

    private Drawable drawable;

    public HorizontalLineSpan(Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public int getSize(Paint paint, CharSequence text,
                       int start, int end,
                       Paint.FontMetricsInt fm) {

        return 0;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y,
                     int bottom, Paint paint) {

        Paint.FontMetricsInt fmi = paint.getFontMetricsInt();

        int height = fmi.descent - fmi.ascent; // set the drawable height to the height of the current font
        Drawable b = getDrawable(canvas.getWidth(), height);

        canvas.save();

        int transY = bottom - b.getBounds().bottom;

        canvas.translate(x, transY);
        b.draw(canvas);

        canvas.restore();
    }

    private Drawable getDrawable(int width, int height) {

        // We copy the implementation of getCachedDrawable in DynamicDrawableSpan
        Drawable d = getCachedDrawable();
        d.setBounds(0, 0, width, height);
        return d;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    // Redefined locally because it is a private member from DynamicDrawableSpan
    private Drawable getCachedDrawable() {
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;

        if (wr != null)
            d = wr.get();

        if (d == null) {
            d = getDrawable();
            mDrawableRef = new WeakReference<>(d);
        }

        return d;
    }

    private WeakReference<Drawable> mDrawableRef;
}
