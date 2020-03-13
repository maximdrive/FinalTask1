package com.dao.impl;

import com.beans.Book;
import com.beans.User;
import com.dao.BookDao;
import com.dao.exeption.DaoExeption;
import com.dao.factory.DaoFactory;
import com.dao.handler.Handler;

import java.sql.*;
import java.util.ArrayList;

public class SqlBookDao implements BookDao {
    Connection connection;
    Statement st;
    ResultSet rs;

    @Override
    public void addBook(Book book) {
        connection = Handler.getDbConnection();
        int id;
        try{
            id = Handler.getIdFromDB("book");
            book.setIdBook(id);

            String querry = "INSERT INTO book  VALUES (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1,id);
            st.setString(2,book.getName());
            st.setString(3,book.getAuthor());
            st.setInt(4,book.getStyle());
            st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editBook(Book book) throws DaoExeption {
        connection = Handler.getDbConnection();
        int id = book.getIdBook();
        try{
            String querry = "update book set BName = '" + book.getName() + "', Author = '"
                    + book.getAuthor() + "', idStyle = " + book.getStyle() + " where id = " + book.getIdBook();
            st = connection.createStatement();
            st.executeUpdate(querry);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoExeption("Wrong info to edit");
        }
    }



    @Override
    public void deleteBook(int idBook) throws DaoExeption{
        connection = Handler.getDbConnection();
        try{
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * from book inner join booking on book.id = booking.idBook where book.id = " +idBook);
            if(rs.first()) throw new DaoExeption("Can't delete book, 'cause it's booked");
            else
            st.executeUpdate("DELETE FROM book WHERE id = " + idBook);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteBook(String Name) throws NullPointerException,DaoExeption{
        int idBook = Handler.searchIDBy("book","BName",Name,1);
        if(idBook==0) throw new NullPointerException("Book doesn't exist");
            deleteBook(idBook);
    }

    @Override
    public ArrayList<Book> showBook() {
        connection = Handler.getDbConnection();
        ArrayList<Book> books = new ArrayList<Book>();
        Book book;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM book");
            while(rs.next()){
                book = new Book();
                book.setIdBook(rs.getInt(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setIdStyle(rs.getInt(4));
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void reserveBook(Book book, User user) {
        connection = Handler.getDbConnection();
        Statement st;
        String querry = "INSERT INTO booking Values(" + user.getIdUser() + "," + book.getIdBook()
                + ",NOW(),date_add(now(),interval 30 day))";
        try{
            st = connection.createStatement();
            st.executeUpdate(querry);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reserveBook(int idBook, int idUser) throws DaoExeption{
        connection = Handler.getDbConnection();
        String querry = "INSERT INTO booking VALUES (" + idUser + "," + idBook + ",NOW(),date_add(now(),interval 30 day))";
        try{
            st = connection.createStatement();
            rs = st.executeQuery("SELECT idBook FROM booking WHERE idBook = " + idBook);
            if(rs.first()) throw new DaoExeption("Book is already booked");
            else st.executeUpdate(querry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String showStyles() throws DaoExeption {
        connection = Handler.getDbConnection();
        String responce = "";
        try{
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM style");
            if(!rs.first()) throw new DaoExeption("Style Table is empty");
            rs.beforeFirst();
            while(rs.next()){
                responce += rs.getInt(1) + "!!" + rs.getString(2) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public String styleById(int id) throws DaoExeption {
        if(id<0) throw new DaoExeption("Wrong ID");
        connection = Handler.getDbConnection();
        String responce;
        try{
            st = connection.createStatement();
            rs = st.executeQuery("select StyleName from style where id = " + id);
            if(!rs.first()) throw new DaoExeption("Wrong ID");
            responce = rs.getString(1);
            return responce;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int idByStyleName(String name) throws DaoExeption {
        if(name.isEmpty()) throw new DaoExeption("Wrong Name of Style");
        connection = Handler.getDbConnection();
        int responce;
        try{
            st = connection.createStatement();
            rs = st.executeQuery("select id from style where StyleName = '" + name + "'");
            if(!rs.first()) throw new DaoExeption("Wrong Name of Style");
            responce = rs.getInt(1);
            return responce;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String showMyBooks(int idUser) throws DaoExeption {
        String responce = "";
        connection = Handler.getDbConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("select booking.idBook, book.BName, book.Author, style.StyleName, booking.RecvDate, booking.RetDate from booking" +
                    " inner join book on booking.idBook = book.id" +
                    " inner join style on book.idStyle = style.id" +
                    " where booking.idUser = " + idUser);
            if(!rs.first()) throw new DaoExeption("Nothing booked");
            rs.beforeFirst();
            while(rs.next()){
                responce += rs.getInt(1) +"!!" + rs.getString(2) +"!!" + rs.getString(3) + "!!"
                        + rs.getString(4) + "!!" + rs.getDate(5) + "!!" +  rs.getDate(6) + "\n";
            }
            return responce;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

        }

}

