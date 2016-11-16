package com.seg2105.fall2016.javacooktime.main.model;

/**
 * Created by markmroz on 2016-11-16.
 */

public class InstructionModel {

    private int id;
    private String content;
    private String position;

    public InstructionModel(String position, String content) {
        this.position = position;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
