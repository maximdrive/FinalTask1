package com.view;


import com.controller.Controller;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainForm extends JFrame{
    JLabel label;
    JButton signIn;
    JButton bookObserve;
    JButton bookObserveAdmin;
    JButton registration;
    JButton users;
    JButton close;
    static JLabel login;
    public static boolean isSigned = false;
    JButton signOut;
    Controller controller = Controller.getInstance();
    static JLabel info;
    BufferedImage img;
    JLabel imgLabel;

    public MainForm(){
        super("Main menu");
        initComp();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600,450);
        setResizable(false);
        setVisible(true);

        draw();
    }

    private void initComp() {
        label = new JLabel("What do you want to do?");
        signIn = new JButton("Sign In");
        signIn.addActionListener(new SignInListner());
        registration = new JButton("Registraton");
        registration.addActionListener(new RegistrationListner());
        bookObserve = new JButton("Observe books");
        bookObserve.addActionListener(new BookObserverListner());
        bookObserveAdmin = new JButton("Admin's observe");
        bookObserveAdmin.addActionListener(new BookObserverAdmListner());
        users = new JButton("Show users");
        users.addActionListener(new UserListner());
        close = new JButton("Close");
        close.addActionListener(new CloserListner());
        login = new JLabel("Name");
        signOut = new JButton("Sign Out");
        signOut.addActionListener(new SignOutListner());
        info = new JLabel("Status Bar");
        try {
            img = ImageIO.read(new File("D:\\pic.jpg"));
        }catch (IOException e){e.printStackTrace();}
        imgLabel = new JLabel(new ImageIcon(img));
    }

    private void draw(){
        /*signIn.setPreferredSize(new Dimension(40, 40));
        registration.setPreferredSize(new Dimension(40, 40));
        bookObserve.setPreferredSize(new Dimension(40, 40));
        users.setPreferredSize(new Dimension(40, 40));
        close.setPreferredSize(new Dimension(40, 40));*/

        getContentPane().setLayout(null);

        label.setBounds(130,120,200,30);
        signIn.setBounds(100,150,200,30);
        registration.setBounds(100,180,200,30);
        bookObserve.setBounds(100,210,200,30);
        bookObserveAdmin.setBounds(100,240,200,30);
        users.setBounds(100,270,200,30);
        close.setBounds(100,300,200,30);
        login.setBounds(475,50,100,20);
        signOut.setBounds(450,70,100,20);
        info.setBounds(20,380,200,20);
        imgLabel.setBounds(0,20,500,110);
        getContentPane().add(imgLabel);
        getContentPane().add(label);
        getContentPane().add(signIn);
        getContentPane().add(registration);
        getContentPane().add(bookObserve);
        getContentPane().add(bookObserveAdmin);
        getContentPane().add(users);
        getContentPane().add(close);
        getContentPane().add(login);
        getContentPane().add(signOut);
        getContentPane().add(info);

        /*Box box1 = Box.createHorizontalBox();
        box1.add(label);

        Box box2 = Box.createVerticalBox();
        box2.add(signIn);
        box2.add(Box.createVerticalStrut(15));
        box2.add(registration);
        box2.add(Box.createVerticalStrut(15));
        box2.add(bookObserve);
        box2.add(Box.createVerticalStrut(15));
        box2.add(bookObserveAdmin);
        box2.add(Box.createVerticalStrut(15));
        box2.add(users);
        box2.add(Box.createVerticalStrut(15));
        box2.add(close);

        Box box3 = Box.createVerticalBox();
        box3.add(login);
        box3.add(Box.createVerticalStrut(10));
        box3.add(signOut);
        box3.add(Box.createVerticalStrut(200));

        Box mainBox1 = Box.createVerticalBox();
        mainBox1.add(box1);
        mainBox1.add(Box.createVerticalStrut(20));
        mainBox1.add(box2);

        Box mainBox = Box.createHorizontalBox();
        mainBox.add(Box.createHorizontalStrut(50));
        mainBox.add(mainBox1);
        mainBox.add(Box.createHorizontalStrut(50));
        mainBox.add(box3);

        setContentPane(mainBox);
        pack();*/

    }

    public static void setLoginName(String name) {
        login.setText(name);
    }

    public static String getLoginName() {
        return login.getText();
    }

    class SignInListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            SignInForm signInForm = new SignInForm();
        }
    }
    class RegistrationListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            RegistrationForm registrationForm = new RegistrationForm();
        }
    }

    class BookObserverListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(isSigned) {
                BookObserverForm bookObserverForm = new BookObserverForm();
            }
            else info.setText("Sign in first");
        }
    }
    class BookObserverAdmListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(isSigned) {
                BookObserverAdminForm bookObserverAdminForm = new BookObserverAdminForm();
            } else info.setText("Sign in first");
        }
    }

    class UserListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(isSigned) {
                UsersObserverForm usersObserverForm = new UsersObserverForm();
            } else info.setText("Sign in first");
        }
    }

    class CloserListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
    class SignOutListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmDialog confirmDialog = new ConfirmDialog(MainForm.this);
            if(confirmDialog.getAnswer()) {
                setLoginName("Sign In, please");
                info.setText(controller.signOut());
            }
        }
    }
}
