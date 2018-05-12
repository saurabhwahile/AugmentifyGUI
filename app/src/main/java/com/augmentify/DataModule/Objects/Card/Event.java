package com.augmentify.DataModule.Objects.Card;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
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
import java.util.ArrayList;
import java.util.List;

public class Event implements DataObject
{
    @Expose
    public String id;
    @Expose
	public String creation_date;
	@Expose
	public String name;
	@Expose
	public String description;
	@Expose
	public String time_from;
	@Expose
	public String time_to;
	@Expose
	public String date;
	@Expose
	public String location;
    @Expose
    public int visibility;
	@Expose
	public String resource_uri;
    @Expose
    public String qr;
    @Expose
    public boolean is_admin;
    @Expose
    public boolean has_joined;
    @Expose
    public int image_id;

    public class EventWrapper
    {
        @Expose
        public Meta meta;
        @Expose
        public List<Event> objects;
    }

    void copyFields(Event event)
    {
        this.id = event.id;
        this.creation_date = event.creation_date;
        this.name = event.name;
        this.description = event.description;
        this.time_from = event.time_from;
        this.time_to = event.time_to;
        this.date = event.date;
        this.location = event.location;
        this.resource_uri = event.resource_uri;
        this.qr = event.qr;
        this.visibility = event.visibility;
        this.is_admin = event.is_admin;
        this.has_joined = event.has_joined;
        this.image_id = event.image_id;
    }

    public static enum RESOURCE_TYPE
    {
        EVENT,
        EVENT_DETAIL,
        MY_EVENTS_FEED,
        JOINED_EVENTS_FEED
    }

    Status status;
    String requestUrl;
    RESOURCE_TYPE requestType;
    JSONObject response;
    SQLCache eventCache;
    Context context;
    public EventWrapper eventWrapper;

    public Event(Context context)
    {
        this.context = context;
        eventCache = new SQLCache(context, Urls.PATH.EVENT);
    }

	@Override
	public void refresh()
	{
        switch(requestType)
        {
            case EVENT:
            case MY_EVENTS_FEED:
            case JOINED_EVENTS_FEED:
            {
                eventWrapper = Controller.dataObjectBuilder.fromJson(response.toString(), EventWrapper.class);
            }
            break;
            case EVENT_DETAIL:
            {
                copyFields(Controller.dataObjectBuilder.fromJson(response.toString(), Event.class));
            }
            break;
        }
	}

    public void setReadRequestParams(RESOURCE_TYPE requestType, String id)
    {
        this.id = id;
        this.requestType = requestType;
    }

