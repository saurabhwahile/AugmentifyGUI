package com.augmentify.GUIModule.Card.Fragments.Comments;

import android.content.Context;

import com.augmentify.GUIModule.Utils.Utils;

public class CommentsViewResource
{
	public static Context context;
	public static void setContext(Context context)
	{
		CommentsViewResource.context = context;
	}
	
	public static final class User
	{
		public static final int fontSize = 12;
		public static final String fontColor = "#EEEEEE";
		
		public static final class Image
		{
			public static final int height = (int) Utils.getPixelsFromDp(32, context);
			public static final int width = (int) Utils.getPixelsFromDp(32, context);
		}
	}
	
	public static final class Comment
	{
		public static final int fontSize = 10;
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(2, context);
			public static final int top = (int) Utils.getPixelsFromDp(2, context);;
			public static final int right = 0;
			public static final int bottom = 0;
		}
	}
	
	public static final class Layout
	{
		public static final int height = (int) Utils.getPixelsFromDp(320, context);
		
		public static class Padding
		{
			public static final int left = (int) Utils.getPixelsFromDp(2, context);
			public static final int top = (int) Utils.getPixelsFromDp(4, context);
			public static final int right = 0;
			public static final int bottom = 0;
		}
		
		public static final class Title
		{
			public static final int fontSize = 16;
			
			public static final class Image
			{
				public static final int height = (int) Utils.getPixelsFromDp(32, context);
				public static final int width = (int) Utils.getPixelsFromDp(32, context);
			}
			
			public static class Padding
			{
				public static final int left = (int) Utils.getPixelsFromDp(4, context);
				public static final int top = (int) Utils.getPixelsFromDp(4, context);
				public static final int right = 0;
				public static final int bottom = (int) Utils.getPixelsFromDp(4, context);;
			}
		}
		
		public static final class HorizontalSeparator
		{
			public static final int height = (int) Utils.getPixelsFromDp(3, context);
		}
		
		public static final class AddComment
		{
			public static final class Image
			{
				public static final int height = (int) Utils.getPixelsFromDp(32, context);
				public static final int width = (int) Utils.getPixelsFromDp(32, context);
			}
			
			public static class Padding
			{
				public static final int left = 0;
				public static final int top = (int) Utils.getPixelsFromDp(4, context);
				public static final int right = 0;
				public static final int bottom = 0;
			}
		}
	}
	
	public static final class Fragment
	{
		public static final float backgroundDimAmount = 0.5f;
	}
}
