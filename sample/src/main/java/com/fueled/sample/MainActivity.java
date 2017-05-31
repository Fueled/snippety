package com.fueled.sample;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fueled.snippety.client.PageClient;
import com.fueled.snippety.core.Snippety;
import com.fueled.snippety.core.SnippetyUtil;
import com.fueled.snippety.core.Truss;
import com.fueled.snippety.entity.TextPage;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.snippety_text_view);

		initText();
	}

	private void initText() {
		PageClient pageClient = new PageClient(getApplication(), new Gson());
		TextPage textPage = pageClient.getTextPage(R.raw.page_user_agreement);
		CharSequence charSequence = SnippetyUtil.getTrussText(this, textPage);
		textView.setText(charSequence);
	}

	private void initImage() {
		Drawable d = ContextCompat.getDrawable(this, R.drawable.ic_content_copy_black_24dp);
		Truss truss = new Truss()
				.pushSpan(new Snippety().image(d))
				.append(d.toString())
				.popSpan()
				.append("Hello");
		textView.setText(truss.build());
	}
}
