package com.augmentify.DataModule.Objects.Card;

import com.augmentify.DataModule.Objects.DataObject;
import com.augmentify.GUIModule.Card.CardView;
import com.augmentify.GUIModule.Card.Content.SayView;
import com.google.gson.annotations.Expose;

public class Say implements DataObject
{
	@Expose
	public String id;
	@Expose
	public String say;
	@Expose
	public String resource_uri;
	
	public SayView sayView;
    Status status;
	public void setView(CardView sayView)
	{
		this.sayView = (com.augmentify.GUIModule.Card.Content.SayView) sayView.decidedContent;
	}

	@Override
	public void refresh()
	{
		sayView.refresh(this);
	}

	public void create(final Say say)
	{
        /*
		StringRequest request = new StringRequest(Method.POST, Urls.SAY,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
                        refresh();
						//SayView.changeCreateStatus(Status.READ_OK);
					}
				}, new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{
						//SayView.changeCreateStatus(Status.ERROR);
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
							.toJson(say)
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
		Controller.networkRequestQueue.add(request);*/
	}

	@Override
	public void read()
	{

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
        this.status = status;
        this.onStatusChange(status);
    }
}
