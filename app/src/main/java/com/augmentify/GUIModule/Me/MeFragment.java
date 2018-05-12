package com.augmentify.GUIModule.Me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.augmentify.GUIModule.Action.ActionView;
import com.augmentify.GUIModule.Action.ActionView.Build;
import com.augmentify.GUIModule.MainLayouts.ActionCardLayout;

public class MeFragment extends Fragment
{
	public ActionCardLayout meLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		meLayout = new ActionCardLayout(this.getActivity(), ActionView.Build.ME);
		meLayout.build();
		meLayout.cardViewScrollView.cardViewLayout.cardLayoutType = Build.ME;
		
		return meLayout;
	}
}
