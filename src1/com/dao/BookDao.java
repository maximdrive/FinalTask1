package com.dao;

import com.beans.Book;
import com.beans.User;
import com.dao.exeption.DaoExeption;

import java.util.ArrayList;

public interface BookDao {
    void addBook(Book book);
    void deleteBook(int idBook) throws DaoExeption;
    void deleteBook(String Name) throws DaoExeption;
    ArrayList<Book> showBook();
    void reserveBook(Book book, User user);
    void reserveBook(int idBook, int idUser) throws DaoExeption;
    String showStyles() throws DaoExeption;
    String styleById(int id) throws DaoExeption;
    void editBook(Book book) throws DaoExeption;
    int idByStyleName(String name) throws DaoExeption;
    String showMyBooks(int idUser) throws DaoExeption;
}
