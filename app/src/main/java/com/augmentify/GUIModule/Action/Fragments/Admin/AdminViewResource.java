package com.augmentify.GUIModule.Action.Fragments.Admin;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

/**
 * Created by Saurabh on 07/02/2015.
 */
public class AdminViewResource
{
    public static Context context;
    public static final String backgroundColor = "#AA000000";
    public static final float backgroundDimAmount = 0.5f;

    public static class Commons
    {
        public static final int fontSize = 12;
        public static final String fontColor = "#808080";
        public static final class Layout
        {
            public static final int height = (int) Utils
                    .getPixelsFromDp(96, context);
            public static final int width = (int) Utils
                    .getPixelsFromDp(96, context);
        }
    }

    public static class MyEvents extends Commons
    {
        public static final String text = "My Events";
        public static final class Margins
        {
            public static final int left = 0;
            public static final int top = 0;
            public static final int right = 0;
            public static final int bottom = 0;
        }
    }

    public static class JoinedEvents extends Commons
    {
        public static final String text = "Joined Events";
        public static final class Margins
        {
            public static final int left = (int) Utils.getPixelsFromDp(12, context);
            public static final int top = 0;
            public static final int right = 0;
            public static final int bottom = 0;
        }
    }

    public static final class Padding
    {
        public static final int left = (int) Utils.getPixelsFromDp(8, context);
        public static final int top = (int) Utils.getPixelsFromDp(8, context);
        public static final int right = (int) Utils.getPixelsFromDp(8, context);
        public static final int bottom = (int) Utils
                .getPixelsFromDp(8, context);
    }
}
