package com.seg2105.fall2016.javacooktime.main.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.seg2105.fall2016.javacooktime.main.model.IngredientModel;
import com.seg2105.fall2016.javacooktime.main.model.InstructionModel;
import com.seg2105.fall2016.javacooktime.main.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

import static com.seg2105.fall2016.javacooktime.main.databaseHelper.DatabaseTables.*;


/**
 * Created by markmroz on 2016-11-16.
 */

public class DatabaseLiteHelper extends SQLiteOpenHelper {

    // Database
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "RecipeDB";

    // SQL statement for creating Recipes Table

    private static final String SQL_CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES +" ( " +
            COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_RECIPE_NAME + " TEXT, " +
            COLUMN_RECIPE_CATEGORY +" TEXT, " +
            COLUMN_RECIPE_DISHTYPE + " TEXT, " +
            COLUMN_RECIPE_IMAGE + " TEXT )";

    // SQL statement for creating Ingredients Table

    private static final String SQL_CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + TABLE_INGREDIENTS +" ( " +
            COLUMN_INGREDIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_INGREDIENT_NAME + " TEXT )";

    // SQL statement for creating Instruction Table

    private static final String SQL_CREATE_INSTRUCTIONS_TABLE = "CREATE TABLE " + TABLE_INSTRUCTIONS +" ( " +
            COLUMN_INSTRUCTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_INSTRUCTION_CONTENT + " TEXT, " +
            COLUMN_INSTRUCTION_POSITION +" INTEGER, " +
            COLUMN_INSTRUCTION_RECIPE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_INSTRUCTION_RECIPE + ") REFERENCES " + TABLE_RECIPES + "("  + COLUMN_RECIPE_ID + ") )";

    // SQL statement for creating Recipe Ingredient Relation Table

    private static final String SQL_CREATE_RECIPE_INGREDIENT_TABLE = "CREATE TABLE " + TABLE_RECIPIES_INGREDIENTS +" ( " +
            COLUMN_RECIPIE_INGREDIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_RECIPIE_INGREDIENT_RECIPE_ID + " INTEGER, " +
            COLUMN_RECIPIE_INGREDIENT_INGREDIENT_ID + " INTEGER, " +
            COLUMN_RECIPIES_INGREDIENT_AMOUNT + " INTEGER, " +
            COLUMN_RECIPIES_INGREDIENT_MEASUMENTTYPE + " TEXT, " +
            "FOREIGN KEY(" + COLUMN_RECIPIE_INGREDIENT_RECIPE_ID + ") REFERENCES " + TABLE_RECIPES + "("  + COLUMN_RECIPE_ID + ")" +
            "FOREIGN KEY(" + COLUMN_RECIPIE_INGREDIENT_INGREDIENT_ID + ") REFERENCES " + TABLE_INGREDIENTS + "("  + COLUMN_INGREDIENT_ID + ") )";

    // Initialization

