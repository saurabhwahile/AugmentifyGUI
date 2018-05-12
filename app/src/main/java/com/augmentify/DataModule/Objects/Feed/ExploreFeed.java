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
public class ExploreFeed implements DataObject
{
    public class Result
    {
        @Expose
        public String id;
        @Expose
        public String label;
    }

    @Expose
    public Result result;

    public class ExploreWrapper
    {
        @Expose
        public List<ExploreFeed> search_set;
    }

    DataObject.Status status;
    String requestUrl;
    JSONObject response;
    public ExploreWrapper exploreWrapper;
    Context context;

    public ExploreFeed(Context context)
    {
        this.context = context;
    }

    @Override
    public void refresh()
    {
        exploreWrapper = Controller.dataObjectBuilder.fromJson(response.toString(), ExploreWrapper.class);
    }

    String q;
    public void setReadRequestParams(String key)
    {
        this.q = key;
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendPath(Urls.PATH.SEARCH)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey)
                .appendQueryParameter(Urls.REQUEST_KEY.Q, q)
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
                        if(response.length()==0)
                        {
                            changeStatus(Status.ERROR);
                            return;
                        }
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
