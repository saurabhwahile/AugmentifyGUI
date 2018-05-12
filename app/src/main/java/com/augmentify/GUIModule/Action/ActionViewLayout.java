package com.augmentify.GUIModule.Action;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

import com.augmentify.Resource;

public class ActionViewLayout extends RelativeLayout
{
	Context context;
	public ActionViewLayout(Context context)
	{
		super(context);
		this.context = context;
	}
	
	public ActionView actionView;
	static LayoutParams params;
	
	public void build()
	{
		actionView = new ActionView(context);
		actionView.build();
		
		params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.addView(actionView, params);
		
		this.setBackgroundColor(Color.TRANSPARENT);
		this.setId(Resource.id++);
	}
}
