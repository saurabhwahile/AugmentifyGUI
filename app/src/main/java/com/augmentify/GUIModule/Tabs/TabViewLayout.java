package com.augmentify.GUIModule.Tabs;

import android.content.Context;
import android.widget.RelativeLayout;

import com.augmentify.GUIModule.Utils.Utils;
import com.augmentify.R;
import com.augmentify.Resource;

public class TabViewLayout extends RelativeLayout
{
	Context context;

	public TabViewLayout(Context context)
	{
		super(context);
		this.context = context;
		TabViewResource.setContext(context);
	}

	static LayoutParams params;

	TabView friendsTabView;
	TabView exploreTabView;
	TabView meTabView;
	RelativeLayout tabIndicator;

	public void build()
	{
		tabIndicator = new RelativeLayout(context);
		tabIndicator.setBackgroundResource(R.drawable.button);
		tabIndicator.setId(Resource.id++);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = TabViewResource.Title.Image.height + (int) Utils.getPixelsFromDp(12, context);
		this.addView(tabIndicator, params);

		friendsTabView = new TabView(context, TabView.Build.FRIENDS);
		friendsTabView.build();
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		this.addView(friendsTabView, params);

		exploreTabView = new TabView(context, TabView.Build.EXPLORE);
		exploreTabView.build();
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		this.addView(exploreTabView, params);

		meTabView = new TabView(context, TabView.Build.ME);
		meTabView.build();
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		this.addView(meTabView, params);

		this.setId(Resource.id++);
		this.setBackgroundResource(R.drawable.action_bar_background_top);
		this.setPadding(TabViewResource.Layout.Padding.left,
				TabViewResource.Layout.Padding.top,
				TabViewResource.Layout.Padding.right,
				TabViewResource.Layout.Padding.bottom);

	}

	public int tabWidth = 0;

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		tabWidth = (int) ((this.getMeasuredWidth()-Utils.getPixelsFromDp(6, context)) / 3);

		tabIndicator.getLayoutParams().width = tabWidth;

		friendsTabView.getLayoutParams().width = tabWidth;
		exploreTabView.getLayoutParams().width = tabWidth;
		meTabView.getLayoutParams().width = tabWidth;

		setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
	}
}
