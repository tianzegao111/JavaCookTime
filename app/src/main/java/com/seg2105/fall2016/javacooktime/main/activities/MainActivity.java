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

        RecipeModel r1;
        RecipeModel r2;
        RecipeModel r3;

        ArrayList<IngredientModel> r1Ingredients = new ArrayList<IngredientModel>();

        r1Ingredients.add(new IngredientModel("Tomato", 5, ""));
        r1Ingredients.add(new IngredientModel("Water", 3, "cups"));
        r1Ingredients.add(new IngredientModel("Onion", 1, ""));

        ArrayList<IngredientModel> r2Ingredients = new ArrayList<IngredientModel>();

        r2Ingredients.add(new IngredientModel("Tomato", 1, ""));
        r2Ingredients.add(new IngredientModel("Lettuce", 1, ""));
        r2Ingredients.add(new IngredientModel("Burger", 2, ""));
        r2Ingredients.add(new IngredientModel("Bun", 2, ""));

        ArrayList<IngredientModel> r3Ingredients = new ArrayList<IngredientModel>();

        r3Ingredients.add(new IngredientModel("Lettuce", 1, "lbs"));
        r3Ingredients.add(new IngredientModel("Rice Paper", 12, ""));
        r3Ingredients.add(new IngredientModel("Shrimp", 2, "lbs"));
        r3Ingredients.add(new IngredientModel("Bean Sprouts", 2, "lbs"));

        ArrayList<InstructionModel> r1Instructions = new ArrayList<InstructionModel>();

        r1Instructions.add(new InstructionModel(1,"Chop vegetables"));
        r1Instructions.add(new InstructionModel(2,"Add to pot"));
        r1Instructions.add(new InstructionModel(3,"Boil 20 minutes"));

        ArrayList<InstructionModel> r2Instructions = new ArrayList<InstructionModel>();

        r2Instructions.add(new InstructionModel(1,"Cook meat"));
        r2Instructions.add(new InstructionModel(2,"Grill bun"));
        r2Instructions.add(new InstructionModel(3,"serve with lettuce and tomato"));

        ArrayList<InstructionModel> r3Instructions = new ArrayList<InstructionModel>();

        r3Instructions.add(new InstructionModel(1,"Cook shrimp"));
        r3Instructions.add(new InstructionModel(2,"Add to rice paper"));
        r3Instructions.add(new InstructionModel(3,"Roll rice paper"));

        r1 = new RecipeModel("Tomato Soup", "American", "Soup", "", r1Instructions, r1Ingredients);

        r2 = new RecipeModel("Hamburger", "American", "Main Dish", "", r2Instructions , r2Ingredients);

        r3 = new RecipeModel("Salad Role", "Vietnamese", "Appatizer", "", r3Instructions, r3Ingredients);

        database.addToRecipeToDatabase(r1);
        database.addToRecipeToDatabase(r2);
        database.addToRecipeToDatabase(r3);

    }
}
