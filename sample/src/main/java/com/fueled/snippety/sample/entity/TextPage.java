package com.fueled.snippety.sample.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright (c) 2017 Fueled. All rights reserved.
 *
 * @author chetansachdeva on 02/05/17
 */

public class TextPage {

    @SerializedName("title") public String title;
    @SerializedName("subtitle") public String subtitle;
    @SerializedName("body") public List<TextItem> body;

}
