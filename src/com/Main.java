package com;

import com.beans.Book;
import com.beans.User;
import com.dao.BookDao;
import com.dao.UserDao;
import com.dao.factory.DaoFactory;
import com.dao.impl.SqlUserDao;
import com.view.StartProcess;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSocket serv = null;
        try {
            serv = new ServerSocket(2525);
            Socket accSocket;

            while (true) {
                accSocket = serv.accept();
                Thread t = new Thread(new StartProcess(accSocket));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



