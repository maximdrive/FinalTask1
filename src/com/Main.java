package com;

import com.controller.Connection;
import com.controller.Controller;
import com.view.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        Socket s = new Socket("127.0.0.1",2525);
        System.out.println("Connection is ready!");
        Scanner in = new Scanner(System.in);
        ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(s.getInputStream());
        String recvCommand = "";
        String sendCommand = "";

        System.out.println("Response: " + (String)is.readObject());
        while(!sendCommand.equals("EXIT")){
            System.out.println("Enter your message:");
            sendCommand = in.nextLine();
            os.writeObject(sendCommand);
            recvCommand = (String) is.readObject();
            System.out.println("Response: " + recvCommand);
        }
        os.writeObject("EXIT");
        */

       // MainForm form = new MainForm();




        /*Connection connection = Connection.getInstance();
        ObjectInputStream is = connection.getInpStream();
        ObjectOutputStream os = connection.getOutStream();
        os.writeObject("SIGN_IN max!!123");
        System.out.println((String)is.readObject());*/



       /* SignInForm signInForm = new SignInForm();
        AddBookForm addBookForm = new AddBookForm();
        RegistrationForm registrationForm = new RegistrationForm();
        UsersObserverForm usersObserverForm = new UsersObserverForm();*/
        //BookObserverForm bookObserverForm = new BookObserverForm();
        //BookObserverAdminForm bookObserverAdminForm = new BookObserverAdminForm();
        MainForm mainForm = new MainForm();
        }

    }

