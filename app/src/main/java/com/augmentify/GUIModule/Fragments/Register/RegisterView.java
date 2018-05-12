package com.augmentify.GUIModule.Fragments.Register;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.augmentify.DataModule.Objects.User.ApiKey;
import com.augmentify.DataModule.Objects.User.Profile;
import com.augmentify.DataModule.Settings;
import com.augmentify.Debug;
import com.augmentify.GUIModule.Fragments.FragmentController;
import com.augmentify.GUIModule.Utils.DisplayUtils;
import com.augmentify.R;
import com.augmentify.Resource;

/**
 * Created by Saurabh on 26/07/2014.
 */
public class RegisterView extends RelativeLayout
{
    Context context;
    public RegisterView(Context context)
    {
        super(context);
        this.context = context;
        RegisterViewResource.context = context;
    }

    TextView title;
    View horizontalSeparator;
    EditText name;
    RadioGroup sexGroup;
    RadioButton male;
    RadioButton female;
    EditText email;
    EditText username;
    EditText password;
    RelativeLayout registerLayout;
    ImageView registerImage;
    TextView register;

    static LayoutParams params;

    Profile profile;

    public void build()
    {
        title = new TextView(context);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, RegisterViewResource.Title.fontSize);
        title.setText(RegisterViewResource.Title.text);
        title.setTextColor(Color.parseColor(RegisterViewResource.Title.fontColor));
        title.setPadding(RegisterViewResource.Title.Padding.left,
                RegisterViewResource.Title.Padding.top,
                RegisterViewResource.Title.Padding.right,
                RegisterViewResource.Title.Padding.bottom);
        title.setId(Resource.id++);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        this.addView(title, params);

        horizontalSeparator = new View(context);
        horizontalSeparator.setId(Resource.id++);
        horizontalSeparator
                .setBackgroundResource(R.drawable.horizontal_separator);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.height = RegisterViewResource.HorizontalSeparator.height;
        params.addRule(RelativeLayout.BELOW, title.getId());
        this.addView(horizontalSeparator, params);

