package com.fueled.snippety.sample.fragment;

import android.graphics.Color;
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
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        textView = (TextView) view.findViewById(R.id.text_view);
        initResources();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(getDemoTrussText());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initResources() {
        typeface = Typeface.createFromAsset(getContext().getAssets(), getString(R.string.font_satisfy_regular));
        textSize = getResources().getDimensionPixelOffset(R.dimen.text_large);
        drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_android_green_24dp);
        colorsRainbow = getResources().getIntArray(R.array.rainbow);
    }

    private CharSequence getDemoTrussText() {
        return new Truss()

                .appendSelectiveln("With Snippety, you can use:", "Snippety",
                        new Snippety().textColor(Color.RED))
                .appendln()

                .pushSpan(new Snippety().typeface(typeface)) // TextTypefaceSpan
                .appendln("typeface for TypefaceSpan")
                .popSpan()

                .appendln("fontStyle for StyleSpan",
                        new Snippety().fontStyle(Snippety.FontStyle.BOLD))  //  StyleSpan

                .appendln("fontStyle for Stylespan",
                        new Snippety().fontStyle(Snippety.FontStyle.ITALIC))    //  StyleSpan

                .appendln("textColor for ForegroundColorSpan",
                        new Snippety().textColor(Color.MAGENTA))    //  ForegroundColorSpan

                .appendln("backgroundColor for BackgroundColorSpan",
                        new Snippety().backgroundColor(Color.YELLOW))   //  BackgroundColorSpan

                .appendln("roundedBackgroundColor\nfor RoundedBackgroundSpan",
                        new Snippety().roundedBackgroundColor(Color.RED, Color.WHITE))  //  RoundedBackgroundSpan

                .appendln("textSizeAbsolute for AbsoluteSizeSpan",
                        new Snippety().textSizeAbsolute(textSize))  //  AbsoluteSizeSpan

                .appendln("textSizeRelative for RelativeSizeSpan",
                        new Snippety().textSizeRelative(1.2f))    //  RelativeSizeSpan

                .appendln("textMultiColor for MultiColorSpan",
                        new Snippety().textMultiColor(colorsRainbow))   //  MultiColorSpan

                .appendln("underline for UnderlineSpan",
                        new Snippety().underline()) //  UnderlineSpan

                .append("image for ImageSpan")
                .appendln(new Snippety().image(drawable)) //  ImageSpan

                .appendln("quote for QuoteSpan",
                        new Snippety().quote(Color.RED))    //  QuoteSpan

                .appendln("strikethrough for StrikethroughSpan",
                        new Snippety().strikethrough()) //  StrikethroughSpan

                .appendln("align for AlignmentSpan",
                        new Snippety().align(Snippety.Indent.RIGHT))  //  AlignmentSpan

                .appendln("url for URLSpan",
                        new Snippety().url("http://developer.android.com")) //  URLSpan

                .appendln("addOnClickListener for ClickableSpan",
                        new Snippety().textColor(Color.BLUE).addOnClickListener(new Snippety.OnClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getContext(), "Thanks for stopping by!", Toast.LENGTH_SHORT).show();
                            }
                        })) //  ClickableSpan
                .build();
    }
}