    public void setReadRequestParams(RESOURCE_TYPE requestType)
    {
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

        switch(requestType)
        {
            case EVENT:
            {
                uriBuilder.appendPath(Urls.PATH.EVENT);
            }
            break;
            case EVENT_DETAIL:
            {
                uriBuilder.appendPath(Urls.PATH.EVENT);
                uriBuilder.appendPath(String.valueOf(id));
            }
            break;
            case MY_EVENTS_FEED:
            {
                uriBuilder.appendPath(Urls.PATH.EVENT);
                uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.ADMIN_ID, Settings.Account.id);
            }
            break;
            case JOINED_EVENTS_FEED:
            {
                uriBuilder.appendPath(Urls.PATH.JOINED_EVENT);
            }
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
            String name,
            String description,
            String location,
            String time_from,
            String time_to,
            int visibility
    )
    {
        this.name = name;
        this.description = description;
        this.location = location;
        this.time_to = time_to;
        this.time_from = time_from;
        this.visibility = visibility;
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
                .appendPath(Urls.PATH.EVENT)
                .appendPath("");
        requestUrl = uriBuilder.build().toString();
        Log.d(Debug.TAG.NET, requestUrl);

        final Event data = new Event(context);
        data.copyFields(this);
		StringRequest request = new StringRequest(Method.POST, requestUrl,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						changeStatus(Status.CREATE_OK);
					}
				}, new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{
                        if (error.networkResponse.statusCode == 500)
                        {
                            changeStatus(Status.ERROR_NETWORK);
                        }
						changeStatus(Status.ERROR);
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

    @Override
    public boolean equals(Object o)
    {
       if(((Event)o).id.equals(this.id))
       {
           return true;
       }
       return false;
    }

    public static class Join implements DataObject
    {
        @Expose
        public List<Integer> users;

        @Expose
        public List<Integer> events;

        public class JoinList
        {
            @Expose
            public List<Join> objects;
        }

        void copyFields(Join join)
        {
            this.users = join.users;
            this.events = join.events;
        }

        public static enum RESOURCE_TYPE
        {
            EVENT_JOIN,
            EVENT_JOIN_DETAIL
        }

        public int id;
        public String resourceId;
        DataObject.Status status;
        String requestUrl;
        RESOURCE_TYPE requestType;
        JSONObject response;
        SQLCache joinCache;
        Join join;
        Context context;

        public Join(Context context)
        {
            this.context = context;
            joinCache = new SQLCache(context, Urls.PATH.EVENT_JOIN);
        }

        @Override
        public void refresh()
        {
            switch(requestType)
            {
                case EVENT_JOIN:
                {
                    JoinList joinList = Controller.dataObjectBuilder.fromJson(response.toString(), JoinList.class);
                    copyFields(joinList.objects.get(0));
                }
                break;
                case EVENT_JOIN_DETAIL:
                {
                    join = Controller.dataObjectBuilder.fromJson(response.toString(), Join.class);
                    copyFields(join);
                }
                break;
            }
        }


        public void setReadRequestParams(RESOURCE_TYPE requestType, String eventId)
        {
            this.resourceId = eventId;
            this.requestType = requestType;
        }

        @Override
        public void read()
        {
            Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                    .buildUpon()
                    .appendPath(Urls.API)
                    .appendPath(Urls.VERSION)
                    .appendPath(Urls.PATH.EVENT_JOIN)
                    .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                    .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                    .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);

            switch(requestType)
            {
                case EVENT_JOIN:
                {
                    uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId);

                }
                break;
                case EVENT_JOIN_DETAIL:
                {
                    uriBuilder.appendPath(resourceId);
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

        public void setCreateRequestParams(RESOURCE_TYPE requestType, String resourceId)
        {
            this.requestType = requestType;
            this.resourceId = resourceId;
        }

        void buildPostData()
        {
            this.events = new ArrayList<Integer>();
            events.add(Integer.valueOf(resourceId));

            join = new Join(context);
            join.copyFields(this);
        }

        @Override
        public void create()
        {
            Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                    .buildUpon()
                    .appendPath(Urls.API)
                    .appendPath(Urls.VERSION)
                    .appendPath(Urls.PATH.EVENT_JOIN)
                    .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                    .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                    .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey)
                    .appendPath("");
            requestUrl = uriBuilder.build().toString();
            Log.d(Debug.TAG.NET, requestUrl);

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
                        buildPostData();
                        Log.d("json", new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation().create()
                                .toJson(join));
                        return new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation().create()
                                .toJson(join)
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

    public static class Volunteer implements DataObject
    {
        @Expose
        public List<Integer> users;

        @Expose
        public List<Integer> events;

        public class VolunteerList
        {
            @Expose
            public List<Volunteer> objects;
        }

        void copyFields(Volunteer volunteer)
        {
            this.users = volunteer.users;
            this.events = volunteer.events;
        }

        public static enum RESOURCE_TYPE
        {
            EVENT_VOLUNTEER
        }

        public int id;
        public String resourceId;
        DataObject.Status status;
        String requestUrl;
        RESOURCE_TYPE requestType;
        JSONObject response;
        SQLCache volunteerCache;
        Volunteer volunteer;
        Context context;

        public Volunteer(Context context)
        {
            this.context = context;
            volunteerCache = new SQLCache(context, Urls.PATH.EVENT_VOLUNTEER);
        }

        @Override
        public void refresh()
        {
            switch(requestType)
            {
                case EVENT_VOLUNTEER:
                {
                    VolunteerList volunteerList = Controller.dataObjectBuilder.fromJson(response.toString(), VolunteerList.class);
                    copyFields(volunteerList.objects.get(0));
                }
                break;
            }
        }


        public void setReadRequestParams(RESOURCE_TYPE requestType, String eventId)
        {
            this.resourceId = eventId;
            this.requestType = requestType;
        }

        @Override
        public void read()
        {
            Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                    .buildUpon()
                    .appendPath(Urls.API)
                    .appendPath(Urls.VERSION)
                    .appendPath(Urls.PATH.EVENT_VOLUNTEER)
                    .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                    .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                    .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);

            switch(requestType)
            {
                case EVENT_VOLUNTEER:
                {
                    uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId);
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

        public void setCreateRequestParams(RESOURCE_TYPE requestType, String eventId, int userIds[])
        {
            this.requestType = requestType;
            this.resourceId = eventId;
            if(this.users == null)
            {
                this.users = new ArrayList<Integer>();
            }
            for(int user: userIds)
            {
                this.users.add(user);
            }
        }

        @Override
        public void create()
        {
            Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                    .buildUpon()
                    .appendPath(Urls.API)
                    .appendPath(Urls.VERSION)
                    .appendPath(Urls.PATH.EVENT_VOLUNTEER)
                    .appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId)
                    .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                    .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                    .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey)
                    .appendPath("");
            requestUrl = uriBuilder.build().toString();
            Log.d(Debug.TAG.NET, requestUrl);

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
                                .toJson(volunteer));
                        return new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation().create()
                                .toJson(volunteer)
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

    public static class Attended implements DataObject
    {
        @Expose
        public List<Integer> users;

        public class AttendedList
        {
            @Expose
            public List<Attended> objects;
        }

        void copyFields(Attended attended)
        {
            this.users = attended.users;
        }

        public static enum RESOURCE_TYPE
        {
            EVENT_ATTENDED
        }

        public int id;
        public String resourceId;
        DataObject.Status status;
        String requestUrl;
        RESOURCE_TYPE requestType;
        JSONObject response;
        SQLCache attendedCache;
        Volunteer volunteer;
        Context context;

        public Attended(Context context)
        {
            this.context = context;
            attendedCache = new SQLCache(context, Urls.PATH.EVENT_ATTENDED);
        }

        @Override
        public void refresh()
        {
            switch(requestType)
            {
                case EVENT_ATTENDED:
                {
                    AttendedList attendedList = Controller.dataObjectBuilder.fromJson(response.toString(), AttendedList.class);
                    copyFields(attendedList.objects.get(0));
                }
                break;
            }
        }


        public void setReadRequestParams(RESOURCE_TYPE requestType, String eventId)
        {
            this.resourceId = eventId;
            this.requestType = requestType;
        }

        @Override
        public void read()
        {
            Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                    .buildUpon()
                    .appendPath(Urls.API)
                    .appendPath(Urls.VERSION)
                    .appendPath(Urls.PATH.EVENT_ATTENDED)
                    .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                    .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                    .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey);

            switch(requestType)
            {
                case EVENT_ATTENDED:
                {
                    uriBuilder.appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId);
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

        public void setCreateRequestParams(RESOURCE_TYPE requestType, String eventId, int userId)
        {
            this.requestType = requestType;
            this.resourceId = eventId;
            if(this.users == null)
            {
                this.users = new ArrayList<Integer>();
            }
            this.users.add(userId);
        }

        @Override
        public void create()
        {
            Uri.Builder uriBuilder = Uri.parse(Urls.SERVER)
                    .buildUpon()
                    .appendPath(Urls.API)
                    .appendPath(Urls.VERSION)
                    .appendPath(Urls.PATH.EVENT_ATTENDED)
                    .appendQueryParameter(Urls.REQUEST_KEY.EVENT, resourceId)
                    .appendQueryParameter(Urls.REQUEST_KEY.FORMAT, "json")
                    .appendQueryParameter(Urls.REQUEST_KEY.USERNAME, Settings.Account.username)
                    .appendQueryParameter(Urls.REQUEST_KEY.API_KEY, Settings.Account.apiKey)
                    .appendPath("");
            requestUrl = uriBuilder.build().toString();
            Log.d(Debug.TAG.NET, requestUrl);

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
                                .toJson(volunteer));
                        return new GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation().create()
                                .toJson(volunteer)
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

}
