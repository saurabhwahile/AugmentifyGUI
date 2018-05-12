package com.augmentify.GUIModule.Fragments.Login;

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
public class LoginViewFragment extends DialogFragment
{
    static LoginViewFragment newInstance;

    public static LoginViewFragment newInstance()
    {
        if (newInstance != null)
        {
            return newInstance;
        }
        newInstance = new LoginViewFragment();
        newInstance.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        newInstance.setStyle(DialogFragment.STYLE_NO_FRAME, 0);

        return newInstance;
    }

    public static LoginView loginView;
    public static ScrollView loginViewScrollView;

    public LoginView newLoginView()
    {
        if (loginView != null)
        {
            loginView.removeAllViews();
            loginView = null;
        }
        loginView = new LoginView(this.getActivity());
        loginView.build();
        return loginView;
    }

    static RelativeLayout.LayoutParams params;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        newLoginView();

        loginViewScrollView = new ScrollView(this.getActivity());
        loginViewScrollView.setId(Resource.id++);
        loginViewScrollView.addView(loginView);
        loginViewScrollView.setId(Resource.id++);

        this.getDialog().getWindow()
                .setBackgroundDrawableResource(R.drawable.card_shadow);
        this.getDialog()
                .getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                        WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getDialog().getWindow().getAttributes().dimAmount = LoginViewResource.Fragment.backgroundDimAmount;
        //this.getDialog().setCanceledOnTouchOutside(true);

        return loginViewScrollView;
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
