package com.augmentify.GUIModule.Card.Fragments.Details.Content;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class EventViewResource
{
	public static Context context;
	
	
	public static class Commons
	{
		public static final class Image
		{
			public static final int width = (int) Utils.getPixelsFromDp(32, context);
			public static final int height = (int) Utils.getPixelsFromDp(32, context);
			public static final class Padding
			{
				public static final int left = 0;
				public static final int top = 0;
				public static final int right = 0;
				public static final int bottom = 0;
			}
		}
	}
	
	public static final class DisplayPicture
	{
		public static final int height = (int) Utils.getPixelsFromDp(128, context);
		public static final int width = (int) Utils.getPixelsFromDp(128, context);
		public static class Margin
		{
			public static final int left = (int) Utils.getPixelsFromDp(16, context);
			public static final int top = (int) Utils.getPixelsFromDp(16, context);
			public static final int right = 0;
			public static final int bottom = 0;
		}
		public static final class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(2, context);
			public static final int top = (int) Utils.getPixelsFromDp(2, context);
			public static final int right = (int) Utils.getPixelsFromDp(2, context);
			public static final int bottom = (int) Utils.getPixelsFromDp(2, context);
		}
	}
	
	public static class Join extends Commons
	{
		public static final String fontColor = "#444444";
		public static final float fontSize = 16;
		
		public static class Margin
		{
			public static final int left = (int) Utils.getPixelsFromDp(38 , context);
			public static final int top = (int) Utils.getPixelsFromDp(32, context);
			public static final int right = 0;
			public static final int bottom = 0;
		}
		
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(4, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
}
