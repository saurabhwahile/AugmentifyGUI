package com.augmentify.GUIModule.Card.Fragments.BulkSearchAndAdd;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.GUIModule.Card.Fragments.Comments.CommentsViewResource;
import com.augmentify.R;

/**
 * Created by Saurabh on 21/02/2015.
 */
public class BulkSearchAndAddViewFragment extends DialogFragment
{
    static BulkSearchAndAddViewFragment newInstance;
    public static BulkSearchAndAddViewFragment newInstance()
    {
        if (newInstance != null)
        {
            return newInstance;
        }
        newInstance = new BulkSearchAndAddViewFragment();
        newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

        return newInstance;
    }

    public static BulkSearchAndAddViewLayout bulkSearchAndAddViewLayout;

    public BulkSearchAndAddViewLayout newBulkSearchAddViewLayout()
    {
        if (bulkSearchAndAddViewLayout != null)
        {
            bulkSearchAndAddViewLayout.removeAllViews();
            bulkSearchAndAddViewLayout = null;
        }
        bulkSearchAndAddViewLayout = new BulkSearchAndAddViewLayout(this.getActivity());
        bulkSearchAndAddViewLayout.build();
        return bulkSearchAndAddViewLayout;
    }

    static RelativeLayout.LayoutParams params;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        newBulkSearchAddViewLayout();
        //bulkSearchAndAddViewLayout.refresh(data);
        this.getDialog().getWindow()
                .setBackgroundDrawableResource(R.drawable.card_shadow);
        this.getDialog()
                .getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                        WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getDialog().getWindow().getAttributes().dimAmount = CommentsViewResource.Fragment.backgroundDimAmount;
        this.getDialog().setCanceledOnTouchOutside(true);

        return bulkSearchAndAddViewLayout;
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
