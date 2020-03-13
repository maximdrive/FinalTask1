package com.beans;

public class Book {
    private int idBook;
    private String name;
    private String author;
    private int idStyle;

    public Book(){}

    public Book(String name,String author,int idStyle){
        this.name = name;
        this.author = author;
        this.idStyle = idStyle;
    }

    public String getName() {
        return name;
    }

    public int getStyle() {
        return idStyle;
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

    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public void setName(String name) {
        this.name = name;
    }
}
