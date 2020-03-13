package com.view;

import com.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindResultForm extends JFrame {
    JLabel lbl;
    JButton ok;
    JTextField idText;
    Controller controller = Controller.getInstance();
    static String str;


    public FindResultForm (){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        lbl = new JLabel("      Enter id        ");
        ok = new JButton("OK");
        ok.addActionListener(new OkListner());
        idText = new JTextField(5);
        Box box = Box.createHorizontalBox();
        box.add(idText);
        box.add(lbl);
        box.add(ok);
        setContentPane(box);

        pack();
        setVisible(true);
    }

    class OkListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            str = controller.findByID(idText.getText());
            lbl.setText(str);
        }
    }

}
