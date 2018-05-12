package com.augmentify.GUIModule.Friends;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.augmentify.GUIModule.Action.ActionView;
import com.augmentify.GUIModule.Action.ActionView.Build;
import com.augmentify.GUIModule.MainLayouts.ActionCardLayout;

public class FriendsFragment extends Fragment
{
	public ActionCardLayout friendsLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		friendsLayout = new ActionCardLayout(this.getActivity(), ActionView.Build.FRIENDS);
		friendsLayout.build();
		friendsLayout.cardViewScrollView.cardViewLayout.cardLayoutType = Build.FRIENDS;
		
		return friendsLayout;
	}
}
