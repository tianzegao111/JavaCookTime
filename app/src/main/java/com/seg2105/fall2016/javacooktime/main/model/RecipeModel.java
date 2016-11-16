package com.seg2105.fall2016.javacooktime.main.model;

import java.util.ArrayList;

/**
 * Created by markmroz on 2016-11-16.
 */

public class RecipeModel {
    // Properties

    private int id;
    private String name;
    private String category;
    private String dishType;
    private String image;
    private ArrayList<InstructionModel> steps;
    private ArrayList<IngredientModel> ingredients;

    // Intializers

    public RecipeModel() { }

    public RecipeModel(String name, String category, String dishType, String image, ArrayList<InstructionModel> steps, ArrayList<IngredientModel> ingredients) {
        super();
        this.name = name;
        this.category = category;
        this.dishType = dishType;
        this.image = image;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    /**
     * get name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get id
     * @return id
     */

    public int getId() {
        return id;
    }

    /**
     * set is
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * get category
     * @return category
     */

    public String getCategory() {
        return category;
    }

    /**
     * set category
     * @param category
     */

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * get dishType
     * @return dishType
     */

    public String getDishType() {
        return dishType;
    }

    /**
     * set dishType
     * @param dishType
     */

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    /**
     * get image
     * @return image
     */

    public String getImage() {
        return image;
    }

    /**
     * set image
     * @param image
     */

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * get instruction
     * @return ArrayList<Instruction>
     */

    public ArrayList<InstructionModel> getSteps() {
        return steps;
    }

    /**
     * set steps
     * @param steps
     */

    public void setSteps(ArrayList<InstructionModel> steps) {
        this.steps = steps;
    }

    /**
     * get ingredients
     * @return ArrayList<Ingredient>
     */

    public ArrayList<IngredientModel> getIngredients() {
        return ingredients;
    }

    /**
     * set ingredients
     * @param ingredients
     */

    public void setIngredients(ArrayList<IngredientModel> ingredients) {
        this.ingredients = ingredients;
    }

    // To String Method

    @Override
    public String toString() {
        String recipeAsString = "";

        recipeAsString += "Recipe: " + name + "\n";
        recipeAsString += " Category: " + category + "\n";
        recipeAsString += " DishType: " + category + "\n";
        recipeAsString += " Image: " + category + "\n";

        recipeAsString += " Ingredeients:"  + "\n";

        for (IngredientModel ingrdeint : ingredients) {
            recipeAsString += ingredients.toString() + "\n";
        }

        recipeAsString += " Steps:" + "\n";

        for (InstructionModel step : steps) {
            recipeAsString += steps.toString()+ "\n";
        }
        return recipeAsString;
    }
}

