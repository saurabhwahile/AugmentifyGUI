package com.augmentify.DataModule.Objects.User;

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
import com.augmentify.DataModule.Objects.Meta;
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
 * Created by Saurabh on 19/07/2014.
 */
public class Profile implements DataObject
{
    @Expose
    public String gender;
    @Expose
    public String profile_picture;
    @Expose
    public User user;

    public static class ProfileMeta
    {
        @Expose
        public Meta meta;
        @Expose
        public List<Profile> objects;
    }

    void copyFields(Profile profile)
    {
        this.gender = profile.gender;
        this.profile_picture = profile.profile_picture;
        this.user = profile.user;
    }

    public static enum RESOURCE_TYPE
    {
        PROFILE,
        PROFILE_DETAIL,
        PROFILE_PRIVATE,
        PROFILE_PRIVATE_DETAIL
    }

    int id;
    Status status;
    String requestUrl;
    RESOURCE_TYPE requestType;
    JSONObject response;
    SQLCache profileCache;
    Context context;

    public Profile(Context context)
    {
        profileCache = new SQLCache(context, Urls.PATH.PROFILE);
        this.context = context;
    }

    @Override
    public void refresh()
    {
        switch (requestType)
        {
            case PROFILE:
            case PROFILE_PRIVATE:
            {
                ProfileMeta profileMeta = Controller.dataObjectBuilder.fromJson(response.toString(), ProfileMeta.class);
                copyFields(profileMeta.objects.get(0));
            }
            break;
            case PROFILE_DETAIL:
            case PROFILE_PRIVATE_DETAIL:
            {
                copyFields(Controller.dataObjectBuilder.fromJson(response.toString(), Profile.class));
            }
        }
    }

    public void setReadRequestParams(RESOURCE_TYPE requestType, int id)
    {
        this.id = id;
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
                .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);

        switch (requestType)
        {
            case PROFILE:
            {
                uriBuilder.appendPath(Urls.PATH.PROFILE);
            }
            break;
            case PROFILE_DETAIL:
            {
                uriBuilder.appendPath(Urls.PATH.PROFILE);
                uriBuilder.appendPath(String.valueOf(id));
            }
            break;
            case PROFILE_PRIVATE:
            {
                uriBuilder.appendPath(Urls.PATH.PROFILE_PRIVATE);
            }
            break;
            case PROFILE_PRIVATE_DETAIL:
            {
                uriBuilder.appendPath(Urls.PATH.PROFILE_PRIVATE);
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
                        profileCache.set(requestUrl, response.toString());
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

    public void setCreateRequestParams(
            String username,
            String password,
            String email,
            String gender,
            String first_name,
            String last_name
    )
    {
        this.user = new User(context);
        this.user.username = username;
        this.user.password = password;
        this.user.email = email;
        this.user.first_name = first_name;
        this.user.last_name = last_name;
        this.gender = gender;
    }

    @Override
    public void create()
    {
        Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                .buildUpon()
                .appendPath(Urls.API)
                .appendPath(Urls.VERSION)
                .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                .appendQueryParameter(Urls.REQUEST_KEY.MAGIC_WORD, "OpenSesame");

        uriBuilder.appendPath(Urls.PATH.PROFILE_CREATE);
        uriBuilder.appendPath("");
        requestUrl = uriBuilder.build().toString();

        final Profile data = new Profile(context);
        data.copyFields(this);
        StringRequest request = new StringRequest(Request.Method.POST, requestUrl,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d(Debug.TAG.NET, "Profile Created");
                        changeStatus(Status.CREATE_OK);
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d(Debug.TAG.NET, "Error [" + error + "]");
                changeStatus(Status.ERROR);
            }
        })
        {
            @Override
            public byte[] getBody() throws AuthFailureError
            {
                try
                {
                    return new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation().create()
                            .toJson(data).getBytes(getParamsEncoding());
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
        this.onStatusChange(status);
    }
}
