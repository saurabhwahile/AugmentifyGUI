package com.augmentify.GUIModule.Tabs;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.augmentify.R;
import com.augmentify.Resource;

public class TabView extends RelativeLayout
{
	Context context;
	public TabView(Context context, Build buildState)
	{
		super(context);
		this.context = context;
		setBuildState(buildState);
	}

	public static enum Build
	{
		FRIENDS, EXPLORE, ME
	};

	static LayoutParams params;

	ImageView title;
	public void setBuildState(Build buildState)
	{
		title = new ImageView(context);
		switch (buildState)
		{
			case FRIENDS:
			{
				//title.setText("Friends");
				title.setImageResource(R.drawable.tab_friends);
			}
				break;
			case EXPLORE:
			{
				//title.setText("Explore");
				title.setImageResource(R.drawable.tab_explore);
			}
				break;
			case ME:
			{
				//title.setText("Me");
				title.setImageResource(R.drawable.tab_me);
			}
				break;

		}
	}

	public void build()
	{
		title.setId(Resource.id++);
		//title.setTextColor(Color.parseColor(TabViewResource.Title.fontColor));
		//title.setTextSize(TabViewResource.Title.fontSize);
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.height = TabViewResource.Title.Image.height;
		params.width = TabViewResource.Title.Image.width;
		params.setMargins(TabViewResource.Title.Margin.left,
				TabViewResource.Title.Margin.top,
				TabViewResource.Title.Margin.right,
				TabViewResource.Title.Margin.bottom);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		this.addView(title, params);
		
		this.setId(Resource.id++);
	}
}
