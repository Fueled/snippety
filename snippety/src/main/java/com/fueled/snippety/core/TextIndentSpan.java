package com.fueled.snippety.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

public class TextIndentSpan implements LeadingMarginSpan {

    private final String data;
    private final Options options;

    public TextIndentSpan(Options options) {
        this(options, "•");
    }

    public TextIndentSpan(Options options, int index) {
        this(options, index + ".");
    }

    public TextIndentSpan(Options options, String data) {
        this.options = options;
        this.data = data;
    }

    public int getLeadingMargin(boolean first) {
        return options.leadWidth + options.gapWidth;
    }

    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline,
                                  int bottom, CharSequence text, int start, int end, boolean first, Layout l) {
        if (first) {
            Paint paint = new Paint(p);

            paint.setStyle(Paint.Style.FILL);

            if (options.textSize != -1) {
                paint.setTextSize(options.textSize);
            }

            if (options.textColor != -1) {
                paint.setColor(options.textColor);
            }

            if (options.typeface != null) {
                paint.setTypeface(options.typeface);
            }

            c.drawText(data,  x + options.leadWidth, baseline, paint);
        }
    }

    public static class Options {
        public int gapWidth;
        public int leadWidth;
        public int textSize = -1;
        public int textColor = -1;
        public Typeface typeface;

        public Options(int leadWidth, int gapWidth) {
            this.leadWidth = leadWidth;
            this.gapWidth = gapWidth;
        }
    }

}
