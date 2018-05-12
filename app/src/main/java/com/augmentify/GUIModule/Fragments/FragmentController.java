package com.augmentify.GUIModule.Fragments;

import android.app.Activity;
import android.app.FragmentManager;

import com.augmentify.GUIModule.Fragments.Login.LoginViewFragment;
import com.augmentify.GUIModule.Fragments.Register.RegisterViewFragment;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class FragmentController
{
    public static FragmentManager fragmentManager;
    public static void setActivity(Activity activity)
    {
        FragmentController.fragmentManager = activity.getFragmentManager();
    }

    public static enum Fragment
    {
        LOGIN_VIEW_FRAGMENT,
        REGISTER_VIEW_FRAGMENT
    }

    public static LoginViewFragment loginViewFragment;
    public static RegisterViewFragment registerViewFragment;
    public static void initializeFragments()
    {
        for (Fragment fragment : Fragment.values())
        {
            initialize(fragment);
        }
    }

    public static void initialize(Fragment fragmentID)
    {
        switch (fragmentID)
        {
            case LOGIN_VIEW_FRAGMENT:
            {
                loginViewFragment = LoginViewFragment.newInstance();
            }
            break;
            case REGISTER_VIEW_FRAGMENT:
            {
                registerViewFragment = RegisterViewFragment.newInstance();
            }
            break;
        }
    }

    public static void show(Fragment fragmentID)
    {
        switch (fragmentID)
        {
            case LOGIN_VIEW_FRAGMENT:
            {
                loginViewFragment.show(fragmentManager, "LoginViewFragment");
            }
            break;
            case REGISTER_VIEW_FRAGMENT:
            {
                registerViewFragment.show(fragmentManager, "RegisterViewFragment");
            }
            break;
        }
    }

    public static void dismiss(Fragment fragmentID)
    {
        switch (fragmentID)
        {
            case LOGIN_VIEW_FRAGMENT:
            {
                loginViewFragment.dismiss();
            }
            break;
            case REGISTER_VIEW_FRAGMENT:
            {
                registerViewFragment.dismiss();
            }
            break;
        }
    }
}
