package com.seg2105.fall2016.javacooktime.main.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.*;
import com.seg2105.fall2016.javacooktime.R;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Parse.initialize(this, "b4qmxSiH1fpSdn4Qx42bGfi7KiecbxF5AE0DXHbQ" , "JW7JvrdR6BOkMMqrDytjo7NStTKC7ThG0Z2pCA05");
    }
}
