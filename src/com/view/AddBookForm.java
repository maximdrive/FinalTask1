package com.view;

import com.Main;
import com.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class AddBookForm extends JFrame {
    JTextField bName;
    JTextField author;
    JComboBox style;
    JButton ok;
    JButton cancel;
    JLabel info;
    ArrayList<String> styles;
    Controller controller = Controller.getInstance();

    public AddBookForm(){
        super("Adding book");
        styles = controller.showStyles();
        Vector<String> vecStyles = new Vector<>(styles);
        initComp(vecStyles);
        setSize(400,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        draw();
    }
    private void initComp(Vector<String> styles){
        bName = new JTextField(20);
        author = new JTextField(20);
        style = new JComboBox(styles);
        ok = new JButton("OK");
        ok.addActionListener(new OkButtonListner());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelButtonListner());
        info = new JLabel("Status Bar");
    }

    private void draw(){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10,0,10,20);


        JLabel namelbl = new JLabel("Book Name");
        layout.setConstraints(namelbl,c);
        add(namelbl);
        c.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(bName,c);
        add(bName);

        JLabel authlbl = new JLabel("Author Name:");
        c.gridwidth = 1;
        layout.setConstraints(authlbl,c);
        add(authlbl);
        c.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(author,c);
        add(author);

        JLabel stlbl = new JLabel("Book's Style:");
        c.gridwidth = 1;
        layout.setConstraints(stlbl,c);
        add(stlbl);
        c.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(style,c);
        add(style);

        c.gridwidth = 1;
        c.insets = new Insets(10,50,10,20);
        layout.setConstraints(ok,c);
        add(ok);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10,0,10,20);
        layout.setConstraints(cancel,c);
        add(cancel);

        layout.setConstraints(info,c);
        add(info);
    }


    class OkButtonListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int id;
            String str = Objects.requireNonNull(style.getSelectedItem()).toString();
            id = Integer.parseInt(str.split(":")[0]);
            String information = controller.addBook(bName.getText(),author.getText(),
                    id);
            info.setText(information);
            if(information.equals("Success")) {
                AddBookForm.this.setVisible(false);
                AddBookForm.this.dispose();
            }
        }
    }
    class CancelButtonListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AddBookForm.this.setVisible(false);
            AddBookForm.this.dispose();
        }
    }

}
