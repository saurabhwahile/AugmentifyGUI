package com.augmentify.GUIModule.Action.Fragments.Bulletin;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.augmentify.R;

/**
 * Created by Saurabh on 06/02/2015.
 */
public class BulletinViewFragment extends DialogFragment
{
    public static BulletinViewFragment newInstance()
    {
        BulletinViewFragment newInstance = new BulletinViewFragment();
        newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

        return newInstance;
    }

    BulletinView bulletinView;

    ScrollView bulletinViewScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        bulletinView = new BulletinView(this.getActivity());
        bulletinView.build();

        bulletinViewScrollView = new ScrollView(this.getActivity());
        bulletinViewScrollView.setPadding(BulletinViewResource.Padding.left,
                BulletinViewResource.Padding.top, BulletinViewResource.Padding.right,
                BulletinViewResource.Padding.bottom);
        bulletinViewScrollView.addView(bulletinView);

        this.getDialog().getWindow()
                .setBackgroundDrawableResource(R.drawable.card_shadow);
        this.getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getDialog().getWindow().getAttributes().dimAmount = BulletinViewResource.backgroundDimAmount;
        this.getDialog().setCanceledOnTouchOutside(true);

        return bulletinViewScrollView;
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
