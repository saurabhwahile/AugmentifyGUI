package com.augmentify.GUIModule.Explore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.augmentify.GUIModule.Action.ActionView;
import com.augmentify.GUIModule.Action.ActionView.Build;
import com.augmentify.GUIModule.MainLayouts.ActionCardLayout;

public class ExploreFragment extends Fragment
{
	public ActionCardLayout exploreLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		exploreLayout = new ActionCardLayout(this.getActivity(), ActionView.Build.EXPLORE);
		exploreLayout.build();
		exploreLayout.cardViewScrollView.cardViewLayout.cardLayoutType = Build.EXPLORE;

		return exploreLayout;
	}
}
