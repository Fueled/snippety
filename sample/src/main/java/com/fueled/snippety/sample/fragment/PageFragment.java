package com.fueled.snippety.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fueled.snippety.sample.R;
import com.fueled.snippety.sample.client.PageClient;
import com.fueled.snippety.sample.entity.TextPage;
import com.fueled.snippety.sample.util.SnippetyUtil;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    private TextView textView;

    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance() {
        return new PageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        textView = (TextView) view.findViewById(R.id.text_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(getPageTrussText());
    }

    private CharSequence getPageTrussText() {
        PageClient pageClient = new PageClient(getActivity().getApplication(), new Gson());
        TextPage textPage = pageClient.getTextPage(R.raw.page_dummy);
        return SnippetyUtil.getTrussText(getContext(), textPage);
    }
}
