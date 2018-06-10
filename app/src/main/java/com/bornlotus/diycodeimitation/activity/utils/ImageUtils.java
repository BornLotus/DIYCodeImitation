package com.bornlotus.diycodeimitation.activity.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {

    public static void loadImage(Context context, String url, ImageView imageView) {
        String url2 = url;
        if (url2.contains("diycode"))
            url2 = url.replace("large_avatar", "avatar");
        Glide.with(context).load(url2).into(imageView);
    }

}
