package com.servise.impl;

import com.beans.Book;
import com.dao.BookDao;
import com.dao.exeption.DaoExeption;
import com.dao.factory.DaoFactory;
import com.dao.impl.SqlBookDao;
import com.servise.BookService;
import com.servise.exception.ServiceException;

import java.util.ArrayList;

public class BookServiceImpl implements BookService {

    @Override
    public void addBook(Book book) throws ServiceException {
        if(book.getName().isEmpty() || book.getAuthor().isEmpty() || book.getIdBook()<0)
            throw new ServiceException("Enter all information");
        DaoFactory daoObjFactory = DaoFactory.getInstance();
        BookDao bookDao = daoObjFactory.getBookDao();

        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(int idBook) throws ServiceException {
        if(idBook<=0) throw new ServiceException("Id is not correct");
        try{
            DaoFactory daoObjFactory = DaoFactory.getInstance();
            BookDao bookDao = daoObjFactory.getBookDao();

            bookDao.deleteBook(idBook);
        }
        catch (DaoExeption daoExeption){
            throw new ServiceException(daoExeption);
        }
    }

    @Override
    public void deleteBook(String name) throws ServiceException {
        if(name.isEmpty()) throw new ServiceException("Book Name is empty");
        try{
            DaoFactory daoObjFactory = DaoFactory.getInstance();
            BookDao bookDao = daoObjFactory.getBookDao();

            bookDao.deleteBook(name);
        }
        catch (DaoExeption daoExeption){
            throw new ServiceException(daoExeption);
        }
    }

    @Override
    public ArrayList<Book> showBook() throws ServiceException{
        ArrayList<Book> books = new ArrayList<Book>();

        DaoFactory daoObjFactory = DaoFactory.getInstance();
        BookDao bookDao = daoObjFactory.getBookDao();
        books.addAll(bookDao.showBook());

        if(books.isEmpty())
            throw new ServiceException("Library is empty");
        return books;
    }

    @Override
    public void reserveBook(int idBook, int idUser) throws ServiceException {
        if(idBook<0 || idUser <0) throw new ServiceException("Incorrect ID");
        try {
            DaoFactory daoObjFactory = DaoFactory.getInstance();
            BookDao bookDao = daoObjFactory.getBookDao();
            bookDao.reserveBook(idBook,idUser);
        }
        catch (DaoExeption daoExeption){
            throw new ServiceException(daoExeption);
        }

    }

    @Override
    public String showStyles() throws ServiceException {
        String responce;
        DaoFactory daoFactoryObj = DaoFactory.getInstance();
        BookDao bookDao = daoFactoryObj.getBookDao();
        try {
            responce = bookDao.showStyles();
            return responce;
        } catch (DaoExeption daoExeption) {
            throw new ServiceException(daoExeption);
        }
    }

    @Override
    public String styleById(int id) throws ServiceException {
        String responce;
        DaoFactory daoFactoryObj = DaoFactory.getInstance();
        BookDao bookDao = daoFactoryObj.getBookDao();
        try {
            responce = bookDao.styleById(id);
            return responce;
        } catch (DaoExeption daoExeption) {
            throw new ServiceException(daoExeption);
        }
    }

    @Override
    public void editBook(Book book) throws ServiceException {
        DaoFactory daoFactoryObj = DaoFactory.getInstance();
        BookDao bookDao = daoFactoryObj.getBookDao();
        try{
            bookDao.editBook(book);
        } catch (DaoExeption daoExeption) {
            daoExeption.printStackTrace();
            throw new ServiceException(daoExeption);
        }
    }

    @Override
    public int idByStyleName(String name) throws ServiceException {
        int responce;
        DaoFactory daoFactoryObj = DaoFactory.getInstance();
        BookDao bookDao = daoFactoryObj.getBookDao();
        try {
            responce = bookDao.idByStyleName(name);
            return responce;
        } catch (DaoExeption daoExeption) {
            throw new ServiceException(daoExeption);
        }
    }

    @Override
    public String showMyBooks(int idUser) throws ServiceException {
        String responce;
        DaoFactory daoFactoryObj = DaoFactory.getInstance();
        BookDao bookDao = daoFactoryObj.getBookDao();
        try{
            responce = bookDao.showMyBooks(idUser);
            return responce;
        } catch (DaoExeption daoExeption) {
            daoExeption.printStackTrace();
            throw new ServiceException(daoExeption);
        }
    }
}
