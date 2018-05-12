package com.augmentify.GUIModule.Action.Fragments.Add;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class AddViewResource
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
	
	public static class Status extends Commons
	{
		public static final String text = "Status";
		public static final class Margins
		{
			public static final int left = 0;
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}

	public static class Event extends Commons
	{
		public static final String text = "Event";
		public static final class Margins
		{
			public static final int left = (int) Utils.getPixelsFromDp(12, context);
			public static final int top = 0;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}

	public static class Message extends Commons
	{
		public static final String text = "Message";
		public static final class Margins
		{
			public static final int left = 0;
			public static final int top = (int) Utils.getPixelsFromDp(12, context);
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}

	public static class Minimaps extends Commons
	{
		public static final String text = "Minimaps";
		public static final class Margins
		{
			public static final int left = (int) Utils.getPixelsFromDp(12, context);
			public static final int top = (int) Utils.getPixelsFromDp(12, context);
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}

	public static class Widget extends Commons
	{
		public static final String text = "Widget";
		public static final class Margins
		{
			public static final int left = (int) Utils.getPixelsFromDp(56, context);
			public static final int top = (int) Utils.getPixelsFromDp(12, context);
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
