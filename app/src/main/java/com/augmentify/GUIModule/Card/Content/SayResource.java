package com.augmentify.GUIModule.Card.Content;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class SayResource
{
	public static Context context;
	public static void setContext(Context context)
	{
		SayResource.context = context;
	}
	
	public static class Say
	{
		public static final String fontColor = "#444444";
		public static final float fontSize = 12;
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(6, context);
			public static final int top = (int) Utils.getPixelsFromDp(6, context);
			public static final int right = 0;
			public static final int bottom = (int) Utils.getPixelsFromDp(6, context);
		}
	}
	
}
