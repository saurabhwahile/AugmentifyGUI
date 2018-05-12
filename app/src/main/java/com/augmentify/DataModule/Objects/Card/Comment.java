package com.augmentify.DataModule.Objects.Card;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.augmentify.DataModule.Controller.Controller;
import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.DataModule.SQLCache;
import com.augmentify.DataModule.Settings;
import com.augmentify.DataModule.Urls;
import com.augmentify.Debug;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Saurabh on 23/08/2014.
 */
public class Comment implements DataObject
{
    @Expose
    public int user;
    @Expose
    public String comment;

    public class Comments
    {
        @Expose
        public List<Comment> objects;
    }

    void copyFields(Comment comment)
    {
        this.user = comment.user;
        this.comment = comment.comment;
    }

    public static enum RESOURCE_TYPE
    {
        EVENT_COMMENTS,
        EVENT_COMMENTS_DETAIL
    }

    public int id;
    public String resourceId;
    DataObject.Status status;
    String requestUrl;
    RESOURCE_TYPE requestType;
    JSONObject response;
    SQLCache commentCache;
    public Comments comments;
    Context context;

    public Comment(Context context)
    {
        this.context = context;
        commentCache = new SQLCache(context, Urls.PATH.EVENT_COMMENTS);
    }

    @Override
    public void refresh()
    {
        switch(requestType)
        {
            case EVENT_COMMENTS:
            {
                comments = Controller.dataObjectBuilder.fromJson(response.toString(), Comments.class);
            }
            break;
            case EVENT_COMMENTS_DETAIL:
            {
                comments = Controller.dataObjectBuilder.fromJson(response.toString(), Comments.class);
                copyFields(comments.objects.get(id));
            }
            break;
        }
    }


    public void setReadRequestParams(RESOURCE_TYPE requestType, String eventId)
    {
        this.resourceId = eventId;
        this.requestType = requestType;
    }

    public void setReadRequestParams(RESOURCE_TYPE requestType, String resourceId, int id)
    {
        this.id = id;
        this.resourceId = resourceId;
        this.requestType = requestType;
    }

    @Override
    public void read()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendPath(Urls.PATH.EVENT_COMMENTS)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);

        switch(requestType)
        {
            case EVENT_COMMENTS:
            {
                uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId);
            }
            break;
            case EVENT_COMMENTS_DETAIL:
            {
                uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId);
                //uriBuilder.appendPath(String.valueOf(id));
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

    public void setCreateRequestParams(RESOURCE_TYPE requestType, String resourceId, String comment)
    {
        this.requestType = requestType;
        this.resourceId = resourceId;
        this.comment = comment;
    }

    @Override
    public void create()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey)
                .appendPath("");
        requestUrl = uriBuilder.build().toString();
        Log.d(Debug.TAG.NET, requestUrl);

        switch(requestType)
        {
            case EVENT_COMMENTS:
            {
                uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId)
                        .appendPath(Urls.PATH.EVENT_COMMENTS);
            }
            break;
        }

        final Comment data = new Comment(context);
        data.copyFields(this);
        StringRequest request = new StringRequest(Request.Method.POST, requestUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        changeStatus(DataObject.Status.CREATE_OK);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                if (error.networkResponse.statusCode == 500)
                {
                    changeStatus(DataObject.Status.ERROR_NETWORK);
                }
                changeStatus(DataObject.Status.ERROR);
                System.out.println("Error [" + new String(error.networkResponse.data) + "]");
            }
        })
        {
            @Override
            public byte[] getBody() throws AuthFailureError
            {
                try
                {
                    Log.d("json", new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation().create()
                            .toJson(data));
                    return new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation().create()
                            .toJson(data)
                            .getBytes(getParamsEncoding());
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public String getBodyContentType()
            {
                return "application/json; charset=" + getParamsEncoding();
            }
        };
        Controller.networkRequestQueue.add(request);
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
        this.onStatusChange(status);
    }

}
