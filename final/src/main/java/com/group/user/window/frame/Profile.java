package com.group.user.window.frame;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.group.data.Constant;
import com.group.data.Employee;
import com.group.editor.Writer;
import com.group.user.component.Template;
import com.group.user.window.panel.Left;

public class Profile extends JFrame implements ActionListener{
    private Left left = new Left();
    private JPanel right = new JPanel();
    private Employee user;

    private Constant c = new Constant();
    private Writer wt = new Writer();
    private JLabel[] infoLabels = new JLabel[c.HEADERS.length];
    private JTextField[] infoFields = new JTextField[c.HEADERS.length];
    private JLabel ID = new JLabel();

    private JLabel heading = new JLabel("ACCOUNT PROFILE");

    private JLabel alertLabel = new JLabel();

    private JButton updateButton = new JButton("UPDATE");
    private JButton returnButton = new JButton("RETURN ");
    
    public Profile(Employee user) {
        this.user = user;
        this.setSize(750, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Some Random Clinic EMS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);

        this.add(left);
        left.setLocation(0, 0);

        this.add(right);
        right.setBounds(250, 0, 500, 500);
        right.setBackground(Template.BLUE);
        right.setLayout(null);

        right.add(heading);
        Template.setFontSize(heading, 25);
        Template.setDefault(heading);
        Template.setCenter(heading, right);
        Template.setYCoordinate(heading, 25);

        String[] info = this.user.getAll();

        for(int i = 0; i < c.HEADERS.length; i++) {
            infoLabels[i] = new JLabel(c.HEADERS[i]);
            right.add(infoLabels[i]);
            Template.setFontSize(infoLabels[i], 15);
            Template.setDefault(infoLabels[i]);
            infoLabels[i].setLocation(40, heading.getLocation().y + heading.getHeight() + 30 + (20 + infoLabels[i].getHeight())*i);
            
            infoFields[i] = new JTextField(info[i], 20);
            right.add(infoFields[i]);
            Template.setFontSize(infoFields[i], 15);
            Template.setDefault(infoFields[i]);
            Template.setBorder(infoFields[i], 2);
            Template.setCenter(infoFields[i], right);
            infoFields[i].setLocation(500 - infoFields[i].getWidth() - 40 ,infoLabels[i].getLocation().y);
            infoFields[i].setCaretColor(Color.WHITE);

            if (i == 0) {
                infoFields[i].setVisible(false);
            }
        }

        ID.setText(info[0]);
        right.add(ID);
        Template.setFontSize(ID, 15);
        Template.setDefault(ID);
        ID.setLocation(200, heading.getLocation().y + heading.getHeight() + 30);


        right.add(alertLabel);
        Template.setFontSize(alertLabel, 15);

        right.add(returnButton);
        Template.setFontSize(returnButton, 15);
        Template.setDefault(returnButton);
        Template.setBorder(returnButton, 2);
        Template.setCenter(returnButton, right);
        Template.setYCoordinate(returnButton, 500 - returnButton.getHeight() - 50);
        Template.setXCoordinate(returnButton, 125);
        returnButton.setFocusable(false);

        returnButton.addActionListener(this);

        right.add(updateButton);
        Template.setFontSize(updateButton, 15);
        Template.setDefault(updateButton);
        Template.setBorder(updateButton, 2);
        Template.setCenter(updateButton, right);
        Template.setYCoordinate(updateButton, returnButton.getLocation().y);
        Template.setXCoordinate(updateButton, 500 - 125 - updateButton.getWidth());
        updateButton.setFocusable(false);

        updateButton.addActionListener(this);



    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnButton) {
            this.dispose();
            new Home(user);

        }

        if (e.getSource() == updateButton) {
            String[] updatedInfo = new String[c.HEADERS.length];
            for (int j = 0; j < c.HEADERS.length; j++) {
                updatedInfo[j] = infoFields[j].getText();
            }
            if (!wt.replace(new Employee(updatedInfo))) {
                right.add(alertLabel);
                alertLabel.setText("This Email is already registed by a different User");
                Template.setDefault(alertLabel);
                Template.setCenter(alertLabel, right);
                Template.setYCoordinate(alertLabel, updateButton.getLocation().y - alertLabel.getHeight() - 10);
            }
        }

    }
}

