package com.fueled.snippety.sample.fragment;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class HtmlFragment extends Fragment {

    private int leadGap, gapWidth;
    private int colorAccent;

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
        leadGap = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_medium);
        gapWidth = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_xlarge);
        colorAccent = ContextCompat.getColor(getContext(), R.color.colorAccent);
    }

    private CharSequence getHtmlTrussText() {
        return new Truss()
                .appendSelectiveln("Hey! This is Snippety", "Snippety",
                        new Snippety().textColor(colorAccent))  //  ForegroundColorSpan
                .appendDelimiterizedln("You can also write `HTML`!", "`",
                        new Snippety().textColor(colorAccent))  //  ForegroundColorSpan
                .newParagraph()

                .pushSpan(new Snippety().fontStyle(Snippety.FontStyle.BOLD))    //  StyleSpan
                .appendln("Ordered List")
                .popSpan()

                .appendln("Number One",
                        new Snippety().number(leadGap, gapWidth, 1))    //  TextIndentSpan
                .appendln("Number Two",
                        new Snippety().number(leadGap, gapWidth, 2))
                .appendln("Number Three",
                        new Snippety().number(leadGap, gapWidth, 2))

                .newParagraph()
                .pushSpan(new Snippety().fontStyle(Snippety.FontStyle.BOLD))    //  StyleSpan
                .appendln("Unordered List")
                .popSpan()

                .appendln("Bullet One",
                        new Snippety().bullet(leadGap, gapWidth))   //  TextIndentSpan
                .appendln("Bullet Two",
                        new Snippety().bullet(leadGap, gapWidth))
                .appendln("Bullet Three",
                        new Snippety().bullet(leadGap, gapWidth))
                .appendln()
                .build();
    }
}
