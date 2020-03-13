package com.beans;

public class Book {
    private int idBook;
    private String name;
    private String author;
    private String style;

    public Book(){}

    public Book(String name,String author,String style){
        this.name = name;
        this.author = author;
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public String  getStyle() {
        return style;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setName(String name) {
        this.name = name;
    }
}
