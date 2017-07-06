package com.fueled.snippety.core;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import com.fueled.snippety.span.MultiColorSpan;
import com.fueled.snippety.span.RoundedBackgroundSpan;
import com.fueled.snippety.span.TextIndentSpan;
import com.fueled.snippety.span.TextTypefaceSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 25/04/17
 */

public class Snippety {

    private List<Object> spans;

    public Snippety() {
        spans = new ArrayList<>();
    }

    public List<Object> getSpans() {
        return spans;
    }

    public Snippety textColor(int color) {
        spans.add(new ForegroundColorSpan(color));
        return this;
    }

    public Snippety textMultiColor(int[] colors) {
        spans.add(new MultiColorSpan(colors));
        return this;
    }

    public Snippety textMultiColor(int[] colors, int angle) {
        spans.add(new MultiColorSpan(colors, angle));
        return this;
    }

    public Snippety backgroundColor(int color) {
        spans.add(new BackgroundColorSpan(color));
        return this;
    }

    public Snippety roundedBackgroundColor(int backgroundColor, int textColor) {
        spans.add(new RoundedBackgroundSpan(backgroundColor, textColor));
        return this;
    }

    public Snippety roundedBackgroundColor(int backgroundColor, int textColor, int cornerRadius) {
        spans.add(new RoundedBackgroundSpan(backgroundColor, textColor, cornerRadius));
        return this;
    }

    public Snippety indent(Indent indent) {
        AlignmentSpan span;
        switch (indent) {
            case LEFT:
                span = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL);
                break;
            case RIGHT:
                span = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE);
                break;
            case CENTER:
            default:
                span = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER);
                break;
        }

        spans.add(span);

        return this;
    }

    public Snippety underline() {
        spans.add(new UnderlineSpan());
        return this;
    }

    public Snippety subscript() {
        spans.add(new SubscriptSpan());
        return this;
    }

    public Snippety superscript() {
        spans.add(new SuperscriptSpan());
        return this;
    }

    public Snippety strikethrough() {
        spans.add(new StrikethroughSpan());
        return this;
    }

    public Snippety url(String url) {
        spans.add(new URLSpan(url));
        return this;
    }

    public Snippety quote(int color) {
        spans.add(new QuoteSpan(color));
        return this;
    }
    
    public Snippety fontStyle(FontStyle fontStyle) {
        StyleSpan span;

        switch (fontStyle) {
            case BOLD:
                span = new StyleSpan(Typeface.BOLD);
                break;
            case ITALIC:
                span = new StyleSpan(Typeface.ITALIC);
                break;
            case NORMAL:
            default:
                span = new StyleSpan(Typeface.NORMAL);
                break;
        }

        spans.add(span);

        return this;
    }

    public Snippety fontStyle(Typeface typeface) {
        spans.add(new TextTypefaceSpan(typeface));
        return this;
    }

    public Snippety addListener(final SpanListener spanListener) {
        // Make sure ClickableSpan is at the beginning of the list so other spans can be applied
        // correctly.
        spans.add(0, new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                spanListener.onSpanClicked();
            }
        });

        return this;
    }

    public Snippety textSize(float size) {
        spans.add(new RelativeSizeSpan(size));
        return this;
    }

    public Snippety testSizeAbsolute(int size) {
        spans.add(new AbsoluteSizeSpan(size));
        return this;
    }

    public Snippety bullet(int leadGap, int gapWidth) {
        spans.add(new TextIndentSpan(new TextIndentSpan.Options(leadGap, gapWidth)));
        return this;
    }

    public Snippety number(int leadGap, int gapWidth, int number) {
        spans.add(new TextIndentSpan(new TextIndentSpan.Options(leadGap, gapWidth), number));
        return this;
    }

    public Snippety number(TextIndentSpan.Options options, int number) {
        spans.add(new TextIndentSpan(options, number));
        return this;
    }

    public Snippety image(Drawable d) {
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        spans.add(new ImageSpan(d, ImageSpan.ALIGN_BASELINE));
        return this;
    }

    public enum Indent {
        LEFT, CENTER, RIGHT
    }

    public enum FontStyle {
        NORMAL, BOLD, ITALIC
    }

    public interface SpanListener {
        void onSpanClicked();
    }
}