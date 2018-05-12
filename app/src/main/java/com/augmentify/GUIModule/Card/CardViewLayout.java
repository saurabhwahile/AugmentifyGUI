package com.augmentify.GUIModule.Card;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.GUIModule.Action.ActionView.Build;
import com.augmentify.GUIModule.Card.Content.Content.CardType;
import com.augmentify.GUIModule.Card.Fragments.FragmentController;
import com.augmentify.GUIModule.Utils.AnimationUtils;
import com.augmentify.R;
import com.augmentify.Resource;

public class CardViewLayout extends LinearLayout
{
	Context context;
	public Build cardLayoutType;
	public CardViewLayout(Context context)
	{
		super(context);
		this.context = context;

        CardViewResource.setContext(context);
        FragmentController.setActivity((Activity) context);

		this.setOrientation(LinearLayout.VERTICAL);
	}

	static LayoutParams params;

	public RelativeLayout blankSpaceAtStart;
	public PullToRefresh pullToRefresh;
	public LinearLayout cardViewCollection;

	public void build()
	{
		blankSpaceAtStart = new RelativeLayout(context);
		blankSpaceAtStart.setBackgroundColor(Color.TRANSPARENT);
		blankSpaceAtStart.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		this.addView(blankSpaceAtStart, params);

		pullToRefresh = new PullToRefresh(context);
		pullToRefresh.build();
		pullToRefresh.setBackgroundColor(Color.TRANSPARENT);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		this.addView(pullToRefresh, params);

		cardViewCollection = new LinearLayout(context);
		cardViewCollection.setId(Resource.id++);
		cardViewCollection.setOrientation(LinearLayout.VERTICAL);
		cardViewCollection.setBackgroundColor(Color.TRANSPARENT);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		this.addView(cardViewCollection, params);

		this.setMinimumHeight((int) Resource.screenHeight);
		this.setPadding(CardViewResource.Layout.Padding.left,
				CardViewResource.Layout.Padding.top,
				CardViewResource.Layout.Padding.right,
				CardViewResource.Layout.Padding.bottom);
		this.setId(Resource.id++);
	}

	public CardView addCard(CardType cardType)
	{
		CardView cardView = new CardView(context);
		cardView.build(cardType);
		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(CardViewResource.Margin.left,
				CardViewResource.Margin.top, CardViewResource.Margin.right,
				CardViewResource.Margin.bottom);
		cardViewCollection.addView(cardView, params);
        AnimationUtils.fadeIn(cardView);
		return cardView;
	}

	public class PullToRefresh extends RelativeLayout
	{
		public PullToRefresh(Context context)
		{
			super(context);
		}

		ProgressBar right;
		ProgressBar left;
		public ProgressBar main;
        TextView pullToRefreshText;
		ClipDrawable progressDrawable;
		AnimationDrawable progressIndeterminateDrawable;

		LayoutParams params;

		void build()
		{
			progressDrawable = new ClipDrawable(getResources().getDrawable(
					R.drawable.card_progress_secondary_holo_light),
					Gravity.CENTER, ClipDrawable.HORIZONTAL);

			progressIndeterminateDrawable = new AnimationDrawable();
			progressIndeterminateDrawable.setOneShot(false);
			buildIndterminateProgressBarAnimations();

            pullToRefreshText = new TextView(context);
            pullToRefreshText.setId(Resource.id++);
            pullToRefreshText.setText(CardViewResource.PullToRefresh.PullToRefreshText.text);
            pullToRefreshText.setTextColor(Color.parseColor(CardViewResource.PullToRefresh.PullToRefreshText.fontColor));
            pullToRefreshText.setTextSize(CardViewResource.PullToRefresh.PullToRefreshText.fontSize);
            pullToRefreshText.setGravity(Gravity.CENTER_HORIZONTAL);
            params = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            this.addView(pullToRefreshText, params);


			main = new ProgressBar(context, null,
					android.R.attr.progressBarStyleHorizontal);
			main.setId(Resource.id++);
			main.setIndeterminate(false);
			main.setMax(CardViewScrollView.PULL_TO_REFRESH_THRESHOLD);
			main.setProgress(10);
			main.setProgressDrawable(progressDrawable);
			main.setIndeterminateDrawable(progressIndeterminateDrawable);
			params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
            params.addRule(BELOW, pullToRefreshText.getId());
			this.addView(main, params);

			this.setId(Resource.id++);
		}

		public static final int FRAME_DURATION = 50;

		protected void buildIndterminateProgressBarAnimations()
		{
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo1),
					FRAME_DURATION);
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo2),
					FRAME_DURATION);
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo3),
					FRAME_DURATION);
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo4),
					FRAME_DURATION);
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo5),
					FRAME_DURATION);
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo6),
					FRAME_DURATION);
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo7),
					FRAME_DURATION);
			progressIndeterminateDrawable.addFrame(
					getResources().getDrawable(
							R.drawable.card_progressbar_indeterminate_holo8),
					FRAME_DURATION);
		}
	}
}