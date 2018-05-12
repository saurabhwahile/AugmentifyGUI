package com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Say;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ScrollView;

import com.augmentify.R;

public class SayViewFragment extends DialogFragment
{
	public static SayViewFragment newInstance()
	{
		SayViewFragment newInstance = new SayViewFragment();
		newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
		return newInstance;
	}

	SayView sayView;
	ScrollView sayViewScrollView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		sayView = new SayView(this.getActivity());
		sayView.build();

		sayViewScrollView = new ScrollView(this.getActivity());
		sayViewScrollView.setPadding(SayViewResource.Padding.left,
				SayViewResource.Padding.top, SayViewResource.Padding.right,
				SayViewResource.Padding.bottom);
		sayViewScrollView.addView(sayView);

		this.getDialog().getWindow()
				.setBackgroundDrawableResource(R.drawable.card_shadow);
		this.getDialog()
				.getWindow()
				.setFlags(LayoutParams.FLAG_DIM_BEHIND,
						LayoutParams.FLAG_DIM_BEHIND);
		this.getDialog().getWindow().getAttributes().dimAmount = SayViewResource.backgroundDimAmount;
		this.getDialog().setCanceledOnTouchOutside(true);

		return sayViewScrollView;
	}
}
