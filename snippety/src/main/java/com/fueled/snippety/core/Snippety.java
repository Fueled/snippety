package com.fueled.snippety.core;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.IconMarginSpan;
import android.text.style.ImageSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuggestionSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import com.fueled.snippety.span.CenteredImageSpan;
import com.fueled.snippety.span.HorizontalLineSpan;
import com.fueled.snippety.span.MultiColorSpan;
import com.fueled.snippety.span.RoundedBackgroundSpan;
import com.fueled.snippety.span.TextIndentSpan;
import com.fueled.snippety.span.TextTypefaceSpan;
import com.fueled.snippety.widget.LineDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Snippety is a wrapper class around different spans (and custom spans) from android.
 * Multiple properties can be applied to a single piece of text and can also be reused.
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 25/04/17
 */

public class Snippety {

    /**
     * List of multiple spans
     */
    private List<Object> spans;

    /**
     * default constructor for Snippety
     */
    public Snippety() {
        spans = new ArrayList<>();
    }

    /**
     * getter for spans object
     *
     * @return List<Object>
     */
    public List<Object> getSpans() {
        return spans;
    }

    /**
     * add color to text
     *
     * @param color of text
     * @return Snippety
     */
    public Snippety textColor(int color) {
        spans.add(new ForegroundColorSpan(color));
        return this;
    }

    /**
     * add multiple colors to text
     *
     * @param colors array of text
     * @return Snippety
     */
    public Snippety textMultiColor(int[] colors) {
        spans.add(new MultiColorSpan(colors));
        return this;
    }

    /**
     * add multi color to text with angle
     *
     * @param colors array of text
     * @param angle  of colors
     * @return Snippety
     */
    public Snippety textMultiColor(int[] colors, int angle) {
        spans.add(new MultiColorSpan(colors, angle));
        return this;
    }

    /**
     * add background color to text
     *
     * @param color of background
     * @return Snippety
     */
    public Snippety backgroundColor(int color) {
        spans.add(new BackgroundColorSpan(color));
        return this;
    }

    /**
     * add rounded background color to text
     *
     * @param backgroundColor of text
     * @param textColor       of text
     * @return Snippety
     */
    public Snippety roundedBackgroundColor(int backgroundColor, int textColor) {
        spans.add(new RoundedBackgroundSpan(backgroundColor, textColor));
        return this;
    }

    /**
     * add rounded background color to text with corner radius
     *
     * @param backgroundColor of text
     * @param textColor       of text
     * @param cornerRadius    of background
     * @return Snippety
     */
    public Snippety roundedBackgroundColor(int backgroundColor, int textColor, int cornerRadius) {
        spans.add(new RoundedBackgroundSpan(backgroundColor, textColor, cornerRadius));
        return this;
    }

