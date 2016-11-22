package com.seg2105.fall2016.javacooktime.main.databaseHelper;

/**
 * Created by markmroz on 2016-11-18.
 */

public class DatabaseTables {

    // Recipes Table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_RECIPE_CATEGORY = "recipe_category";
    public static final String COLUMN_RECIPE_DISHTYPE = "recipe_dishType";
    public static final String COLUMN_RECIPE_IMAGE = "recipe_image";


    // Ingridient Table
    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_INGREDIENT_ID = "ingredient_id";
    public static final String COLUMN_INGREDIENT_NAME = "ingredient_name";

    // Instruction Table
    public static final String TABLE_INSTRUCTIONS = "instructions";
    public static final String COLUMN_INSTRUCTION_ID = "instruction_id";
    public static final String COLUMN_INSTRUCTION_CONTENT = "instruction_content";
    public static final String COLUMN_INSTRUCTION_POSITION = "instruction_position";
    public static final String COLUMN_INSTRUCTION_RECIPE = COLUMN_RECIPE_ID;

    //Recipe Ingredient Table
    public static final String TABLE_RECIPIES_INGREDIENTS = "recipes_ingredients";
    public static final String COLUMN_RECIPIE_INGREDIENT_ID = "recipe_ingredient_id";
    public static final String COLUMN_RECIPIES_INGREDIENT_AMOUNT = "ingredient_amount";
    public static final String COLUMN_RECIPIES_INGREDIENT_MEASUMENTTYPE = "ingredient_measurment_type";
    public static final String COLUMN_RECIPIE_INGREDIENT_RECIPE_ID = COLUMN_RECIPE_ID;
    public static final String COLUMN_RECIPIE_INGREDIENT_INGREDIENT_ID = COLUMN_INGREDIENT_ID;


}
