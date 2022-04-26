package ru.sirlacmus.note;

import java.util.Random;

public class Note {

    private final int id;
    private final String date;
    private final String author;
    private String content;
    private Categories category;

    public Note(String date, String content, Categories categories, String author) {
        this.id = new Random(10000).nextInt();
        this.date = date;
        this.content = content;
        this.category = categories;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String notes) {
        this.content = notes;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories categories) {
        this.category = categories;
    }

    public String getAuthor() {
        return author;
    }

    public enum Categories {
        EVERYDAY,
        WORK,
        PERSONAL,
        TRAVEL,
        NO_CATEGORY
    }
}