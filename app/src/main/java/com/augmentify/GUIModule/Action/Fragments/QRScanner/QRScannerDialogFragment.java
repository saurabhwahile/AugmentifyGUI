package com.augmentify.GUIModule.Action.Fragments.QRScanner;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ScrollView;

import com.augmentify.R;

public class QRScannerDialogFragment extends DialogFragment
{
	public static QRScannerDialogFragment newInstance()
	{
		QRScannerDialogFragment newInstance = new QRScannerDialogFragment();
		newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
		
		return newInstance;
	}
	
	QRScannerView scannerView;

	ScrollView scannerScrollView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		scannerView = new QRScannerView(this.getActivity());
		scannerView.buildQRScannerView();

		scannerScrollView = new ScrollView(this.getActivity());
		scannerScrollView.addView(scannerView);
		
		this.getDialog().getWindow()
				.setBackgroundDrawableResource(R.drawable.card_shadow);
		this.getDialog().getWindow().setFlags(LayoutParams.FLAG_DIM_BEHIND, LayoutParams.FLAG_DIM_BEHIND);
		this.getDialog().getWindow().getAttributes().dimAmount = QRScannerViewResource.backgroundDimAmount;
		this.getDialog().setCanceledOnTouchOutside(true);

		
		return scannerScrollView;
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		scannerView.onResume();
	}

	@Override
	public void onPause()
	{
		super.onPause();
		scannerView.onPause();
	}
	
}
