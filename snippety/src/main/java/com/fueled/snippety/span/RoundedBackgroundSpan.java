package com.fueled.snippety.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

public class RoundedBackgroundSpan extends ReplacementSpan {

    private int cornerRadius = 8;
    private int backgroundColor = 0;
    private int textColor = 0;

    public RoundedBackgroundSpan(int backgroundColor, int textColor, int cornerRadius) {
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.cornerRadius = cornerRadius;
    }

    public RoundedBackgroundSpan(int backgroundColor, int textColor) {
        this(backgroundColor, textColor, 8);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        RectF rect = new RectF(x, top, x + paint.measureText(text, start, end) + cornerRadius, bottom);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint);
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + cornerRadius / 2, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}