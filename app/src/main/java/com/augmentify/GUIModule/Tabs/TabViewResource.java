package com.augmentify.GUIModule.Tabs;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class TabViewResource
{
	public static Context context;
	public static void setContext(Context context)
	{
		TabViewResource.context = context;
	}
	public static class Title
	{
		public static final String fontColor = "#000000";
		public static final int fontSize = 18;
		public static class Margin
		{
			public static final int left = 0;
			public static final int top = (int) Utils.getPixelsFromDp(12, context);
			public static final int right = 0;
			public static final int bottom = 0;
		}
		
		public static final class Image
		{
			public static final int width = (int) Utils.getPixelsFromDp(32, context);
			public static final int height = (int) Utils.getPixelsFromDp(32, context);
		}
	}
	
	public static final class Layout
	{
		public static final class Margin
		{
			public static final int left = (int) Utils.getPixelsFromDp(2, context);
			public static final int top = (int) Utils.getPixelsFromDp(-6, context);
			public static final int right = (int) Utils.getPixelsFromDp(2, context);
			public static final int bottom = 0;
		}
		
		public static final class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(4, context);
			public static final int top = (int) Utils.getPixelsFromDp(10, context);
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
}
