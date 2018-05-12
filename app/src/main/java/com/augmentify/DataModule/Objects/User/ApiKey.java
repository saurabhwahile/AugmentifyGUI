package com.augmentify.DataModule.Objects.User;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.Objects.Meta;
import com.augmentify.DataModule.SQLCache;
import com.augmentify.DataModule.Urls;
import com.augmentify.Debug;
import com.google.gson.annotations.Expose;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Saurabh on 23/07/2014.
 */

public class ApiKey implements DataObject
{
    @Expose
    public String key;

    public class ApiKeyMeta
    {
        @Expose
        public Meta meta;
        @Expose
        public List<ApiKey> objects;
    }

    void copyFields(ApiKey apiKey)
    {
        this.key = apiKey.key;
    }

    public static enum RESOURCE_TYPE
    {
        API_KEY,
        API_KEY_DETAIL
    }

    int id;
    public String username;
    String password;
    Status status;
    String requestUrl;
    RESOURCE_TYPE requestType;
    JSONObject response;
    SQLCache apiKeyCache;
    Context context;

    public ApiKey(Context context)
    {
        apiKeyCache = new SQLCache(context, Urls.PATH.API_KEY.replace('-','_'));
        this.context = context;
    }

    @Override
    public void refresh()
    {
        switch (requestType)
        {
            case API_KEY:
            {
                Log.d(Debug.TAG.NET, response.toString());
                copyFields(Controller.dataObjectBuilder.fromJson(response.toString(), ApiKeyMeta.class).objects.get(0));
            }
            break;
            case API_KEY_DETAIL:
            {
                copyFields(Controller.dataObjectBuilder.fromJson(response.toString(), ApiKey.class));
            }
            break;
        }
    }

    public void setReadRequestParams(RESOURCE_TYPE requestType, String username, String password, int id)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.requestType = requestType;
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, username)
                .appendQueryParameter(Urls.REQUEST_KEY.PASSWORD, password);

        switch (requestType)
        {
            case API_KEY:
            {
                uriBuilder.appendPath(Urls.PATH.API_KEY);
            }
            break;
            case API_KEY_DETAIL:
            {
                uriBuilder.appendPath(Urls.PATH.API_KEY);
                uriBuilder.appendPath(String.valueOf(id));
            }
            break;
        }

        uriBuilder.appendPath("");
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
                        apiKeyCache.set(requestUrl, response.toString());
                        changeStatus(Status.READ_OK);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d(Debug.TAG.NET, error.toString());
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED)
                {
                    changeStatus(Status.ERROR_AUTHENTICATION);
                    return;
                }
                if(networkResponse==null)
                {
                    changeStatus(Status.ERROR_NETWORK);
                    return;
                }
                changeStatus(Status.ERROR);
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

    public void onResponse(Status status)
    {

    }

    @Override
    public void onStatusChange(Status status)
    {

    }

    @Override
    public void changeStatus(Status status)
    {
        this.status = status;
        this.onStatusChange(status);
        this.onResponse(status);
    }
}
