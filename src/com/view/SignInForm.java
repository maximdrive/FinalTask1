package com.view;

import com.Main;
import com.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SignInForm extends JFrame {
    JTextField loginField;
    JPasswordField passwordField;
    JLabel inform;
    JButton ok;
    JButton cancel;
    JButton registration;
    Controller controller = Controller.getInstance();
    public SignInForm(){
        super("Sign In");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField(15);
        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);

        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);

        Box box3 = Box.createHorizontalBox();
        ok = new JButton("Ok");
        ok.addActionListener(new OkButtonListner());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelButtonListener());
        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
        box3.add(Box.createHorizontalStrut(12));
        registration = new JButton("Registration");
        registration.addActionListener(new RegButtonListner());
        box3.add(registration);
        Box box4 = Box.createHorizontalBox();
        inform = new JLabel("Status Bar");
        box4.add(inform);

        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box4);
        setContentPane(mainBox);


        pack();
        setResizable(false);
        setVisible(true);

    }

    class OkButtonListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            inform.setText(controller.SignIn(loginField.getText(),
                    String.valueOf(passwordField.getPassword()) ) );
            if(inform.getText().equals("Welcome")){
                SignInForm.this.setVisible(false);
                MainForm.setLoginName(loginField.getText());
                MainForm.info.setText("Hello, "+ loginField.getText());
                MainForm.isSigned = true;
            }
        }
    }

    class CancelButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            SignInForm.this.setVisible(false);
            SignInForm.this.dispose();
        }
    }

    class RegButtonListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            RegistrationForm registrationForm = new RegistrationForm();
        }
    }
}
