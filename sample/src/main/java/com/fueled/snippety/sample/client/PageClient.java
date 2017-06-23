package com.fueled.snippety.sample.client;

import android.app.Application;

import com.fueled.snippety.sample.entity.TextPage;
import com.google.gson.Gson;

import java.io.IOException;

import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * Created by hussein@fueled.com on 16/05/2017.
 * Copyright (c) 2017 Fueled. All rights reserved.
 */

public class PageClient {

    private final Application application;
    private final Gson gson;

    public PageClient(Application application, Gson gson) {
        this.application = application;
        this.gson = gson;
    }

    public TextPage getTextPage(int resourceId) {
        try {
            return gson.fromJson(openRawResource(resourceId), TextPage.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads the specified raw resource, decode it as UTF-8, and returns the string.
     *
     * @param resourceId the raw resource id for the page to be retrieved.
     * @return the raw resource as a UTF-8 string.
     * @throws IOException if an error occurs while reading the specified raw resource.
     */
    private String openRawResource(int resourceId) throws IOException {
        Source source = Okio.source(application.getResources().openRawResource(resourceId));
        BufferedSource buffer = Okio.buffer(source);
        try {
            return buffer.readUtf8();
        } finally {
            try {
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
