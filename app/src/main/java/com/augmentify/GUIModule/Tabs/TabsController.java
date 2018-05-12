package com.augmentify.GUIModule.Tabs;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.augmentify.GUIModule.Explore.ExploreFragment;
import com.augmentify.GUIModule.Friends.FriendsFragment;
import com.augmentify.GUIModule.Me.MeFragment;
import com.augmentify.Resource;

public class TabsController extends FragmentPagerAdapter
{
	public ViewPager viewPager;
	ActionBar actionBar;

	public TabsController(FragmentManager fragmentManager, Activity activity)
	{
		super(fragmentManager);
		
		initializeAll();
		initializeViewPager(activity);
		initializeTabView(activity);
	}

	//public static final String[] TAB_NAMES = { "Friends", "Explore", "Me" };

	void initializeViewPager(Activity activity)
	{
		viewPager = new ViewPager(activity);
		viewPager.setId(Resource.id++);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(this);

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{

			}

			@Override
			public void onPageScrolled(int position, float offset,
					int offsetPixels)
			{
				tabViewLayout.tabIndicator
						.setTranslationX((int) (offset * tabViewLayout.tabWidth)
								+ (tabViewLayout.tabWidth * position));
			}

			@Override
			public void onPageScrollStateChanged(int tab)
			{

			}
		});
	}

	public TabViewLayout tabViewLayout;

	void initializeTabView(Activity activity)
	{
		tabViewLayout = new TabViewLayout(activity);
		tabViewLayout.build();

		tabViewLayout.friendsTabView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View view)
			{
				viewPager.setCurrentItem(FRIENDS_FRAGMENT, true);
			}
		});

		tabViewLayout.exploreTabView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View view)
			{
				viewPager.setCurrentItem(EXPLORE_FRAGMENT, true);
			}
		});

		tabViewLayout.meTabView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View view)
			{
				viewPager.setCurrentItem(ME_FRAGMENT, true);
			}
		});
	}

	public static final int NUMBER_OF_FRAGMENTS = 3;
	public static final int FRIENDS_FRAGMENT = 0;
	public static final int EXPLORE_FRAGMENT = 1;
	public static final int ME_FRAGMENT = 2;

	public FriendsFragment friendsFragment;
	public ExploreFragment exploreFragment;
	public MeFragment meFragment;

	public void initialize(int tab)
	{
		switch (tab)
		{
			case FRIENDS_FRAGMENT:
			{
				friendsFragment = new FriendsFragment();
			}
				break;
			case EXPLORE_FRAGMENT:
			{
				exploreFragment = new ExploreFragment();
			}
				break;
			case ME_FRAGMENT:
			{
				meFragment = new MeFragment();
			}
				break;
		}
	}

	public void initializeAll()
	{
		initialize(0);
		initialize(1);
		initialize(2);
	}

	@Override
	public Fragment getItem(int index)
	{
		switch (index)
		{
			case FRIENDS_FRAGMENT:
			{
				// initialize(FRIENDS_FRAGMENT);
				return friendsFragment;
			}
			case EXPLORE_FRAGMENT:
			{
				// initialize(EXPLORE_FRAGMENT);
				return exploreFragment;
			}
			case ME_FRAGMENT:
			{
				// initialize(ME_FRAGMENT);
				return meFragment;
			}
		}
		return null;
	}

	@Override
	public int getCount()
	{
		return NUMBER_OF_FRAGMENTS;
	}
}
