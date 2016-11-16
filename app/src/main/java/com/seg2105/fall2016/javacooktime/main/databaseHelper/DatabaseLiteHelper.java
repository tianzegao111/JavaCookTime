package com.seg2105.fall2016.javacooktime.main.databaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by markmroz on 2016-11-16.
 */

public class DatabaseLiteHelper extends SQLiteOpenHelper {

    // Database
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "RecipieDB";

    // Recipes Table
    public static final String TABLE_RECIPES = "recipes";
    private static final String COLUMN_RECIPE_ID = "id";
    private static final String COLUMN_RECIPE_NAME = "name";
    private static final String COLUMN_RECIPE_CATEGORY = "category";
    private static final String COLUMN_RECIPE_DISHTYPE = "dishType";
    private static final String COLUMN_RECIPE_IMAGE = "image";
    private static final String COLUMN_RECIPE_STEPS = "steps";
    private static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";

    // Ingridient Table
    public static final String TABLE_INGREDIENTS = "ingredient";
    private static final String COLUMN_INGREDIENTS_ID = "id";
    private static final String COLUMN_INGREDIENTS_NAME = "name";
    private static final String COLUMN_INGREDIENTS_AMOUNT = "amount";
    private static final String COLUMN_INGREDIENTS_MEASUMENTTYPE = "measurment_type";
    private static final String COLUMN_INGREDIENTS_RECIPE = "recipe_id";

    // Instruction Table
    public static final String TABLE_INSTRUCTIONS = "instructions";
    private static final String COLUMN_INSTRUCTIONS_ID = "id";
    private static final String COLUMN_INSTRUCTIONS_CONTENT = "content";
    private static final String COLUMN_INSTRUCTIONS_POSITION = "position";
    private static final String COLUMN_INSTRUCTIONS_RECIPE = "recipe_id";


    // SQL statement for creating Recipes Table

    private static final String SQL_CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES +" ( " +
            COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_RECIPE_NAME + " TEXT, " +
            COLUMN_RECIPE_CATEGORY +" TEXT, " +
            COLUMN_RECIPE_DISHTYPE + " TEXT, " +
            COLUMN_RECIPE_IMAGE + " TEXT, " +
            COLUMN_RECIPE_STEPS + " TEXT, " +
            COLUMN_RECIPE_INGREDIENTS + " TEXT )";

    // SQL statement for creating Ingredients Table

    private static final String SQL_CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + TABLE_INGREDIENTS +" ( " +
            COLUMN_INGREDIENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_INGREDIENTS_NAME + " TEXT, " +
            COLUMN_INGREDIENTS_AMOUNT +" TEXT, " +
            COLUMN_INGREDIENTS_MEASUMENTTYPE + " TEXT, " +
            COLUMN_INGREDIENTS_RECIPE + " TEXT )";

    // SQL statement for creating Instruction Table

    private static final String SQL_CREATE_INSTRUCTIONS_TABLE = "CREATE TABLE " + TABLE_INSTRUCTIONS +" ( " +
            COLUMN_INSTRUCTIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_INSTRUCTIONS_CONTENT + " TEXT, " +
            COLUMN_INSTRUCTIONS_POSITION +" TEXT, " +
            COLUMN_INSTRUCTIONS_RECIPE + " TEXT )";

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
    }

    //On Upgrade

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop recipies table if already exists
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_RECIPES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_INGREDIENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SQL_CREATE_INSTRUCTIONS_TABLE);

        //Create new
        this.onCreate(db);
    }


}
