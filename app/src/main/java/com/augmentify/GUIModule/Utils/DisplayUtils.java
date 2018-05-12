package com.augmentify.GUIModule.Utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.augmentify.R;

public class DisplayUtils
{
	public static class OnTouchButtonBackgroundSet implements OnTouchListener
	{
		@Override
		public boolean onTouch(View view, MotionEvent event)
		{
			if (event.getAction() == MotionEvent.ACTION_UP
					|| event.getAction() == MotionEvent.ACTION_CANCEL)
			{
				view.setBackgroundResource(R.drawable.button);
			}
			else
			{
				view.setBackgroundResource(R.drawable.button_pressed);
			}
			return false;
		}
	}
}
