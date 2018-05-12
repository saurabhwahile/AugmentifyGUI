package com.augmentify.GUIModule.Action.Fragments.Admin;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.augmentify.R;

/**
 * Created by Saurabh on 07/02/2015.
 */
public class AdminViewFragment extends DialogFragment
{
    public static AdminViewFragment newInstance()
    {
        AdminViewFragment newInstance = new AdminViewFragment();
        newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

        return newInstance;
    }

    AdminView adminView;
    ScrollView adminViewScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        adminView = new AdminView(this.getActivity());
        adminView.build();

        adminViewScrollView = new ScrollView(this.getActivity());
        adminViewScrollView.setPadding(AdminViewResource.Padding.left,
                AdminViewResource.Padding.top, AdminViewResource.Padding.right,
                AdminViewResource.Padding.bottom);
        adminViewScrollView.addView(adminView);

        this.getDialog().getWindow()
                .setBackgroundDrawableResource(R.drawable.card_shadow);
        this.getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getDialog().getWindow().getAttributes().dimAmount = AdminViewResource.backgroundDimAmount;
        this.getDialog().setCanceledOnTouchOutside(true);

        return adminViewScrollView;
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
