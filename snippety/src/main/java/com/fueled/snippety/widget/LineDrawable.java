package com.fueled.snippety.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Draws a Line with specified width and color.
 *
 * source: https://medium.com/@efemoney/i-drew-lines-in-a-textview-using-text-spans-31bce948acaf
 */

public class LineDrawable extends Drawable {

    private final Paint paint;

    public LineDrawable(float lineWidth, int lineColor) {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        paint.setColor(lineColor);
    }

    @Override

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();

        canvas.save();
        canvas.drawLine(bounds.left, bounds.centerY(), bounds.right - bounds.left, bounds.centerY(), paint);
        canvas.restore();
    }

    @Override public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override public int getOpacity() { return PixelFormat.OPAQUE; }
}