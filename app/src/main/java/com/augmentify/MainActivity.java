package com.augmentify;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Menu;

import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Settings;
import com.augmentify.GUIModule.Fragments.FragmentController;
import com.augmentify.GUIModule.MainLayouts.TabActionCardLayout;
import com.augmentify.GUIModule.Utils.AnimationUtils;

public class MainActivity extends FragmentActivity
{
	TabActionCardLayout tabActionCardLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		Resource.screenWidth = metrics.widthPixels;
		Resource.screenHeight = metrics.heightPixels;

		hideActionBar();

		AnimationUtils.initializeAnimations();

        FragmentController.setActivity(this);
        FragmentController.initializeFragments();
        try
        {
            Settings.load(this);
        }
        catch (Settings.SettingsException e)
        {
            FragmentController.show(FragmentController.Fragment.LOGIN_VIEW_FRAGMENT);
        }

        tabActionCardLayout = new TabActionCardLayout(this);
        tabActionCardLayout.build();
        new Controller(this);
        Controller.viewRootHandle = tabActionCardLayout;
        Controller.build();



        setContentView(tabActionCardLayout);
        //com.augmentify.GUIModule.Card.Fragments.FragmentController.setActivity(this);
        //com.augmentify.GUIModule.Card.Fragments.FragmentController.show(com.augmentify.GUIModule.Card.Fragments.FragmentController.Fragment.BULK_SEARCH_AND_ADD_FRAGMENT, null);
		//getWindow().setFormat(PixelFormat.RGBA_8888);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	public void hideActionBar()
	{
		if (Build.VERSION.SDK_INT >= 11)
		{
			ActionBar actionBar = this.getActionBar();
			actionBar.hide();
		}
	}

}
