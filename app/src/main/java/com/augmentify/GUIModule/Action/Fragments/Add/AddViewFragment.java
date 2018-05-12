package com.augmentify.GUIModule.Action.Fragments.Add;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ScrollView;

import com.augmentify.R;

public class AddViewFragment extends DialogFragment
{
	public static AddViewFragment newInstance()
	{
		AddViewFragment newInstance = new AddViewFragment();
		newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

		return newInstance;
	}

	AddView addView;

	ScrollView addViewScrollView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		addView = new AddView(this.getActivity());
		addView.build();

		addViewScrollView = new ScrollView(this.getActivity());
		addViewScrollView.setPadding(AddViewResource.Padding.left,
				AddViewResource.Padding.top, AddViewResource.Padding.right,
				AddViewResource.Padding.bottom);
		addViewScrollView.addView(addView);
		
		this.getDialog().getWindow()
				.setBackgroundDrawableResource(R.drawable.card_shadow);
		this.getDialog().getWindow().setFlags(LayoutParams.FLAG_DIM_BEHIND, LayoutParams.FLAG_DIM_BEHIND);
		this.getDialog().getWindow().getAttributes().dimAmount = AddViewResource.backgroundDimAmount;
		this.getDialog().setCanceledOnTouchOutside(true);

		return addViewScrollView;
	}

	@Override
	public void onResume()
	{
		super.onResume();
	}

	@Override
	public void onPause()
	{
		super.onPause();
	}
}
