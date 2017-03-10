package com.github.chizoba.alcchallenge;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileDetails extends AppCompatActivity {

    TextView usernameTextView, profileUrlTextView;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        getSupportActionBar().setTitle("Profile");

        initializedViews();
        Intent receivedIntent = getIntent();

        if(receivedIntent.hasExtra(Intent.EXTRA_TEXT)){
            String username = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
            String avatarUrl = receivedIntent.getStringExtra("image");
            String profileUrl = receivedIntent.getStringExtra("url");

//            ArrayList<User> us = new ArrayList<User>(Arrays.asList(user.split(",")));
//            Toast.makeText(this, user, Toast.LENGTH_LONG).show();

            usernameTextView.setText(username);
            Picasso.with(this).load(avatarUrl).into(avatar);
            profileUrlTextView.setText(profileUrl);
        }


    }

    private void initializedViews() {
        usernameTextView = (TextView) findViewById(R.id.username_tv );
        avatar = (ImageView) findViewById(R.id.avatar_img);
        profileUrlTextView = (TextView) findViewById(R.id.profile_url);
    }

//    private void openWebPage(String url){
//        Uri webpage = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//
//        if(intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

//    @Override
//    public void onClick(View view) {
//        int viewId = view.getId();
//        if (viewId == R.id.profile_url){
//            openWebPage(profileUrlTextView.getText().toString());
//        }
//    }

    public void share(View view) {
        String mimeType = "text/plain";
        String title = "Share to";
        String textToShare = "Check out this awesome developer @" + usernameTextView.getText().toString() + ", " + profileUrlTextView.getText().toString();

        ShareCompat.IntentBuilder.from(this)
                .setChooserTitle(title)
                .setType(mimeType)
                .setText(textToShare)
                .startChooser();
    }
}
