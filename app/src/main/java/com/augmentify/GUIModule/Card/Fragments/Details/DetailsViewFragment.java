package com.augmentify.GUIModule.Card.Fragments.Details;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.R;
import com.augmentify.Resource;

public class DetailsViewFragment extends DialogFragment
{
	static DetailsViewFragment newInstance;

	public static DetailsViewFragment newInstance()
	{
		if (newInstance != null)
		{
			return newInstance;
		}
		newInstance = new DetailsViewFragment();
		newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

		return newInstance;
	}

	public static DetailsView detailsView;
	public static ScrollView detailsViewScrollView;

	public DetailsView newDetailsView()
	{
		if (detailsView != null)
		{
			detailsView.removeAllViews();
			detailsView = null;
		}
		detailsView = new DetailsView(this.getActivity());
		detailsView.build();
		return detailsView;
	}

	static LayoutParams params;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		newDetailsView();

        detailsView.refresh(data);
		detailsViewScrollView = new ScrollView(this.getActivity());
		detailsViewScrollView.addView(detailsView);
		detailsViewScrollView.setId(Resource.id++);

		this.getDialog().getWindow()
				.setBackgroundDrawableResource(R.drawable.card_shadow);
		this.getDialog()
				.getWindow()
				.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
						WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		this.getDialog().getWindow().getAttributes().dimAmount = DetailsViewResource.Fragment.backgroundDimAmount;
		this.getDialog().setCanceledOnTouchOutside(true);

		return detailsViewScrollView;
	}

    DataObject data;
    public void refresh(DataObject data)
    {
        this.data = data;
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
