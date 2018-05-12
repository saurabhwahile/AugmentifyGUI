package com.augmentify.GUIModule.Fragments;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;
/**
 * Created by Saurabh on 07/02/2015.
 */
public class FragmentViewFactoryResource
{
    public static Context context;

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

    public static class DialogMenuButton extends Commons
    {
        public static final String text = "Requests";
        public static final class Margins
        {
            public static final int left = (int) Utils.getPixelsFromDp(12, context);
            public static final int top = 0;
            public static final int right = 0;
            public static final int bottom = 0;
        }
    }
}
