package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Minimap;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ScrollView;

import com.augmentify.R;

public class MinimapViewFragment extends DialogFragment
{
	static MinimapViewFragment newInstance;
	public static MinimapViewFragment newInstance()
	{
		if(newInstance == null)
		{
			newInstance = new MinimapViewFragment();
			newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
			newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
		}
		return newInstance;
	}

	MinimapView minimapView;
	ScrollView minimapViewScrollView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		minimapView = new MinimapView(this.getActivity());
		minimapView.build();

		minimapViewScrollView = new ScrollView(this.getActivity());
		minimapViewScrollView.setPadding(MinimapViewResource.Padding.left,
				MinimapViewResource.Padding.top, MinimapViewResource.Padding.right,
				MinimapViewResource.Padding.bottom);
		minimapViewScrollView.addView(minimapView);

		this.getDialog().getWindow()
				.setBackgroundDrawableResource(R.drawable.card_shadow);
		this.getDialog()
				.getWindow()
				.setFlags(LayoutParams.FLAG_DIM_BEHIND,
						LayoutParams.FLAG_DIM_BEHIND);
		this.getDialog().getWindow().getAttributes().dimAmount = MinimapViewResource.backgroundDimAmount;
		this.getDialog().setCanceledOnTouchOutside(true);

		return minimapViewScrollView;
	}
}

