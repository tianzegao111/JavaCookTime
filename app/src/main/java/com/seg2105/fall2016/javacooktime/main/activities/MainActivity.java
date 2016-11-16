package com.seg2105.fall2016.javacooktime.main.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.*;
import com.seg2105.fall2016.javacooktime.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        // Add your initialization code here
        Parse.initialize(this, "b4qmxSiH1fpSdn4Qx42bGfi7KiecbxF5AE0DXHbQ" , "JW7JvrdR6BOkMMqrDytjo7NStTKC7ThG0Z2pCA05");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

//        Parse.enableLocalDatastore(getApplicationContext());
//        Parse.initialize(this, "b4qmxSiH1fpSdn4Qx42bGfi7KiecbxF5AE0DXHbQ" , "JW7JvrdR6BOkMMqrDytjo7NStTKC7ThG0Z2pCA05");
//        ParseInstallation.getCurrentInstallation().saveInBackground();
//
        ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();
    }
}
