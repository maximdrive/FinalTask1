package com.view;

import com.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.CheckedOutputStream;

import static java.lang.Thread.sleep;

public class RegistrationForm extends JFrame {
    JTextField login;
    JTextField password;
    JTextField name;
    JTextField passport;
    JTextField phone;
    JButton registration;
    JButton cancel;
    JLabel info;
    Controller controller = Controller.getInstance();

    public RegistrationForm(){
        super("Registration");
        initComp();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        draw();
    }

    private void initComp(){
        login = new JTextField(20);
        password = new JTextField(20);
        name = new JTextField(20);
        passport = new JTextField(20);
        phone = new JTextField(20);
        registration = new JButton("Registration");
        registration.addActionListener(new RegButtonListner());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelButtonListner());
        info = new JLabel("Status Bar");
    }
    private void draw(){
        Box box1 = Box.createHorizontalBox();
        JLabel loginLbl = new JLabel("Create login");
        box1.add(loginLbl);
        box1.add(Box.createHorizontalStrut(5));
        box1.add(login);

        Box box2 = Box.createHorizontalBox();
        JLabel paswLbl = new JLabel("Create password:");
        box2.add(paswLbl);
        box2.add(Box.createHorizontalStrut(5));
        box2.add(password);

        Box box3 = Box.createHorizontalBox();
        JLabel nameLbl = new JLabel("Your Name");
        box3.add(nameLbl);
        box3.add(Box.createHorizontalStrut(5));
        box3.add(name);

        Box box4 = Box.createHorizontalBox();
        JLabel passpLbl = new JLabel("Passport Number");
        box4.add(passpLbl);
        box4.add(Box.createHorizontalStrut(5));
        box4.add(passport);

        Box box5 = Box.createHorizontalBox();
        JLabel phoneLbl = new JLabel("Phone Number");
        box5.add(phoneLbl);
        box5.add(Box.createHorizontalStrut(5));
        box5.add(phone);

        Box box6 = Box.createHorizontalBox();
        box6.add(registration);
        box6.add(Box.createHorizontalStrut(15));
        box6.add(cancel);

        Box box7 = Box.createHorizontalBox();
        box7.add(info);

        loginLbl.setPreferredSize(paswLbl.getPreferredSize());
        nameLbl.setPreferredSize(paswLbl.getPreferredSize());
        passpLbl.setPreferredSize(paswLbl.getPreferredSize());
        phoneLbl.setPreferredSize(paswLbl.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box4);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box5);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box6);
        mainBox.add(Box.createVerticalStrut(8));
        mainBox.add(box7);




        setContentPane(mainBox);
        pack();
    }

    class RegButtonListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            info.setText(controller.registration(login.getText(),password.getText(),
                            name.getText(), passport.getText(), phone.getText()));
            if(info.getText().equals("Success")) RegistrationForm.this.setVisible(false);
        }
    }

    class CancelButtonListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            RegistrationForm.this.setVisible(false);
            RegistrationForm.this.dispose();
        }
    }
}
