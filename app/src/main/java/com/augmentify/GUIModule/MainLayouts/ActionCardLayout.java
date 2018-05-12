package com.augmentify.GUIModule.MainLayouts;

import android.content.Context;
import android.widget.RelativeLayout;

import com.augmentify.GUIModule.Action.ActionView;
import com.augmentify.GUIModule.Action.ActionViewLayout;
import com.augmentify.GUIModule.Card.CardViewScrollView;
import com.augmentify.Resource;

public class ActionCardLayout extends RelativeLayout
{
	Context context;

	public ActionCardLayout(Context context, ActionView.Build buildId)
	{
		super(context);
		this.context = context;

		ActionCardLayoutResource.setContext(context);
		ActionView.setBuildState(buildId);
	}

	public CardViewScrollView cardViewScrollView;
	public ActionViewLayout actionViewLayout;

	static LayoutParams params;

	public void build()
	{
		actionViewLayout = new ActionViewLayout(context);
		actionViewLayout.build();


		cardViewScrollView = new CardViewScrollView(context)
		{
            /*
			int deltaChangeThreshold = 0;

			@Override
			protected void onScrollChanged(int l, int t, int oldl, int oldt)
			{
				super.onScrollChanged(l, t, oldl, oldt);

				// Pull To Refresh Jitter Fix
				if (t <= this.cardViewLayout.pullToRefresh.getMeasuredHeight())
				{
					return;
				}

				deltaChangeThreshold = deltaChangeThreshold + oldt - t;
				// Check Overfitting
				if (deltaChangeThreshold > 0)
				{
					deltaChangeThreshold = 0;
				}
				else if (deltaChangeThreshold < -actionViewLayout
						.getMeasuredHeight()
						+ (int) Utils.getPixelsFromDp(6, context))
				{
					deltaChangeThreshold = -actionViewLayout
							.getMeasuredHeight()
							+ (int) Utils.getPixelsFromDp(6, context);
				}
                actionViewLayout.scrollTo(0, -deltaChangeThreshold);
			}*/
		};
		cardViewScrollView.build();
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		this.addView(cardViewScrollView, params);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(
				ActionCardLayoutResource.ActionViewLayout.Margin.left,
				ActionCardLayoutResource.ActionViewLayout.Margin.top,
				ActionCardLayoutResource.ActionViewLayout.Margin.right,
				ActionCardLayoutResource.ActionViewLayout.Margin.bottom);
		this.addView(actionViewLayout, params);

		this.setId(Resource.id++);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		cardViewScrollView.cardViewLayout.blankSpaceAtStart.getLayoutParams().height = actionViewLayout
				.getMeasuredHeight();

		setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
	}

}
