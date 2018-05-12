package com.augmentify.DataModule.Controller;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.augmentify.DataModule.Controller.Modules.ExploreLoader;
import com.augmentify.DataModule.Controller.Modules.FriendsLoader;
import com.augmentify.DataModule.Controller.Modules.MeLoader;
import com.augmentify.GUIModule.MainLayouts.TabActionCardLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Controller
{
	public static Context context;
	public static RequestQueue networkRequestQueue;
	public static Gson dataObjectBuilder;
	public Controller(Context context)
	{
		Controller.context = context;
		networkRequestQueue = Volley.newRequestQueue(context);
		dataObjectBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	public static TabActionCardLayout viewRootHandle;
	
	public static FriendsLoader friendsLoader;
	public static Thread friendsLoaderThread;
	public static void buildFriends()
	{
		friendsLoaderThread = new Thread(friendsLoader);
		friendsLoaderThread.start();
	}

    public static ExploreLoader exploreLoader;
    public static Thread exploreLoaderThread;
	public static void buildExplore()
	{
        exploreLoaderThread = new Thread(exploreLoader);
        exploreLoaderThread.start();
	}

    public static MeLoader meLoader;
    public static Thread meLoaderThread;
	public static void buildMe()
	{
        meLoaderThread = new Thread(meLoader);
        meLoaderThread.start();
	}
	
	public static void build()
	{
        friendsLoader = new FriendsLoader(context);
        meLoader = new MeLoader(context);
        exploreLoader = new ExploreLoader(context);
		buildFriends();
        buildMe();
        //buildExplore();
	}
}
