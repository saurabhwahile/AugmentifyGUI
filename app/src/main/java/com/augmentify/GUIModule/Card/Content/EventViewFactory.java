package com.augmentify.GUIModule.Card.Content;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augmentify.GUIModule.Utils.Utils;
import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 05/08/2014.
 */
public class EventViewFactory
{
    Context context;
    RelativeLayout addTo;
    public EventViewFactory(Context context, RelativeLayout addTo)
    {
        this.context = context;
        this.addTo = addTo;
    }

    static RelativeLayout.LayoutParams params;

    public static class Time extends RelativeLayout
    {
        public ImageView timeImage;
        public TextView time;
        public Time(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            timeImage = new ImageView(context);
            timeImage.setImageResource(R.drawable.card_event_time);
            timeImage.setPadding(EventViewResource.Time.Image.Padding.left,
                    EventViewResource.Time.Image.Padding.top,
                    EventViewResource.Time.Image.Padding.right,
                    EventViewResource.Time.Image.Padding.bottom);
            timeImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = EventViewResource.Time.Image.height;
            params.width = EventViewResource.Time.Image.width;
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            this.addView(timeImage, params);

            time = new TextView(context);
            time.setText("10:00 am Tuesday");
            time.setTextSize(TypedValue.COMPLEX_UNIT_SP, EventViewResource.Time.fontSize);
            time.setTextColor(Color
                    .parseColor(EventViewResource.Time.fontColor));
            time.setPadding(EventViewResource.Time.Padding.left,
                    EventViewResource.Time.Padding.top,
                    EventViewResource.Time.Padding.right,
                    EventViewResource.Time.Padding.bottom);
            time.setId(Resource.id++);

            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.RIGHT_OF, timeImage.getId());
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            this.addView(time, params);

            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(EventViewResource.Time.Layout.Margin.left,
                    EventViewResource.Time.Layout.Margin.top,
                    EventViewResource.Time.Layout.Margin.right,
                    EventViewResource.Time.Layout.Margin.bottom);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);
            addTo.addView(this, params);

            this.setId(Resource.id++);
        }
    }

    public static class Venue extends RelativeLayout
    {
        public ImageView venueImage;
        public TextView venue;
        public Venue(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            venueImage = new ImageView(context);
            venueImage.setImageResource(R.drawable.card_event_venue);
            venueImage.setPadding(EventViewResource.Venue.Image.Padding.left,
                    EventViewResource.Venue.Image.Padding.top,
                    EventViewResource.Venue.Image.Padding.right,
                    EventViewResource.Venue.Image.Padding.bottom);
            venueImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = EventViewResource.Venue.Image.height;
            params.width = EventViewResource.Venue.Image.width;
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            this.addView(venueImage, params);

            venue = new TextView(context);
            venue.setText("Seminary Hills");
            venue.setTextSize(TypedValue.COMPLEX_UNIT_SP, EventViewResource.Time.fontSize);
            venue.setTextColor(Color
                    .parseColor(EventViewResource.Time.fontColor));
            venue.setPadding(EventViewResource.Venue.Padding.left,
                    EventViewResource.Venue.Padding.top,
                    EventViewResource.Venue.Padding.right,
                    EventViewResource.Venue.Padding.bottom);
            venue.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.RIGHT_OF, venueImage.getId());
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            this.addView(venue, params);

            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(EventViewResource.Venue.Layout.Margin.left,
                    EventViewResource.Venue.Layout.Margin.top,
                    EventViewResource.Venue.Layout.Margin.right,
                    EventViewResource.Venue.Layout.Margin.bottom);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);
            this.setId(Resource.id++);
            addTo.addView(this, params);
        }
    }

    public static class Description extends RelativeLayout
    {
        public ImageView descriptionImage;
        public TextView description;
        public Description(Context context, RelativeLayout addTo, int leftOf, int above, int rightOf, int below)
        {
            super(context);
            descriptionImage = new ImageView(context);
            descriptionImage.setImageResource(R.drawable.card_event_description);
            descriptionImage.setPadding(EventViewResource.Description.Image.Padding.left,
                    EventViewResource.Description.Image.Padding.top,
                    EventViewResource.Description.Image.Padding.right,
                    EventViewResource.Description.Image.Padding.bottom);
            descriptionImage.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = EventViewResource.Venue.Image.height;
            params.width = EventViewResource.Venue.Image.width;
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            this.addView(descriptionImage, params);

            description = new TextView(context);
            String descriptionSample =
                    "This is a sample very long text. This is interesting And The Amazing Part Of This";
            description.setText(Utils.textClipper(descriptionSample, 64, 3));
            description.setTextSize(TypedValue.COMPLEX_UNIT_SP, EventViewResource.Description.fontSize);
            description.setTextColor(Color.parseColor(EventViewResource.Description.fontColor));
            description.setPadding(EventViewResource.Description.Padding.left,
                    EventViewResource.Description.Padding.top,
                    EventViewResource.Description.Padding.right,
                    EventViewResource.Description.Padding.bottom);
            description.setId(Resource.id++);
            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.RIGHT_OF, descriptionImage.getId());
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            this.addView(description, params);

            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(EventViewResource.Description.Layout.Margin.left,
                    EventViewResource.Description.Layout.Margin.top,
                    EventViewResource.Description.Layout.Margin.right,
                    EventViewResource.Description.Layout.Margin.bottom);
            params.addRule(RelativeLayout.LEFT_OF, leftOf);
            params.addRule(RelativeLayout.ABOVE, above);
            params.addRule(RelativeLayout.RIGHT_OF, rightOf);
            params.addRule(RelativeLayout.BELOW, below);

            this.setId(Resource.id++);
            addTo.addView(this, params);
        }
    }

}
