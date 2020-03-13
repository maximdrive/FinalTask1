package com.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmDialog extends JDialog {
    private boolean answer = false;
    JButton yes;
    JButton no;
    JLabel question;
    public ConfirmDialog(JFrame owner){
        super(owner,"Confirmation",true);
        yes = new JButton("Yes");
        yes.addActionListener(new YesBtnListner());
        no = new JButton("No");
        no.addActionListener(new NoBtnListner());
        question = new JLabel("Are you sure?");

        Box box1 = Box.createHorizontalBox();
        box1.add(question);
        Box box2 = Box.createHorizontalBox();
        box2.add(yes);
        box2.add(no);
        Box mainBox = Box.createVerticalBox();
        mainBox.add(box1);
        mainBox.add(box2);

        setContentPane(mainBox);
        pack();
        setVisible(true);
    }

    public boolean getAnswer(){
        return answer;
    }

    class YesBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            answer = true;
            ConfirmDialog.this.setVisible(false);
        }
    }
    class NoBtnListner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            answer = false;
            ConfirmDialog.this.setVisible(false);
        }
    }
}
