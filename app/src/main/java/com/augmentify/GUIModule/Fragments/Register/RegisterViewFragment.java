package com.augmentify.GUIModule.Fragments.Register;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class RegisterViewFragment extends DialogFragment
{
    static RegisterViewFragment newInstance;

    public static RegisterViewFragment newInstance()
    {
        if (newInstance != null)
        {
            return newInstance;
        }
        newInstance = new RegisterViewFragment();
        newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

        return newInstance;
    }

    public static RegisterView registerView;
    public static ScrollView registerViewScrollView;

    public RegisterView newRegisterView()
    {
        if (registerView != null)
        {
            registerView.removeAllViews();
            registerView = null;
        }
        registerView = new RegisterView(this.getActivity());
        registerView.build();
        return registerView;
    }

    static RelativeLayout.LayoutParams params;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        newRegisterView();

        registerViewScrollView = new ScrollView(this.getActivity());
        registerViewScrollView.setId(Resource.id++);
        registerViewScrollView.addView(registerView);
        registerViewScrollView.setId(Resource.id++);

        this.getDialog().getWindow()
                .setBackgroundDrawableResource(R.drawable.card_shadow);
        this.getDialog()
                .getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                        WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getDialog().getWindow().getAttributes().dimAmount = RegisterViewResource.Fragment.backgroundDimAmount;
        //this.getDialog().setCanceledOnTouchOutside(true);

        return registerViewScrollView;
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
