package com.augmentify.GUIModule.Action.Fragments.Add.Fragments;

import android.app.Activity;
import android.app.FragmentManager;

import com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Event.EventViewFragment;
import com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Minimap.MinimapViewFragment;
import com.augmentify.GUIModule.Action.Fragments.Add.Fragments.Say.SayViewFragment;

public class FragmentController
{
	public static FragmentManager fragmentManager;
	public static void setActivity(Activity activity)
	{
		FragmentController.fragmentManager = activity.getFragmentManager();
	}

	public static enum Fragment
	{
		EVENT_VIEW_FRAGMENT,
		SAY_VIEW_FRAGMENT,
		MINIMAP_VIEW_FRAGMENT
	}

	static EventViewFragment eventViewFragment;
	static SayViewFragment sayViewFragment;
	static MinimapViewFragment minimapViewFragment;
	
	public static void initialize(Fragment fragmentID)
	{
		switch (fragmentID)
		{
			case EVENT_VIEW_FRAGMENT:
			{
				eventViewFragment = EventViewFragment.newInstance();
			}
				break;
			case SAY_VIEW_FRAGMENT:
			{
				sayViewFragment = SayViewFragment.newInstance();
			}
				break;
			case MINIMAP_VIEW_FRAGMENT:
			{
				minimapViewFragment = MinimapViewFragment.newInstance();
			}
				break;
		}
	}
	
	public static void show(Fragment fragmentID)
	{
		switch (fragmentID)
		{
			case EVENT_VIEW_FRAGMENT:
			{
				eventViewFragment.show(fragmentManager, "EventViewFragment");
			}
				break;
			case SAY_VIEW_FRAGMENT:
			{
				sayViewFragment.show(fragmentManager, "SayViewFragment");
			}
				break;
			case MINIMAP_VIEW_FRAGMENT:
			{
				minimapViewFragment.show(fragmentManager, "MinimapViewFragment");
			}
				break;
		}
	}

    public static void dismiss(Fragment fragmentID)
    {
        switch (fragmentID)
        {
            case EVENT_VIEW_FRAGMENT:
            {
                eventViewFragment.dismiss();
            }
            break;
            case SAY_VIEW_FRAGMENT:
            {
                sayViewFragment.dismiss();
            }
            break;
            case MINIMAP_VIEW_FRAGMENT:
            {
                minimapViewFragment.dismiss();
            }
            break;
        }
    }
}
