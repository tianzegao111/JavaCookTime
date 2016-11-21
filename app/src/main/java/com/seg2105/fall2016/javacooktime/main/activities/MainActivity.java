package com.seg2105.fall2016.javacooktime.main.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.parse.*;
import com.seg2105.fall2016.javacooktime.R;
import com.seg2105.fall2016.javacooktime.main.databaseHelper.DatabaseLiteHelper;
import com.seg2105.fall2016.javacooktime.main.model.IngredientModel;
import com.seg2105.fall2016.javacooktime.main.model.InstructionModel;
import com.seg2105.fall2016.javacooktime.main.model.RecipeModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseLiteHelper database = new DatabaseLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

    }
}
