package com.view;

import com.beans.Book;
import com.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

public class BookObserverForm extends JFrame {
    JTable booksTable;
    JButton ok;
    JButton refresh;
    JButton reserve;
    JButton myBooks;
    Controller controller = Controller.getInstance();
    JLabel info;
    Vector<Vector<String>> books;
    Vector<String> columsHeader;
    JButton findById;

    public BookObserverForm(){
        super("Book's observer");
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
        reserve = new JButton("Reserve");
        reserve.addActionListener(new ReserveListner());
        myBooks = new JButton("My Books");
        myBooks.addActionListener(new MyBookListner());
        info = new JLabel("Status Bar");
        columsHeader = new Vector<>();
        columsHeader.add("idBook");
        columsHeader.add("BookName");
        columsHeader.add("Author");
        columsHeader.add("Style");
        books = controller.showBooks();
        if(books == null) info.setText("Sign In, please");
        booksTable = new JTable(books,columsHeader);
        findById = new JButton("Find by ID");
        findById.addActionListener(new FindListner());
    }

    private void draw(){
        Box box1 = Box.createVerticalBox();
        box1.add(new JScrollPane(booksTable));

        Box box2 = Box.createVerticalBox();
        box2.add(ok);
        box2.add(Box.createVerticalStrut(10));
        box2.add(refresh);
        box2.add(Box.createVerticalStrut(10));
        box2.add(reserve);
        box2.add(Box.createVerticalStrut(10));
        box2.add(myBooks);
        box2.add(Box.createVerticalStrut(10));
        box2.add(findById);
        Box box3 = Box.createHorizontalBox();
        box3.add(info);

        ok.setPreferredSize(refresh.getPreferredSize());

        Box mainBox1 = Box.createVerticalBox();
        mainBox1.setBorder(new EmptyBorder(12,12,12,12));
        mainBox1.add(box1);
        mainBox1.add(Box.createVerticalStrut(10));
        mainBox1.add(box3);
        Box mainBox = Box.createHorizontalBox();
        mainBox.add(mainBox1);
        mainBox.add(box2);

        setContentPane(mainBox);
        pack();
    }

    class RefreshListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            BookObserverForm.this.getContentPane().removeAll();
            initComp();
            draw();
        }
    }

    class OkBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            BookObserverForm.this.setVisible(false);
            BookObserverForm.this.dispose();
        }
    }

    class ReserveListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String id = (String) booksTable.getValueAt(booksTable.getSelectedRow(),0);
            info.setText(controller.reserveBook(id));
        }
    }

    class MyBookListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ShowMyBooksForm showMyBooksForm = new ShowMyBooksForm();
        }
    }

    class FindListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FindResultForm result = new FindResultForm();
        }
    }
}
