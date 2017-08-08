package com.fueled.snippety.sample;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fueled.snippety.sample.client.PageClient;
import com.fueled.snippety.core.Snippety;
import com.fueled.snippety.sample.util.SnippetyUtil;
import com.fueled.snippety.core.Truss;
import com.fueled.snippety.sample.entity.TextPage;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private Typeface typeface;
    private int colorAccent;
    private int colorBlack;
    private int leadGap;
    private int gapWidth;
    private int textSize;
    private Drawable drawable;
    private int[] colorsRainbow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResources();
        textView = (TextView) findViewById(R.id.snippety_text_view);

        initTruss();
    }

    private void initTruss() {
        Truss t = new Truss().appendSelective("You are Hussein. You are the best.", "are", new Snippety().textColor(colorAccent));
        textView.setText(t.build());
    }

    private void initResources() {
        typeface = Typeface.createFromAsset(getAssets(), getString(R.string.font_calligraffitti_regular));
        colorAccent = ContextCompat.getColor(this, R.color.colorAccent);
        colorBlack = ContextCompat.getColor(this, R.color.black);
        leadGap = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_medium);
        gapWidth = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.space_xlarge);
        textSize = getResources().getDimensionPixelOffset(com.fueled.snippety.R.dimen.text_xxlarge);
        drawable = ContextCompat.getDrawable(this, R.drawable.ic_android_green_24dp);
        colorsRainbow = getResources().getIntArray(R.array.rainbow);
    }

    private void initTrussDemo() {
        Truss html = new Truss()
                .appendln("Bullets List")
                .appendln("Bullet 1", new Snippety().bullet(leadGap, gapWidth))
                .appendln("Bullet 2", new Snippety().bullet(leadGap, gapWidth))
                .appendln("Numbered List")
                .appendln("Number 1", new Snippety().number(leadGap, gapWidth, 1))
                .appendln("Number 2", new Snippety().number(leadGap, gapWidth, 2));

        Truss truss = new Truss()
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
                .appendln("Rounded Corners", new Snippety().roundedBackgroundColor(colorAccent, colorBlack, leadGap))
                .appendln("Text Color", new Snippety().textColor(colorAccent))
                .appendln("Text Relative Size", new Snippety().textSize(2f))
                .appendln("Text Absolute size", new Snippety().testSizeAbsolute(textSize))
                .appendln(drawable.toString(), new Snippety().image(drawable)).appendln("Android")
                .appendln("Rainbow Color", new Snippety().textMultiColor(colorsRainbow))
                .appendln("Clickable", new Snippety().addListener(new Snippety.SpanListener() {
                    @Override
                    public void onSpanClicked() {
                        Toast.makeText(MainActivity.this, "Span Clicked", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void initText() {
        PageClient pageClient = new PageClient(getApplication(), new Gson());
        TextPage textPage = pageClient.getTextPage(R.raw.page_dummy);
        CharSequence charSequence = SnippetyUtil.getTrussText(this, textPage);
        textView.setText(charSequence);
    }
}
