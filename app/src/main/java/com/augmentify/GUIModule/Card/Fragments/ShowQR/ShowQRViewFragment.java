package com.augmentify.GUIModule.Card.Fragments.ShowQR;

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

public class ShowQRViewFragment extends DialogFragment
{
	static ShowQRViewFragment newInstance;

	public static ShowQRViewFragment newInstance()
	{
		if (newInstance != null)
		{
			return newInstance;
		}
		newInstance = new ShowQRViewFragment();
		newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

		return newInstance;
	}

	public static ShowQRView showQRView;
	public static ScrollView showQRViewScrollView;

	public ShowQRView newShowQRView()
	{
		if (showQRView != null)
		{
			showQRView.removeAllViews();
			showQRView = null;
		}
		showQRView = new ShowQRView(this.getActivity());
		showQRView.build();
		return showQRView;
	}

	static LayoutParams params;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		newShowQRView();
        showQRView.refresh(data);
		
		showQRViewScrollView = new ScrollView(this.getActivity());
		showQRViewScrollView.setId(Resource.id++);
		showQRViewScrollView.addView(showQRView);
		showQRViewScrollView.setId(Resource.id++);

		this.getDialog().getWindow()
				.setBackgroundDrawableResource(R.drawable.card_shadow);
		this.getDialog()
				.getWindow()
				.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
						WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		this.getDialog().getWindow().getAttributes().dimAmount = ShowQRViewResource.Fragment.backgroundDimAmount;
		this.getDialog().setCanceledOnTouchOutside(true);

		return showQRViewScrollView;
	}

    DataObject data;
    public void refresh(DataObject dataObject)
    {
        this.data = dataObject;
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
