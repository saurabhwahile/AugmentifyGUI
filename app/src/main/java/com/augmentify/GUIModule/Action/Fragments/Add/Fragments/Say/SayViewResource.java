package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Say;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class SayViewResource
{
	public static Context context;
	public static void setContext(Context context)
	{
		SayViewResource.context = context;
	}

	public static final String backgroundColor = "#AA000000";
	public static final float backgroundDimAmount = 0.5f;
	
	public static class Commons
	{
		public static final int fontSize = 12;
		public static final String fontColor = "#222222";
		public static final String hintFontColor = "#AAAAAA";
		public static final class Image
		{
			public static final int height = (int) Utils.getPixelsFromDp(16, context);
			public static final int width = (int) Utils.getPixelsFromDp(16, context);
		}
	}
	
	public static final class Title extends Commons
	{
		public static final String text = "Create Event";
		public static final int fontSize = 16;
		public static final String fontColor = "#444444";
		public static class Margin
		{
			public static final int left = 0;
			public static final int top = (int) Utils.getPixelsFromDp(4, context);
			public static final int right = 0;
			public static final int bottom = (int) Utils.getPixelsFromDp(4, context);
		}
	}
	
	public static final class HorizontalSeparator
	{
		public static final int height = (int) Utils.getPixelsFromDp(3, context);
	}
	
	public static final class Say extends Commons
	{
		public static final String text = "Update Status";
	}
	
	public static final class CreateSay extends Commons
	{
		public static final String text = "Say";
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
