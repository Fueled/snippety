package com.fueled.snippety.span;

import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

/**
 * Add multiple colors to text with specified angle.
 *
 * source: @link{http://chiuki.github.io/advanced-android-textview/}
 */

public class MultiColorSpan extends CharacterStyle implements UpdateAppearance {

    private final int[] colors;
    private int angle;

    public MultiColorSpan(int[] colors, int angle) {
        this.colors = colors;
        this.angle = angle;
    }

    public MultiColorSpan(int[] colors) {
        this(colors, 90);
    }

    @Override
    public void updateDrawState(TextPaint paint) {
        paint.setStyle(Paint.Style.FILL);
        Shader shader = new LinearGradient(0, 0, 0, paint.getTextSize() * colors.length, colors, null,
                Shader.TileMode.MIRROR);
        Matrix matrix = new Matrix();
        matrix.setRotate(angle);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);
    }
}