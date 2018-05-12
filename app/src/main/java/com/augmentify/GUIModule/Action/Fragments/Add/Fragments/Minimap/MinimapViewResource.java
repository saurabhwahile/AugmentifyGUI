package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Minimap;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class MinimapViewResource
{
	static Context context;
	
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
		public static final String text = "Create Minimap";
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
	
	public static final class GetImage extends Commons
	{
		public static final String text = "Select Image";
	}
	
	public static final class Left extends Commons
	{
		public static final String text = "Left";
	}
	
	public static final class Forward extends Commons
	{
		public static final String text = "Forward";
	}
	
	public static final class Right extends Commons
	{
		public static final String text = "Right";
	}
	
	public static final class Backward extends Commons
	{
		public static final String text = "Back";
	}
	
	public static final class Previous extends Commons
	{
		public static final String text = "Previous";
	}
	
	public static final class Next extends Commons
	{
		public static final String text = "Next";
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
