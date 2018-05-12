package com.augmentify.GUIModule.Card.Fragments.Comments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.R;

public class CommentsViewFragment extends DialogFragment
{
	static CommentsViewFragment newInstance;
	public static CommentsViewFragment newInstance()
	{
		if (newInstance != null)
		{
			return newInstance;
		}
		newInstance = new CommentsViewFragment();
		newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
		newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

		return newInstance;
	}

	public static CommentsViewLayout commentsViewLayout;

	public CommentsViewLayout newCommentsViewLayout()
	{
		if (commentsViewLayout != null)
		{
			commentsViewLayout.removeAllViews();
			commentsViewLayout = null;
		}
		commentsViewLayout = new CommentsViewLayout(this.getActivity());
		commentsViewLayout.build();
		return commentsViewLayout;
	}

	static LayoutParams params;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		newCommentsViewLayout();
		commentsViewLayout.refresh(data);
		this.getDialog().getWindow()
				.setBackgroundDrawableResource(R.drawable.card_shadow);
		this.getDialog()
				.getWindow()
				.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
						WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		this.getDialog().getWindow().getAttributes().dimAmount = CommentsViewResource.Fragment.backgroundDimAmount;
		this.getDialog().setCanceledOnTouchOutside(true);

		return commentsViewLayout;
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
