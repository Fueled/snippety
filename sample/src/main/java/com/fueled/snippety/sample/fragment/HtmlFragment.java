package com.fueled.snippety.sample.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fueled.snippety.core.Snippety;
import com.fueled.snippety.core.Truss;
import com.fueled.snippety.sample.R;
import com.fueled.snippety.sample.util.ViewUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HtmlFragment extends Fragment {

    private int leadWidth, gapWidth, oneDp;
    private int colorAccent, colorGrey;

    private TextView textView;

    public HtmlFragment() {
        // Required empty public constructor
    }

    public static HtmlFragment newInstance() {
        return new HtmlFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_html, container, false);
        textView = (TextView) view.findViewById(R.id.text_view);
        initResources();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(getHtmlTrussText());
    }

    private void initResources() {
        leadWidth = getResources().getDimensionPixelOffset(R.dimen.space_medium);
        gapWidth = getResources().getDimensionPixelOffset(R.dimen.space_xlarge);
        oneDp = getResources().getDimensionPixelOffset(R.dimen.one_dp);
        colorAccent = ContextCompat.getColor(getContext(), R.color.colorAccent);
        colorGrey = ContextCompat.getColor(getContext(), R.color.grey_light);
    }

    private CharSequence getHtmlTrussText() {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_star_black_24dp);
        Bitmap bitmap = ViewUtil.getBitmap(drawable);

        return new Truss()

                .appendSelective("Hey! This is Snippety. ", "Snippety",
                        new Snippety().textColor(colorAccent))  //  ForegroundColorSpan
                .appendDelimiterizedln("You can also write `HTML`!", "`",
                        new Snippety().textColor(colorAccent))  //  ForegroundColorSpan
                .appendln()

                // Ordered List

                .appendln("Ordered List", new Snippety().fontStyle(Snippety.FontStyle.BOLD))
                .appendln(new Snippety().hr(oneDp, colorGrey))

                .appendln("Number One", new Snippety().number(leadWidth, gapWidth, 1))    //  TextIndentSpan
                .appendln("Number Two", new Snippety().number(leadWidth, gapWidth, 2))
                .appendln()

                // Unordered List

                .appendln("Unordered List", new Snippety().fontStyle(Snippety.FontStyle.BOLD))
                .appendln(new Snippety().hr(oneDp, colorGrey))

                .appendln("Bullet One", new Snippety().bullet(leadWidth, gapWidth))               //  TextIndentSpan
                .appendln("Bullet Two", new Snippety().bullet(leadWidth, gapWidth))
                .appendln()

                // Custom Unordered List

                .appendln("Custom Unordered List", new Snippety().fontStyle(Snippety.FontStyle.BOLD))
                .appendln(new Snippety().hr(oneDp, colorGrey))

                .appendln("Custom Bullet One", new Snippety().bullet(leadWidth, gapWidth, "I."))     //  TextIndentSpan
                .appendln("Custom Bullet Two", new Snippety().bullet(leadWidth, gapWidth, "II."))
                .appendln("Custom Bullet Three", new Snippety().bullet(leadWidth, gapWidth, "III."))
                .appendln()

                // Image Unordered List

                .appendln("Image Unordered List", new Snippety().fontStyle(Snippety.FontStyle.BOLD))
                .appendln(new Snippety().hr(oneDp, colorGrey))

                .appendln("Image Bullet One", new Snippety().bullet(bitmap, leadWidth))           //  IconMarginSpan
                .appendln("Image Bullet Two", new Snippety().bullet(bitmap, leadWidth))
                .appendln("Image Bullet Three", new Snippety().bullet(bitmap, leadWidth))
                .appendln()

                .build();
    }
}
