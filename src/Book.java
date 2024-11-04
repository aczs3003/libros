package models;

public class Book {
    private int id;
    private String title;
    private String[] authors;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String[] getAuthors() { return authors; }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + title + ", Autor: " + String.join(", ", authors);
    }
}
