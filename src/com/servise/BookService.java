package com.servise;

import com.beans.Book;
import com.dao.exeption.DaoExeption;
import com.servise.exception.ServiceException;

import java.util.ArrayList;

public interface BookService {
    void addBook(Book book) throws ServiceException;
    void deleteBook(int idBook) throws ServiceException;
    void deleteBook(String name) throws ServiceException;
    ArrayList<Book> showBook() throws ServiceException;
    void reserveBook(int idBook, int idUser) throws ServiceException;
    String showStyles() throws ServiceException;
    String styleById(int id) throws ServiceException;
    void editBook(Book book) throws ServiceException;
    int idByStyleName(String name) throws ServiceException;
    String showMyBooks(int idUser) throws ServiceException;

}
