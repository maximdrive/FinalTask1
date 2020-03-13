package com.view;

import com.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class BookObserverAdminForm extends JFrame {
    JTable booksTable;
    JButton ok;
    JButton refresh;
    JButton delete;
    JButton edit;
    JButton add;
    JLabel info;
    Controller controller = Controller.getInstance();
    Vector<Vector<String>> books;
    Vector<String> columsHeader;

    public BookObserverAdminForm(){
        super("Book's observer for admins");
        initComp();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        draw();
    }


    private void initComp(){
        ok = new JButton("Ok");
        ok.addActionListener(new BookObserverAdminForm.OkBtnListner());
        refresh = new JButton("Refresh");
        refresh.addActionListener(new BookObserverAdminForm.RefreshListner());
        add = new JButton("Add Book");
        add.addActionListener(new AddBtnListner());
        delete = new JButton("Delete");
        delete.addActionListener(new DeleteBtnListner());
        edit = new JButton("Edit");
        edit.addActionListener(new EditBtnListner());
        columsHeader = new Vector<>();
        columsHeader.add("idBook");
        columsHeader.add("BookName");
        columsHeader.add("Author");
        columsHeader.add("Style");
        books = controller.showBooks();
        info = new JLabel("Status Bar");
        booksTable = new JTable(books,columsHeader);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTable.setEditingColumn(1);
    }

    private void draw(){
        Box box1 = Box.createVerticalBox();
        box1.add(new JScrollPane(booksTable));
        box1.add(Box.createVerticalStrut(20));
        box1.add(info);

        Box box2 = Box.createVerticalBox();
        box2.add(ok);
        box2.add(Box.createVerticalStrut(10));
        box2.add(refresh);
        box2.add(Box.createVerticalStrut(10));
        box2.add(add);
        box2.add(Box.createVerticalStrut(10));
        box2.add(delete);
        box2.add(Box.createVerticalStrut(10));
        box2.add(edit);

        ok.setPreferredSize(refresh.getPreferredSize());

        Box mainBox = Box.createHorizontalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createHorizontalStrut(10));
        mainBox.add(box2);

        setContentPane(mainBox);
        pack();
    }

    class RefreshListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            BookObserverAdminForm.this.getContentPane().removeAll();
            initComp();
            draw();
        }
    }

    class OkBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            BookObserverAdminForm.this.setVisible(false);
            BookObserverAdminForm.this.dispose();
        }
    }

    class AddBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AddBookForm addBookForm = new AddBookForm();
        }
    }

    class DeleteBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int row;
            row = booksTable.getSelectedRow();
            ConfirmDialog confirmDialog = new ConfirmDialog(BookObserverAdminForm.this);
            if(confirmDialog.getAnswer()) {
                info.setText(controller.deleteBookById((String) booksTable.getValueAt(row, 0)));
            }
            confirmDialog.dispose();
        }
    }
    class EditBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ConfirmDialog confirmDialog = new ConfirmDialog(BookObserverAdminForm.this);

            if(confirmDialog.getAnswer()) {
                int row = booksTable.getSelectedRow();
                String id = (String) booksTable.getValueAt(row, 0);
                String name = (String) booksTable.getValueAt(row, 1);
                String author = (String) booksTable.getValueAt(row, 2);
                String style = (String) booksTable.getValueAt(row, 3);
                String responce = controller.editBook(id,name,author,style);
                info.setText(responce);
            }
        }
    }
}

//TODO Запретить изменять id в таблице