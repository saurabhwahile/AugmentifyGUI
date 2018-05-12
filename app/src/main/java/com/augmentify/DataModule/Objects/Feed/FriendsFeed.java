package com.augmentify.DataModule.Objects.Feed;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Settings;
import com.augmentify.DataModule.Urls;
import com.augmentify.Debug;
import com.google.gson.annotations.Expose;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Saurabh on 25/07/2014.
 */
public class FriendsFeed implements DataObject
{
    public class Action
    {}

    public class Source
    {
        @Expose
        public String user;
        @Expose
        public String event;
    }

    public class Feed
    {
        @Expose
        public Action actions;
        @Expose
        public Source sources;
        @Expose
        public String timestamp;
        @Expose
        public String type;
    }

    @Expose
    public Feed feed;

    public class FriendsFeedWrapper
    {
        @Expose
        public List<FriendsFeed> objects;
    }

    Status status;
    String requestUrl;
    JSONObject response;
    public FriendsFeedWrapper friendsFeedWrapper;
    Context context;

    public FriendsFeed(Context context)
    {
        this.context = context;
    }

    @Override
    public void refresh()
    {
        friendsFeedWrapper = Controller.dataObjectBuilder.fromJson(response.toString(), FriendsFeedWrapper.class);
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendPath(Urls.PATH.FEED)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey)
                .appendPath("");

        requestUrl = uriBuilder.build().toString();
        Log.d(Debug.TAG.NET, requestUrl);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, requestUrl, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject serverResponse)
                    {
                        response = serverResponse;
                        refresh();
                        changeStatus(Status.READ_OK);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                changeStatus(Status.ERROR);
                Log.d(Debug.TAG.NET, error.toString());
            }
        });
        Controller.networkRequestQueue.add(request);
    }

    @Override
    public void create()
    {

    }

    @Override
    public void delete()
    {

    }

    @Override
    public void onStatusChange(Status status)
    {

    }

    @Override
    public void changeStatus(Status status)
    {
        if(this.status == status)
        {
            return;
        }
        this.status = status;
        onStatusChange(status);
    }
}
