package com.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import com.beans.Book;
import com.controller.Connection;
import com.view.MainForm;

import    javax.swing.*;

    public class Controller {

        private Controller(){}

        Connection connection = Connection.getInstance();
        private Socket socket ;
        private ObjectOutputStream os;
        private ObjectInputStream is;


        private static final Controller instance = new Controller();

        public static Controller getInstance(){return instance;}

    public String SignIn(String login,String password) {
        String responce;
        try {
            socket = connection.getSocket();
            is = connection.getInpStream();
            os = connection.getOutStream();
            os.writeObject("SIGN_IN " + login + "!!" + password);
            responce = (String) is.readObject();
            return responce;
        } catch (IOException | ClassNotFoundException | NullPointerException e ) {
            responce = "Error, problems with server connection";
            e.printStackTrace();
        }
        return responce;
    }

    public Vector<Vector<String>> showBooks() throws java.lang.ArrayIndexOutOfBoundsException  {
        Vector<Vector<String>> books = new Vector<Vector<String>>();
        Vector<String> book;
        String recieved;
        try{
            socket = connection.getSocket();
            is = connection.getInpStream();
            os = connection.getOutStream();
            os.writeObject("SHOW_BOOKS");
            recieved = (String) is.readObject();
            String[] parce1 = recieved.split("\n");
            for(String str : parce1){
                book = new Vector<>();
                book.add(str.split("!!")[0]);
                book.add(str.split("!!")[1]);
                book.add(str.split("!!")[2]);
                book.add(str.split("!!")[3]);
                books.add(book);
            }

            return books;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    public Vector<Vector<String>> showUsers(){
        Vector<Vector<String>> users = new Vector<Vector<String>>();
        Vector<String> user;
        String recieved;
        try{
            socket = connection.getSocket();
            is = connection.getInpStream();
            os = connection.getOutStream();
            os.writeObject("SHOW_USERS");
            recieved = (String) is.readObject();
            String[] parce1 = recieved.split("\n");
            for(String str : parce1){
                user = new Vector<>();
                user.add(str.split("!!")[0]);
                user.add(str.split("!!")[1]);
                user.add(str.split("!!")[2]);
                user.add(str.split("!!")[3]);
                user.add(str.split("!!")[4]);
                user.add(str.split("!!")[5]);
                users.add(user);
            }

            return users;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> showStyles(){
        ArrayList<String> styles = new ArrayList<>();
        String recieved;
        try{
            socket = connection.getSocket();
            is = connection.getInpStream();
            os = connection.getOutStream();
            os.writeObject("SHOW_STYLES");
            recieved = (String) is.readObject();
            String[] parce1 = recieved.split("\n");
            for(String str : parce1){
                styles.add(str.split("!!")[0] + ":" + str.split("!!")[1]);
            }
            return styles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addBook(String name, String author, int idStyle){
        String received;
        socket = connection.getSocket();
        is = connection.getInpStream();
        os = connection.getOutStream();
        try {
            os.writeObject("ADD_BOOK " + name + "!!" + author + "!!" + idStyle);
            received = (String) is.readObject();
            return received;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String registration (String login, String passw, String name,String passport, String phone){
        String recieved;
        socket = connection.getSocket();
        is = connection.getInpStream();
        os = connection.getOutStream();
        try{
            os.writeObject("REGISTRATION " + login + "!!" + passw + "!!" + name + "!!"
                    + passport + "!!" + phone);
            recieved = (String) is.readObject();
            return recieved;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String deleteBookById(String id){
        String received;
        socket = connection.getSocket();
        is = connection.getInpStream();
        os = connection.getOutStream();
        try{
            os.writeObject("DELETE_BOOK_BY_ID " + id);
            received = (String) is.readObject();
            return received;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Exception";
    }
    public String editBook(String id, String name, String author, String style){
        String received;
        socket = connection.getSocket();
        is = connection.getInpStream();
        os = connection.getOutStream();
        try{
            os.writeObject("EDIT_BOOK " + id+ "!!" + name +"!!"+ author + "!!" + style);
            received = (String) is.readObject();
            return received;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String reserveBook(String idBook){
        String received;
        socket = connection.getSocket();
        is = connection.getInpStream();
        os = connection.getOutStream();
        try{
            os.writeObject("RESERVE_BOOK " + idBook);
            received = (String) is.readObject();
            return received;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Unexpected Eror";
        }
    }

    public Vector<Vector<String>> showMyBooks() throws Exception{
        Vector<Vector<String>> books = new Vector<Vector<String>>();
        Vector<String> book;
        String recieved;
        try{
            socket = connection.getSocket();
            is = connection.getInpStream();
            os = connection.getOutStream();
            os.writeObject("SHOW_MY_BOOKS");
            recieved = (String) is.readObject();
            String[] parce1 = recieved.split("\n");
            for(String str : parce1){
                book = new Vector<>();
                book.add(str.split("!!")[0]);
                book.add(str.split("!!")[1]);
                book.add(str.split("!!")[2]);
                book.add(str.split("!!")[3]);
                book.add(str.split("!!")[4]);
                book.add(str.split("!!")[5]);
                books.add(book);
            }
            return books;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            throw new Exception(e);
        }
        return null;
    }
    public String signOut(){
        String received;
        socket = connection.getSocket();
        is = connection.getInpStream();
        os = connection.getOutStream();
        try{
            os.writeObject("SIGN_OUT");
            received = (String)is.readObject();
            MainForm.isSigned = false;
            return received;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Error";
        }
    }
    public String findByID(String idStr){
        try{
        int id;
        id = Integer.parseInt(idStr);
        String recieved;
        socket = connection.getSocket();
        is = connection.getInpStream();
        os = connection.getOutStream();
            os.writeObject("FIND_BY_ID " + id);
            recieved = (String) is.readObject();
            return recieved;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }

    }



}
