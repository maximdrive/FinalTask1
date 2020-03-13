package com.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public  class Connection {

    static private Socket socket;
    static private ObjectOutputStream os;
    static private ObjectInputStream is;

    static {
        try {
            socket = new Socket("127.0.0.1", 2525);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static Connection connection = new Connection();

    private Connection() {}

    public static Connection getInstance() {
        return connection;
    }

    public Socket getSocket(){
        return socket;
    }

    public  ObjectInputStream getInpStream(){
        return is;
    }

    public  ObjectOutputStream getOutStream(){
        return os;
    }

}
