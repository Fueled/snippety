package com.fueled.snippety.core;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.text.style.TypefaceSpan;

public class TextTypefaceSpan extends MetricAffectingSpan {

	private final Typeface typeface;

	public TextTypefaceSpan(Typeface typeface) {
		this.typeface = typeface;
	}

	@Override public void updateDrawState(TextPaint tp) {
		tp.setTypeface(typeface);
		tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
	}

	@Override public void updateMeasureState(TextPaint p) {
		p.setTypeface(typeface);
		p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
	}

}