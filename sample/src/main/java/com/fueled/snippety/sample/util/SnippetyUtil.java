package com.fueled.snippety.sample.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.fueled.snippety.core.SnipettyAttr;
import com.fueled.snippety.core.Snippety;
import com.fueled.snippety.core.Truss;
import com.fueled.snippety.sample.R;
import com.fueled.snippety.sample.entity.TextItem;
import com.fueled.snippety.sample.entity.TextPage;


import java.util.List;

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 02/05/17
 */

public class SnippetyUtil {

	private SnippetyUtil() {
	}

	public static CharSequence getTrussText(Context context, TextPage page) {
		return getTrussText(getDefaultAttr(context), page);
	}

	public static CharSequence getTrussText(SnipettyAttr attr, TextPage page) {
		Truss truss = new Truss();
		if (!TextUtils.isEmpty(page.title)) {
			addTitle(attr, page.title, truss);
		}
		if (!TextUtils.isEmpty(page.subtitle)) {
			addSubTitle(attr, page.subtitle, truss);
		}
		if (page.body != null && page.body.size() > 0) {
			truss.append(getTrussText(attr.textColor, attr.leadGap, attr.gapWidth, page.body));
		}
		return truss.build();
	}

	public static CharSequence getTrussText(int textColor, int leadGap, int gapWidth,
	                                        List<TextItem> body) {
		Truss truss = new Truss();
		for (TextItem item : body) {
			if (item.isNumbers()) {
				addOrderedList(textColor, leadGap, gapWidth, truss, item);
			} else if (item.isBullets()) {
				addUnOrderedList(textColor, leadGap, gapWidth, truss, item);
			} else {
				truss.append(item.text).newParagraph();
			}
		}
		return truss.build();
	}

	private static void addTitle(SnipettyAttr attr, String text, Truss truss) {
		truss.pushSpan(new Snippety().textSizeAbsolute(attr.titleTextSize)
				.textColor(attr.titleTextColor)
				.typeface(attr.titleTextFont))
				.append(text)
				.popSpan()
				.newParagraph();
	}

	private static void addSubTitle(SnipettyAttr attr, String text, Truss truss) {
		truss.pushSpan(new Snippety().textSizeAbsolute(attr.subTitleTextSize)
				.textColor(attr.subTitleTextColor)
				.typeface(attr.subtitleTextFont))
				.append(text)
				.popSpan()
				.newParagraph();
	}

	private static void addText(SnipettyAttr attr, String text, Truss truss) {
		truss.pushSpan(new Snippety().textColor(attr.textColor))
				.append(text)
				.popSpan()
				.newParagraph();
	}

	public static void addOrderedList(int textColor, int leadGap, int gapWidth, Truss truss,
	                                  TextItem item) {
		for (int i = 0; i < item.list.size(); i++) {
			truss.pushSpan(new Snippety().number(leadGap, gapWidth, i + 1).textColor(textColor))
					.append(item.list.get(i))
					.popSpan()
					.newLine();
		}

		truss.newLine();
	}

	public static void addUnOrderedList(int textColor, int leadGap, int gapWidth, Truss truss,
	                                    TextItem item) {
		for (int i = 0; i < item.list.size(); i++) {
			truss.pushSpan(new Snippety().bullet(leadGap, gapWidth).textColor(textColor))
					.append(item.list.get(i))
					.popSpan()
					.newLine();
		}

		truss.newLine();
	}

	private static SnipettyAttr getDefaultAttr(Context context) {
		SnipettyAttr attr = new SnipettyAttr();

		Resources resources = context.getResources();
		attr.leadGap = resources.getDimensionPixelOffset(R.dimen.space_medium);
		attr.gapWidth = resources.getDimensionPixelOffset(R.dimen.space_xlarge);
		attr.textColor = ContextCompat.getColor(context, R.color.secondary_text);
		attr.titleTextSize = resources.getDimensionPixelOffset(R.dimen.text_xlarge);
		attr.subTitleTextSize = resources.getDimensionPixelOffset(R.dimen.text_medium);
		attr.titleTextColor = ContextCompat.getColor(context, R.color.primary_text);
		attr.subTitleTextColor = ContextCompat.getColor(context, R.color.primary_text);
		attr.titleTextFont = Typeface.createFromAsset(context.getAssets(),
				context.getString(R.string.font_roboto_bold));
		attr.subtitleTextFont = Typeface.createFromAsset(context.getAssets(),
				context.getString(R.string.font_roboto_medium));

		return attr;
	}
}
