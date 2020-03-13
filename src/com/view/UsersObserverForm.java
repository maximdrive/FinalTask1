package com.view;

import com.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class UsersObserverForm extends JFrame {
    JTable usersTable;
    JButton ok;
    JButton refresh;
    Controller controller = Controller.getInstance();
    Vector<Vector<String>> users;
    Vector<String> columsHeader;

    public UsersObserverForm(){
        super("User's observer");
        initComp();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        draw();
    }

    private void initComp(){
        ok = new JButton("Ok");
        ok.addActionListener(new OkBtnListner());
        refresh = new JButton("Refresh");
        refresh.addActionListener(new RefreshListner());
        columsHeader = new Vector<>();
        columsHeader.add("idUser");
        columsHeader.add("Login");
        columsHeader.add("Password");
        columsHeader.add("Name");
        columsHeader.add("Passport Number");
        columsHeader.add("Phone");

        users = controller.showUsers();
        usersTable = new JTable(users,columsHeader);
    }

    private void draw(){
        Box box1 = Box.createHorizontalBox();
        box1.add(new JScrollPane(usersTable));

        Box box2 = Box.createHorizontalBox();
        box2.add(ok);
        box2.add(Box.createHorizontalStrut(10));
        box2.add(refresh);

        ok.setPreferredSize(refresh.getPreferredSize());
        // refresh.setPreferredSize(booksTable.getPreferredSize());

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(10));
        mainBox.add(box2);

        setContentPane(mainBox);
        pack();
    }

    class RefreshListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UsersObserverForm.this.getContentPane().removeAll();
            initComp();
            draw();
        }
    }

    class OkBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UsersObserverForm.this.setVisible(false);
            UsersObserverForm.this.dispose();
        }
    }
}