    public DatabaseLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    // Required method implementation
    // On Create

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_RECIPES_TABLE);
        db.execSQL(SQL_CREATE_INGREDIENTS_TABLE);
        db.execSQL(SQL_CREATE_INSTRUCTIONS_TABLE);
        db.execSQL(SQL_CREATE_RECIPE_INGREDIENT_TABLE);
    }

    //On Upgrade

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop recipies table if already exists
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_RECIPES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_INGREDIENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_INSTRUCTIONS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_RECIPE_INGREDIENT_TABLE);

        //Create new
        this.onCreate(db);
    }

    // Add to database

    /**
     * Add a recipe to the proper tables in the database
     * @param recipe the recipe to add
     * @return true if everything is added correctly
     */

    public boolean addToRecipeToDatabase(RecipeModel recipe) {


        // add recipe to recipe database

        if (!addToRecipeInfoDatabase(recipe)) {
            System.out.print("Failed to add recipe info");
            return false;
        }

        // add the instruction with the proper for the newest recipe to the instruction database

        int relationalRecipeId = Integer.parseInt(retrieveLastInputFor(getAllRecipeData(),COLUMN_RECIPE_ID));

        for (InstructionModel instruction : recipe.getSteps()) {
            if (!addToInstructionTable(instruction, relationalRecipeId)) {
                System.out.print("Failed to add instruction");
                return false;
            }
        }

        int relationalIngredientId = -1;

        // add to ingredient database if it is not already present otherwise skip

        for (IngredientModel ingredient : recipe.getIngredients()) {

            relationalIngredientId = ingredientIdByName(ingredient.getName());

            if (relationalIngredientId == -1 ) {
                if (!addToIngredientTable(ingredient)) {
                    System.out.print("Failed to add ingredient");
                    return false;
                }
                relationalIngredientId = Integer.parseInt(retrieveLastInputFor(getAllIngredients(),COLUMN_INGREDIENT_ID));
            }

            if (!addToRecipeIngredientTable(relationalRecipeId, relationalIngredientId, ingredient.getAmount(), ingredient.getMeasumentStandard())) {
                System.out.print("Failed to add to relational database");
                return false;
            }
        }

        return true;
    }


    /**
     * add the recipe portion to the recipe databases
     * @param recipe to store
     * @return true if successful
     */

    private boolean addToRecipeInfoDatabase(RecipeModel recipe) {

        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues recipeValues = new ContentValues();

        recipeValues.put(COLUMN_RECIPE_NAME, recipe.getName());
        recipeValues.put(COLUMN_RECIPE_CATEGORY, recipe.getName());
        recipeValues.put(COLUMN_RECIPE_CATEGORY, recipe.getCategory());
        recipeValues.put(COLUMN_RECIPE_DISHTYPE, recipe.getDishType());
        recipeValues.put(COLUMN_RECIPE_IMAGE, recipe.getImage());


        createSuccessful = db.insert(TABLE_RECIPES, null, recipeValues) > 0;
        db.close();

        return createSuccessful;
    }

    /**
     * add the ingredient to the ingredients database
     * @param ingredient to store
     * @return true if successful
     */

    private boolean addToIngredientTable(IngredientModel ingredient) {

        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues recipeValues = new ContentValues();

        recipeValues.put(COLUMN_INGREDIENT_NAME, ingredient.getName());

        createSuccessful = db.insert(TABLE_INGREDIENTS, null, recipeValues) > 0;
        db.close();

        return createSuccessful;

    }

    /**
     * add the ingredient to the instructions database
     * @param instruction to store
     * @return true if successful
     */

    private boolean addToInstructionTable(InstructionModel instruction, int recipeId) {

        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues recipeValues = new ContentValues();

        recipeValues.put(COLUMN_INSTRUCTION_POSITION, instruction.getPosition());
        recipeValues.put(COLUMN_INSTRUCTION_CONTENT, instruction.getContent());
        recipeValues.put(COLUMN_INSTRUCTION_RECIPE,recipeId);

        createSuccessful = db.insert(TABLE_INSTRUCTIONS, null, recipeValues) > 0;
        db.close();

        return createSuccessful;

    }

    private boolean addToRecipeIngredientTable(int recipeId, int ingredientId, int amount, String measurmentType) {
        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues recipeValues = new ContentValues();

        recipeValues.put(COLUMN_RECIPIE_INGREDIENT_RECIPE_ID, recipeId);
        recipeValues.put(COLUMN_RECIPIE_INGREDIENT_INGREDIENT_ID, ingredientId);
        recipeValues.put(COLUMN_RECIPIES_INGREDIENT_AMOUNT, amount);
        recipeValues.put(COLUMN_RECIPIES_INGREDIENT_MEASUMENTTYPE, measurmentType);

        createSuccessful = db.insert(TABLE_RECIPIES_INGREDIENTS, null, recipeValues) > 0;
        db.close();

        return createSuccessful;
    }

    // Get data

    public int ingredientIdByName(String ingredientName) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ COLUMN_INGREDIENT_ID +" FROM "+ TABLE_INGREDIENTS +" WHERE "+ COLUMN_INGREDIENT_NAME +" = '" + ingredientName + "'", null);


        if(cursor.moveToFirst()){
            do{

                return cursor.getInt(0);

            }while(cursor.moveToNext());
        }
        cursor.close();

        return -1;

    }

    public String retrieveLastInputFor(Cursor cursor, String tableColumn) {

        String retrievedValue = "";

        if (cursor.moveToLast()){
            retrievedValue = cursor.getString(cursor.getColumnIndex(tableColumn));
        }
        cursor.close();
        return retrievedValue;
    }

    public Cursor getAllRecipeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from " + TABLE_RECIPES,null);
    }


    public Cursor getAllIngredients() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from " + TABLE_INGREDIENTS,null);
    }

    public Cursor getAllInstructions() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from " + TABLE_INSTRUCTIONS,null);
    }

    public Cursor getAllRecipeIngredients() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from " + TABLE_RECIPIES_INGREDIENTS,null);
    }
}