    /**
     * add alignment for text
     *
     * @param indent LEFT, RIGHT, CENTER
     * @return Snippety
     */
    public Snippety align(Indent indent) {
        AlignmentSpan span;
        switch (indent) {
            case RIGHT:
                span = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE);
                break;
            case CENTER:
                span = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER);
                break;
            case LEFT:
            default:
                span = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL);
                break;
        }

        spans.add(span);

        return this;
    }

    /**
     * add underlined text
     *
     * @return Snippety
     */
    public Snippety underline() {
        spans.add(new UnderlineSpan());
        return this;
    }

    /**
     * add subscript for text
     *
     * @return Snippety
     */
    public Snippety subscript() {
        spans.add(new SubscriptSpan());
        return this;
    }

    /**
     * add superscript for text
     *
     * @return Snippety
     */
    public Snippety superscript() {
        spans.add(new SuperscriptSpan());
        return this;
    }

    /**
     * strikethrough text
     *
     * @return Snippety
     */
    public Snippety strikethrough() {
        spans.add(new StrikethroughSpan());
        return this;
    }

    /**
     * add url for text
     *
     * @param url of text
     * @return Snippety
     */
    public Snippety url(String url) {
        spans.add(new URLSpan(url));
        return this;
    }

    /**
     * quote for text
     *
     * @param color of quote line
     * @return Snippety
     */
    public Snippety quote(int color) {
        spans.add(new QuoteSpan(color));
        return this;
    }

    /**
     * add font style of text
     *
     * @param fontStyle BOLD, ITALIC, NORMAL
     * @return Snippety
     */
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

    /**
     * add typeface for text
     *
     * @param typeface for font
     * @return Snippety
     */
    public Snippety typeface(Typeface typeface) {
        spans.add(new TextTypefaceSpan(typeface));
        return this;
    }

    /**
     * add click listener to text
     *
     * @param onClickListener for text
     * @return Snippety
     */
    public Snippety addOnClickListener(final OnClickListener onClickListener) {
        // Make sure ClickableSpan is at the beginning of the list so other spans can be applied correctly.
        spans.add(0, new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickListener.onClick();
            }
        });
        return this;
    }

    /**
     * change relative text size
     *
     * @param proportion of text size
     * @return Snippety
     */
    public Snippety textSizeRelative(float proportion) {
        spans.add(new RelativeSizeSpan(proportion));
        return this;
    }

    /**
     * change absolute text size
     *
     * @param size of text
     * @return Snippety
     */
    public Snippety textSizeAbsolute(int size) {
        spans.add(new AbsoluteSizeSpan(size));
        return this;
    }

    /**
     * add suggestions to @link{{@link android.widget.EditText}}
     *
     * @param suggestions suggestions String array
     * @return Snippety
     */
    public Snippety suggestions(String... suggestions) {
        spans.add(new SuggestionSpan(Locale.getDefault(), suggestions, SuggestionSpan.FLAG_EASY_CORRECT));
        return this;
    }

    /**
     * add image bullets with specified padding
     *
     * @return Snippety
     */
    public Snippety bullet() {
        spans.add(new BulletSpan());
        return this;
    }

    /**
     * add bullets with specified leadGap and gapWidth
     *
     * @param leadGap  starting gap from left
     * @param gapWidth gap between bullet and text
     * @return Snippety
     */
    public Snippety bullet(int leadGap, int gapWidth) {
        spans.add(new TextIndentSpan(new TextIndentSpan.Options(leadGap, gapWidth)));
        return this;
    }

    /**
     * add custom bullets with specified leadGap and gapWidth
     *
     * @param leadGap  starting gap from left
     * @param gapWidth gap between bullet and text
     * @param data data to show on bullet
     * @return Snippety
     */
    public Snippety bullet(int leadGap, int gapWidth, String data) {
        spans.add(new TextIndentSpan(new TextIndentSpan.Options(leadGap, gapWidth), data));
        return this;
    }

    /**
     * add image bullets with specified padding
     *
     * @param bitmap  bitmap to add as bullet
     * @param padding padding between bullet and text
     * @return Snippety
     */
    public Snippety bullet(Bitmap bitmap, int padding) {
        spans.add(new IconMarginSpan(bitmap, padding));
        return this;
    }

    /**
     * add bullets with specified options
     *
     * @param options for leadGap and gapWidth
     * @return Snippety
     */
    public Snippety bullet(TextIndentSpan.Options options) {
        spans.add(new TextIndentSpan(options));
        return this;
    }

    /**
     * add bullets with specified options
     *
     * @param options for leadGap and gapWidth
     * @param data data to show on bullet
     * @return Snippety
     */
    public Snippety bullet(TextIndentSpan.Options options, String data) {
        spans.add(new TextIndentSpan(options, data));
        return this;
    }

    /**
     * add numbers with specified leadGap and gapWidth
     *
     * @param leadGap  starting gap from left
     * @param gapWidth gap between number and text
     * @param number   index in list, not 0 based
     * @return Snippety
     */
    public Snippety number(int leadGap, int gapWidth, int number) {
        spans.add(new TextIndentSpan(new TextIndentSpan.Options(leadGap, gapWidth), number));
        return this;
    }

    /**
     * add numbers with specified options
     *
     * @param options for leadGap and gapWidth
     * @param number  index in list, not 0 based
     * @return Snippety
     */
    public Snippety number(TextIndentSpan.Options options, int number) {
        spans.add(new TextIndentSpan(options, number));
        return this;
    }

    /**
     * add image drawable and align it to center
     *
     * @param drawable for image
     * @return Snippety
     */
    public Snippety image(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spans.add(new CenteredImageSpan(drawable, ImageSpan.ALIGN_BOTTOM));
        return this;
    }

    /**
     * add image drawable
     *
     * @param drawable for image
     * @return Snippety
     */
    public Snippety image(Drawable drawable, int verticalAlignment) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spans.add(new ImageSpan(drawable, verticalAlignment));
        return this;
    }

    /**
     * Horizontal line
     *
     * @return Snippety
     */
    public Snippety hr(float lineWidth, int lineColor) {
        spans.add(new HorizontalLineSpan(new LineDrawable(lineWidth, lineColor)));
        return this;
    }

    /**
     * Custom horiztontal line
     *
     * @param drawable line drawable
     * @return Snippety
     */
    public Snippety hr(Drawable drawable) {
        spans.add(new HorizontalLineSpan(drawable));
        return this;
    }

    /**
     * Indent options for text
     */
    public enum Indent {
        LEFT, CENTER, RIGHT
    }

    /**
     * FontStyle options for text
     */
    public enum FontStyle {
        NORMAL, BOLD, ITALIC
    }

    /**
     * click listener for text
     */
    public interface OnClickListener {
        void onClick();
    }
}