        name = new EditText(context);
        name.setId(Resource.id++);
        name.setHint(RegisterViewResource.Name.text);
        name.setHintTextColor(Color
                .parseColor(RegisterViewResource.Name.hintFontColor));
        name.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                RegisterViewResource.Name.fontSize);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, horizontalSeparator.getId());
        this.addView(name, params);

        sexGroup = new RadioGroup(context);
        sexGroup.setId(Resource.id++);
        sexGroup.setOrientation(RadioGroup.HORIZONTAL);

        male = new RadioButton(context);
        male.setId(Resource.id++);
        male.setText(RegisterViewResource.Male.text);
        male.setTextColor(Color.parseColor(RegisterViewResource.Male.fontColor));
        male.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                RegisterViewResource.Male.fontSize);
        sexGroup.addView(male);

        female = new RadioButton(context);
        female.setId(Resource.id++);
        female.setText(RegisterViewResource.Female.text);
        female.setTextColor(Color.parseColor(RegisterViewResource.Female.fontColor));
        female.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                RegisterViewResource.Female.fontSize);
        sexGroup.addView(female);

        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, name.getId());
        this.addView(sexGroup, params);

        email = new EditText(context);
        email.setId(Resource.id++);
        email.setHint(RegisterViewResource.Email.text);
        email.setHintTextColor(Color
                .parseColor(RegisterViewResource.Email.hintFontColor));
        email.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                RegisterViewResource.Email.fontSize);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, sexGroup.getId());
        this.addView(email, params);

        username = new EditText(context);
        username.setId(Resource.id++);
        username.setHint(RegisterViewResource.Username.text);
        username.setHintTextColor(Color
                .parseColor(RegisterViewResource.Username.hintFontColor));
        username.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                RegisterViewResource.Username.fontSize);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, email.getId());
        this.addView(username, params);

        password = new EditText(context);
        password.setId(Resource.id++);
        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.setHint(RegisterViewResource.Password.text);
        password.setHintTextColor(Color
                .parseColor(RegisterViewResource.Password.hintFontColor));
        password.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                RegisterViewResource.Password.fontSize);
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, username.getId());
        this.addView(password, params);

        registerLayout = new RelativeLayout(context);
        registerLayout.setId(Resource.id++);

        registerImage = new ImageView(context);
        registerImage.setId(Resource.id++);
        registerImage.setImageResource(R.drawable.card_event_image);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.height = RegisterViewResource.Register.Image.height;
        params.width = RegisterViewResource.Register.Image.width;
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        registerLayout.addView(registerImage, params);

        register = new TextView(context);
        register.setId(Resource.id++);
        register.setText(RegisterViewResource.Register.text);
        register.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                RegisterViewResource.Register.fontSize);
        register.setTextColor(Color
                .parseColor(RegisterViewResource.Register.fontColor));
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.RIGHT_OF, registerImage.getId());
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        registerLayout.addView(register, params);

        registerLayout
                .setOnTouchListener(new DisplayUtils.OnTouchButtonBackgroundSet());
        registerLayout.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(buildDataFromView())
                {
                    profile.create();
                }
            }
        });
        registerLayout.setBackgroundResource(R.drawable.button);
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, password.getId());
        this.addView(registerLayout, params);

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setId(Resource.id++);
    }

    public boolean buildDataFromView()
    {
        if(name.getText().toString().matches(""))
        {
            name.setHintTextColor(Color.parseColor(RegisterViewResource.Name.hintErrorFontColor));
            return false;
        }
        if(!male.isChecked()&&!female.isChecked())
        {
            male.setTextColor(Color.parseColor(RegisterViewResource.Male.hintErrorFontColor));
            female.setTextColor(Color.parseColor(RegisterViewResource.Male.hintErrorFontColor));
            return false;
        }
        if(email.getText().toString().matches(""))
        {
            email.setHintTextColor(Color.parseColor(RegisterViewResource.Email.hintErrorFontColor));
            return false;
        }
        if(username.getText().toString().matches(""))
        {
            username.setHintTextColor(Color.parseColor(RegisterViewResource.Username.hintErrorFontColor));
            return false;
        }
        Log.d(Debug.TAG.GUI, username.getText().toString());
        if(password.getText().toString().matches(""))
        {
            password.setHintTextColor(Color.parseColor(RegisterViewResource.Password.hintErrorFontColor));
            return false;
        }

        String gender = male.isChecked()? "M":"F";
        String lastName;
        try
        {
            lastName = name.getText().toString().split(" ")[1];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            lastName = "";
        }

        profile = new Profile(context)
        {
            @Override
            public void onStatusChange(Status status)
            {
                switch(status)
                {
                    case CREATE_OK:
                    {
                        ApiKey apiKey = new ApiKey(context)
                        {
                            @Override
                            public void onStatusChange(Status status)
                            {
                                switch (status)
                                {
                                    case ERROR:
                                    {
                                        super.changeStatus(status);
                                    }
                                }
                            }
                        };
                        apiKey.setReadRequestParams(ApiKey.RESOURCE_TYPE.API_KEY, username.getText().toString(), password.getText().toString(), 0);
                        apiKey.read();
                        Settings.Account.set(apiKey.username, apiKey.key);
                        FragmentController.dismiss(FragmentController.Fragment.REGISTER_VIEW_FRAGMENT);
                        FragmentController.dismiss(FragmentController.Fragment.LOGIN_VIEW_FRAGMENT);
                    }
                    break;
                    case ERROR:
                    {
                        Toast.makeText(context, "Network Problem", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        profile.setCreateRequestParams(username.getText().toString(),
                password.getText().toString(),
                email.getText().toString(),
                gender,
                name.getText().toString().split(" ")[0],
                lastName);
        return true;
    }
}
