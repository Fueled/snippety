package com.fueled.snippety.sample.fragment;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fueled.snippety.core.Snippety;
import com.fueled.snippety.core.Truss;
import com.fueled.snippety.sample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {

    private Typeface typeface;
    private int colorAccent;
    private int colorBlack;
    private int leadGap;
    private int gapWidth;
    private int textSize;
    private Drawable drawable;
    private int[] colorsRainbow;

    private TextView textView;

    public DemoFragment() {
        // Required empty public constructor
    }

    public static DemoFragment newInstance() {
        return new DemoFragment();
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
        textView.setText(getTrussForDemo());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initResources() {
        typeface = Typeface.createFromAsset(getContext().getAssets(), getString(R.string.font_calligraffitti_regular));
        colorAccent = ContextCompat.getColor(getContext(), R.color.colorAccent);
        colorBlack = ContextCompat.getColor(getContext(), R.color.black);
        leadGap = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_medium);
        gapWidth = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_xlarge);
        textSize = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.text_xxlarge);
        drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_android_green_24dp);
        colorsRainbow = getResources().getIntArray(R.array.rainbow);
    }

    private CharSequence getTrussForDemo() {
        return new Truss()
                .appendln("Hello this is Snippety", new Snippety().fontStyle(Snippety.FontStyle.ITALIC))
                .appendln("I can have any Typeface", new Snippety().typeface(typeface))
                .appendln("You can underline", new Snippety().underline())
                .appendln("Add a URL", new Snippety().url("www.google.com"))
                .appendln("Strikethrough", new Snippety().strikethrough())
                .appendln("Quote", new Snippety().quote(colorAccent))
                .appendln("How about a fraction?")
                .append("2", new Snippety().superscript())
                .append("/")
                .appendln("5", new Snippety().subscript())
                .appendln("Background", new Snippety().backgroundColor(colorAccent))
                .appendln("Rounded Corners", new Snippety().roundedBackgroundColor(colorAccent, colorBlack, gapWidth))
                .appendln("Text Color", new Snippety().textColor(colorAccent))
                .appendln("Text Relative Size", new Snippety().textSize(2f))
                .appendln("Text Absolute size", new Snippety().testSizeAbsolute(textSize))
                .appendln(drawable.toString(), new Snippety().image(drawable)).appendln("Android")
                .appendln("Rainbow Color", new Snippety().textMultiColor(colorsRainbow))
                .appendln("Click Me!", new Snippety().addOnClickListener(new Snippety.OnClickListener() {
                    @Override
                    public void onClicked() {
                        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    }
                }))
                .build();

    }
}
