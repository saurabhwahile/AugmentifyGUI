package com.augmentify.GUIModule.Fragments.Register;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class RegisterViewResource
{
    static Context context;

    public static class Commons
    {
        public static final int fontSize = 12;
        public static final String fontColor = "#222222";
        public static final String hintFontColor = "#AAAAAA";
        public static final String hintErrorFontColor = "#330000";
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
        public static final String text = "Create Account";
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

    public static final class Name extends Commons
    {
        public static final String text = "Firstname Lastname";
    }

    public static final class Male extends Commons
    {
        public static final String text = "Male";
    }

    public static final class Female extends Commons
    {
        public static final String text = "Female";
    }

    public static final class Email extends Commons
    {
        public static final String text = "Email";
    }

    public static final class Username extends Commons
    {
        public static final String text = "Username";
    }

    public static final class Password extends Commons
    {
        public static final String text = "Password";
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
