package org.example;

public class Quote {
    private String author;
    private String text;

    public Quote(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void show() {
        System.out.println(" ");
        System.out.println(" -- ");
        System.out.println("Quote: " + this.getText());
        System.out.println("Author: " + this.getAuthor());
        System.out.println(" -- ");
        System.out.println(" ");
    }
}
