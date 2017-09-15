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
        typeface = Typeface.createFromAsset(getContext().getAssets(), getString(R.string.font_sunshiney));
        textSize = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.text_xlarge);
        drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_android_green_24dp);
        colorsRainbow = getResources().getIntArray(R.array.rainbow);
    }

    private CharSequence getDemoTrussText() {
        return new Truss()

                .pushSpan(new Snippety().typeface(typeface).testSizeAbsolute(textSize)) // TextTypefaceSpan
                .appendSelectiveln("Hi! I'm Sunshine, the typeface", "Sunshine",
                        new Snippety().textColor(Color.RED))    //  ForegroundColorSpan
                .popSpan()

                .appendln("I'm bold and straight forward",
                        new Snippety().fontStyle(Snippety.FontStyle.BOLD))  //  StyleSpan

                .appendln("I love Italian food",
                        new Snippety().fontStyle(Snippety.FontStyle.ITALIC))    //  StyleSpan

                .appendln("Italian, Mexican and Chinese!",
                        new Snippety().roundedBackgroundColor(Color.RED, Color.WHITE))  //  RoundedBackgroundSpan

                .appendln("Mom says I'm a big girl",
                        new Snippety().textSizeRelative(2f))    //  RelativeSizeSpan

                .appendln("And I should marry soon",
                        new Snippety().testSizeAbsolute(textSize))  //  AbsoluteSizeSpan

                .appendln("But I'm just 16sp",
                        new Snippety().textColor(Color.MAGENTA))    //  ForegroundColorSpan

                .appendln("I think I'm young and beautiful",
                        new Snippety().textMultiColor(colorsRainbow))   //  MultiColorSpan

                .appendln("My career is just as important",
                        new Snippety().underline()) //  UnderlineSpan

                .append("I'm an Android Developer ")

                .appendln(drawable.toString(),
                        new Snippety().image(drawable)) //  ImageSpan

                .appendln("I did have a boy friend",
                        new Snippety().quote(Color.RED))    //  QuoteSpan

                .appendln("But we broke up one day",
                        new Snippety().strikethrough()) //  StrikethroughSpan

                .appendln("That's how life is, you can't always be",
                        new Snippety().backgroundColor(Color.YELLOW))   //  BackgroundColorSpan

                .appendln("right..",
                        new Snippety().align(Snippety.Indent.RIGHT).backgroundColor(Color.YELLOW))  //  AlignmentSpan

                .appendln("I spend most of my time at work now",
                        new Snippety().url("http://developer.android.com")) //  URLSpan

                .appendln("But I'm hopeful 'cause I know",
                        new Snippety().subscript()) //  SubscriptSpan

                .appendln("There's sunshine behind that rain",
                        new Snippety().textColor(Color.BLUE).addOnClickListener(new Snippety.OnClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getContext(), "Thanks for stopping by!", Toast.LENGTH_SHORT).show();
                            }
                        })) //  ClickableSpan
                .build();
    }
}
