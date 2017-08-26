package com.fueled.snippety.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private int leadGap;
    private int gapWidth;

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
        textView.setText(getTrussForHtml());
    }

    private void initResources() {
        leadGap = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_medium);
        gapWidth = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_xlarge);
    }

    private CharSequence getTrussForHtml() {
        return new Truss()
                .appendln("Bullets List")
                .appendln("Bullet 1", new Snippety().bullet(leadGap, gapWidth))
                .appendln("Bullet 2", new Snippety().bullet(leadGap, gapWidth))
                .appendln()
                .appendln("Numbered List")
                .appendln("Number 1", new Snippety().number(leadGap, gapWidth, 1))
                .appendln("Number 2", new Snippety().number(leadGap, gapWidth, 2))
                .build();
    }
}
