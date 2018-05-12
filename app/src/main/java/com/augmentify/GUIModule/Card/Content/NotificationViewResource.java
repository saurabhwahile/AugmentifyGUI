package com.augmentify.GUIModule.Card.Content;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

/**
 * Created by Saurabh on 06/02/2015.
 */
public class NotificationViewResource
{
    public static Context context;
    public static void setContext(Context context)
    {
        NotificationViewResource.context = context;
    }

    public static class Padding
    {
        public static final int left = (int) Utils.getPixelsFromDp(4, context);
        public static final int top = (int) Utils.getPixelsFromDp(4, context);
        public static final int right = (int) Utils.getPixelsFromDp(4, context);
        public static final int bottom = (int) Utils.getPixelsFromDp(4, context);
    }
}
