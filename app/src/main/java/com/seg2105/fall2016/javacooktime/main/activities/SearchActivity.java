package com.seg2105.fall2016.javacooktime.main.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.seg2105.fall2016.javacooktime.R;
import com.seg2105.fall2016.javacooktime.main.model.RecipeModel;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //search input
        SearchView search = (SearchView)findViewById(R.id.searchField);

        //Category input
        Spinner categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        String[] categories = getResources().getStringArray(R.array.category_arrays);
        ArrayAdapter<CharSequence> categoryAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Dish Type input
        Spinner dishTypeSpinner = (Spinner)findViewById(R.id.dishTypeSpinner);
        String[] types = getResources().getStringArray(R.array.dishType_arrays);
        ArrayAdapter<CharSequence> dishTypeAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, types);
        dishTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dishTypeSpinner.setAdapter(dishTypeAdapter);
        dishTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Search results
        ListView listView = (ListView)findViewById(R.id.searchResults);
        RecipeModel[] values = new RecipeModel[]{}; //change to accept recipes from database
        SearchResultsArrayAdapter criteriaAdapter = new SearchResultsArrayAdapter(this, values);
        listView.setAdapter(criteriaAdapter);
    }
}
