package com.augmentify.GUIModule.Card;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ScrollView;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.GUIModule.Utils.Utils;
import com.augmentify.Resource;

public class CardViewScrollView extends ScrollView
{
	Context context;

	public CardViewScrollView(Context context)
	{
		super(context);
		this.context = context;
		PULL_TO_REFRESH_THRESHOLD = (int) Utils.getPixelsFromDp(50, context);
		PULL_TO_REFRESH_INCREMENT = (int) Utils.getPixelsFromDp(2, context);
	}

	public CardViewLayout cardViewLayout;

	public void build()
	{
		initializeAnimations();

		cardViewLayout = new CardViewLayout(context);
		cardViewLayout.build();
		this.addView(cardViewLayout);

		this.setFillViewport(true);
		this.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		this.setVerticalScrollBarEnabled(false);
		this.setId(Resource.id++);
	}

	public static int PULL_TO_REFRESH_THRESHOLD;
	public static int PULL_TO_REFRESH_INCREMENT;
	int deltaThreshold = 0;
	int oldY = 0;
	boolean isRefreshing = false;
	boolean isRefreshProgressViewLocked = false;

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (!isRefreshing && !isRefreshProgressViewLocked)
		{
			// User Scrolled To End And Reached Threshold, Start Refresh
			if (deltaThreshold >= PULL_TO_REFRESH_THRESHOLD)
			{
				cardViewLayout.pullToRefresh.startAnimation(fadeIn);
				cardViewLayout.pullToRefresh.main.setIndeterminate(true);
				onRefreshStart();
				isRefreshing = true;
				isRefreshProgressViewLocked = true;
			}
			else // User Scrolled To End, Is Try To Reach Threshold
			if ((this.getScrollY() == 0)
					&& (event.getActionMasked() == MotionEvent.ACTION_MOVE)
					&& ((event.getY() - oldY) > 0))
			{
				cardViewLayout.pullToRefresh.setVisibility(View.VISIBLE);
				cardViewLayout.pullToRefresh.main.setIndeterminate(false);

				deltaThreshold += PULL_TO_REFRESH_INCREMENT;
				oldY = (int) event.getY();
			}
		}

		if (event.getActionMasked() == MotionEvent.ACTION_UP
				|| event.getActionMasked() == MotionEvent.ACTION_CANCEL)
		{
			deltaThreshold = 0;
			oldY = 0;
			isRefreshProgressViewLocked = false;

			if (!isRefreshing
					&& (this.getScrollY() < this.cardViewLayout.pullToRefresh
							.getMeasuredHeight()))
			{
				new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        smoothScrollTo(0, cardViewLayout.pullToRefresh
                                .getMeasuredHeight());
                    }

                }, 100L);
			}
		}

		this.cardViewLayout.pullToRefresh.main.setProgress(deltaThreshold);

		return super.onTouchEvent(event);
	}

	public void onRefreshStart()
	{
		switch (cardViewLayout.cardLayoutType)
		{
			case EXPLORE:
			{
				Controller.buildExplore();
			}
				break;
			case FRIENDS:
			{
				Controller.buildFriends();
			}
				break;
			case ME:
			{
				Controller.buildMe();
			}
				break;
		}
	}

	public void onRefreshOver()
	{
		cardViewLayout.pullToRefresh.startAnimation(fadeOut);
		if (this.getScrollY() < this.cardViewLayout.pullToRefresh
				.getMeasuredHeight())
		{
			if(!isRefreshProgressViewLocked)
			{
				scrollTo(0,
						cardViewLayout.pullToRefresh.getMeasuredHeight());
			}
		}
		isRefreshing = false;
	}

	AlphaAnimation fadeIn, fadeOut;

	public void initializeAnimations()
	{
		fadeIn = new AlphaAnimation(0.0f, 1.0f);
		fadeIn.setDuration(1000);
		fadeIn.setAnimationListener(new Animation.AnimationListener()
		{

			@Override
			public void onAnimationEnd(Animation animation)
			{
				cardViewLayout.pullToRefresh.setVisibility(View.VISIBLE);
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
		fadeOut.setDuration(1000);
		fadeOut.setAnimationListener(new Animation.AnimationListener()
		{

			@Override
			public void onAnimationEnd(Animation animation)
			{
				cardViewLayout.pullToRefresh.setVisibility(View.INVISIBLE);
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
}
