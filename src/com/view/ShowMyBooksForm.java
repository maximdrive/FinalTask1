package com.view;

import com.controller.Connection;
import com.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ShowMyBooksForm extends JFrame {
    JTable booksTable;
    JButton ok;
    JButton refresh;
    Controller controller = Controller.getInstance();
    JLabel info;
    Vector<Vector<String>> books;
    Vector<String> columsHeader;
    JButton save;
    Connection connection = Connection.getInstance();
    private Socket socket ;
    private ObjectOutputStream os;
    private ObjectInputStream is;


    public ShowMyBooksForm(){
        super("My booked books");
        initComp();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        draw();
    }

    private void initComp() {
        ok = new JButton("Ok");
        ok.addActionListener(new OkBtnListner());
        refresh = new JButton("Refresh");
        refresh.addActionListener(new RefreshListner());
        save = new JButton("Save to file");
        save.addActionListener(new SaveListner());
        info = new JLabel("Status Bar");
        columsHeader = new Vector<>();
        columsHeader.add("idBook");
        columsHeader.add("BookName");
        columsHeader.add("Author");
        columsHeader.add("Style");
        columsHeader.add("Reservation date");
        columsHeader.add("Returning date");
        try {
            books = controller.showMyBooks();
        }
        catch (Exception e)
        {
            info.setText("Nothing booked");
        }
        booksTable = new JTable(books,columsHeader);
    }

    private void draw(){
        Box box1 = Box.createVerticalBox();
        box1.add(new JScrollPane(booksTable));

        Box box2 = Box.createVerticalBox();
        box2.add(ok);
        box2.add(Box.createVerticalStrut(10));
        box2.add(refresh);
        Box box3 = Box.createHorizontalBox();
        box3.add(info);
        box3.add(save);

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

    class RefreshListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ShowMyBooksForm.this.getContentPane().removeAll();
            initComp();
            draw();
        }
    }

    class OkBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ShowMyBooksForm.this.setVisible(false);
            ShowMyBooksForm.this.dispose();
        }
    }
    class SaveListner implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String str ="";
            socket = connection.getSocket();
            is = connection.getInpStream();
            os = connection.getOutStream();
            try{
                os.writeObject("SHOW_MY_BOOKS");
                str = (String) is.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try(FileWriter writer = new FileWriter("mybooks.txt", false))
            {
                writer.write(str);
                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
