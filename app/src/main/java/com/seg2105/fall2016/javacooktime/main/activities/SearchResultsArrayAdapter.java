package com.seg2105.fall2016.javacooktime.main.activities;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.seg2105.fall2016.javacooktime.R;
import com.seg2105.fall2016.javacooktime.main.model.RecipeModel;

/**
 * Created by limeg on 2016-11-19.
 */

public class SearchResultsArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final RecipeModel[] values;

    public SearchResultsArrayAdapter(Context context, RecipeModel[] values) {
        super(context, R.layout.search_result_layout, values);
        this.context = context;
        this.values = values;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.search_result_layout, parent, false);

        TextView nameView = (TextView) rowView.findViewById(R.id.recipeTitle);
        TextView categoryView = (TextView) rowView.findViewById(R.id.categoryInfo);
        TextView dishTypeView = (TextView) rowView.findViewById(R.id.dishTypeInfo);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.recipePhoto);

        nameView.setText(values[position].getName());
        imageView.setImageResource(Integer.parseInt(values[position].getImage()));
        categoryView.setText(values[position].getCategory());
        dishTypeView.setText(values[position].getDishType());

        return rowView;
    }

}
