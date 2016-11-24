package com.seg2105.fall2016.javacooktime.main.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.seg2105.fall2016.javacooktime.R;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    String imgDecodableString;

    ArrayAdapter<String> ingredientAdapter;
    ArrayList<String> ingredientList;
    EditText ingredientAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //NEEDS TO BE FIXED
        ingredientList = new ArrayList<String>();
        ingredientAdapter = new ArrayAdapter<String>(this,R.layout.activity_add,R.id.ingredients_view);
        ListView ingredients_view = (ListView)findViewById(R.id.ingredients_view);
        ingredientAdd = (EditText)findViewById(R.id.ingredients_edit);
        Button addButton = (Button)findViewById(R.id.addButton);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText)findViewById(R.id.ingredients_edit);
                ingredientList.add(edit.getText().toString());
                edit.setText("");
                ingredientAdapter.notifyDataSetChanged();
            }
        };
        addButton.setOnClickListener(listener);

        ingredients_view.setAdapter(ingredientAdapter);

    }

    public void loadImageFromGallery(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.recipePhoto);
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            } else {
                Toast.makeText(this, "You haven't picked an image", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
