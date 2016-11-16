package com.seg2105.fall2016.javacooktime.main.model;

/**
 * Created by markmroz on 2016-11-16.
 */

public class IngredientModel {
    private int id;
    private String name;
    private int amount;
    private String measumentStandard;


    public IngredientModel(String name, int amount, String measumentStandard) {
        this.name = name;
        this.amount = amount;
        this.measumentStandard = measumentStandard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasumentStandard() {
        return measumentStandard;
    }

    public void setMeasumentStandard(String measumentStandard) {
        this.measumentStandard = measumentStandard;
    }
}
