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
 * Created by Saurabh on 08/02/2015.
 */
public class NotificationFeed implements DataObject
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

    public class Notification
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
    public Notification notification;

    public class NotificationWrapper
    {
        @Expose
        public List<NotificationFeed> notification_set;
    }

    DataObject.Status status;
    String requestUrl;
    JSONObject response;
    public NotificationWrapper notificationWrapper;
    Context context;

    public NotificationFeed(Context context)
    {
        this.context = context;
    }

    @Override
    public void refresh()
    {
        notificationWrapper = Controller.dataObjectBuilder.fromJson(response.toString(), NotificationWrapper.class);
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendPath(Urls.PATH.NOTIFICATION)
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
                        changeStatus(DataObject.Status.READ_OK);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                changeStatus(DataObject.Status.ERROR);
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
    public void onStatusChange(DataObject.Status status)
    {

    }

    @Override
    public void changeStatus(DataObject.Status status)
    {
        if(this.status == status)
        {
            return;
        }
        this.status = status;
        onStatusChange(status);
    }
}
