package com.augmentify.GUIModule.Fragments.Login;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class LoginViewResource
{
    public static Context context;
    public static void setContext(Context context)
    {
        LoginViewResource.context = context;
    }

    public static class Commons
    {
        public static final int fontSize = 12;
        public static final String fontColor = "#222222";
        public static final String hintFontColor = "#AAAAAA";
        public static final String hintErrorFontColor = "#AA0000";
        public static final class Image
        {
            public static final int height = (int) Utils.getPixelsFromDp(16, context);
            public static final int width = (int) Utils.getPixelsFromDp(16, context);
        }
    }

    public static class Title
    {
        public static final String fontColor = "#000000";
        public static final float fontSize = 24;
        public static final String text = "Login";
        public static class Padding
        {
            public static final int left = 0;
            public static final int top = 0;
            public static final int right = 0;
            public static final int bottom = 0;
        }
    }

    public static final class HorizontalSeparator
    {
        public static final int height = (int) Utils.getPixelsFromDp(3, context);
    }

    public static final class Username extends Commons
    {
        public static final String text = "Username";
    }

    public static final class Password extends Commons
    {
        public static final String text = "Password";
    }

    public static final class Login extends Commons
    {
        public static final int fontSize = 20;
        public static final String text = "Login";
    }

    public static final class Register extends Commons
    {
        public static final int fontSize = 18;
        public static final String text = "Register";
    }

    public static final class Fragment
    {
        public static final float backgroundDimAmount = 0.5f;
    }
}
