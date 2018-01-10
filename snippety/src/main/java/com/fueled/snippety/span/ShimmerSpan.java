package com.fueled.snippety.span;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.Property;

/**
 * Imparts shimmer effect to the text.
 *
 * source: @link{https://www.youtube.com/watch?v=G55mJvel7iI}
 */

public class ShimmerSpan extends CharacterStyle implements UpdateAppearance {

    private final int[] colors;
    private Shader shader = null;
    private Matrix matrix = new Matrix();
    private float translateXPercentage = 0;

    public ShimmerSpan() {
        colors = new int[3];
        colors[0] = Color.WHITE;
        colors[1] = Color.LTGRAY;
        colors[2] = Color.DKGRAY;
    }

    public void setTranslateXPercentage(float percentage) {
        translateXPercentage = percentage;
    }

    public float getTranslateXPercentage() {
        return translateXPercentage;
    }

    @Override
    public void updateDrawState(TextPaint paint) {
        paint.setStyle(Paint.Style.FILL);
        float width = paint.getTextSize() * colors.length;
        if (shader == null) {
            shader = new LinearGradient(0, 0, 0, width, colors, null, Shader.TileMode.MIRROR);
        }
        matrix.reset();
        matrix.setRotate(90);
        matrix.postTranslate(width * translateXPercentage, 0);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);
    }
}