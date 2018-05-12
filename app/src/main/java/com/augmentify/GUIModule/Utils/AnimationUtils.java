package com.augmentify.GUIModule.Utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.augmentify.Resource;

public class AnimationUtils
{
	static AlphaAnimation fadeIn;
	static AlphaAnimation fadeOut;
	static TranslateAnimation cardTranslateAnimation;
	static ScaleAnimation cardScaleAnimation;
	static AnimationSet cardAnimationSet;

	static View view;

	public static void initializeAnimations()
	{
		cardTranslateAnimation = new TranslateAnimation(Resource.screenWidth,
				0, 0, 0);
		cardScaleAnimation = new ScaleAnimation(1, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.0f);
		
		cardTranslateAnimation.setDuration(500);
		cardScaleAnimation.setDuration(500);
		
		cardAnimationSet = new AnimationSet(false);
		cardAnimationSet.addAnimation(cardTranslateAnimation);
		cardAnimationSet.addAnimation(cardScaleAnimation);

		fadeIn = new AlphaAnimation(0.0f, 1.0f);
		fadeIn.setDuration(500);
		fadeIn.setAnimationListener(new AnimationListener()
		{

			@Override
			public void onAnimationEnd(Animation animation)
			{
				view.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{

			}

			@Override
			public void onAnimationStart(Animation animation)
			{

			}

		});

		fadeOut = new AlphaAnimation(1.0f, 0.0f);
		fadeOut.setDuration(100);
		fadeOut.setAnimationListener(new AnimationListener()
		{

			@Override
			public void onAnimationEnd(Animation animation)
			{
				view.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{

			}

			@Override
			public void onAnimationStart(Animation animation)
			{

			}

		});

	}

	public static void fadeIn(View view)
	{
		AnimationUtils.view = view;
		view.startAnimation(fadeIn);
	}

	public static void fadeOut(View view)
	{
		AnimationUtils.view = view;
		view.startAnimation(fadeOut);
	}
	public static void animateCardEntry(View card)
	{
		card.startAnimation(cardAnimationSet);
	}
}
