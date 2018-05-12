package com.augmentify.GUIModule.MainLayouts;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.widget.RelativeLayout;

import com.augmentify.GUIModule.Tabs.TabViewResource;
import com.augmentify.GUIModule.Tabs.TabsController;
import com.augmentify.Resource;

public class TabActionCardLayout extends RelativeLayout
{
	Context context;

	public TabActionCardLayout(Context context)
	{
		super(context);
		this.context = context;
	}

	static LayoutParams params;

	public TabsController tabsController;

	public void build()
	{
		tabsController = new TabsController(
				((FragmentActivity) context).getSupportFragmentManager(),
				(FragmentActivity) context);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(TabViewResource.Layout.Margin.left,
				TabViewResource.Layout.Margin.top,
				TabViewResource.Layout.Margin.right,
				TabViewResource.Layout.Margin.bottom);
		this.addView(tabsController.tabViewLayout, params);

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.BELOW,
				tabsController.tabViewLayout.getId());
		this.addView(tabsController.viewPager, params);


		this.setBackgroundColor(Color
				.parseColor(Resource.AugmentifyActivity.backgroundColor));
		this.setId(Resource.id++);
	}
}
