package com.group.user.window.frame;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.group.data.Employee;
import com.group.user.component.Template;
import com.group.user.window.panel.Left;

public class Home extends JFrame implements ActionListener{
    private Left left = new Left();
    private JPanel right = new JPanel();
    private Employee user;

    private JLabel heading = new JLabel();

    private JButton toProfile = new JButton("Account Profile");
    private JButton searchDatabase = new JButton("Search Database");

    public Home(Employee user) {
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

        heading.setText("WELCOME BACK, " + this.user.getName().split(" ")[0].toUpperCase());
        right.add(heading);
        Template.setFontSize(heading, 25);
        Template.setDefault(heading);
        Template.setCenter(heading, right);
        Template.setYCoordinate(heading, 25);

        right.add(toProfile);
        Template.setFontSize(toProfile, 20);
        Template.setDefault(toProfile);
        Template.setBorder(toProfile, 2);
        Template.enlarge(toProfile, 2);
        Template.setCenter(toProfile, right);
        Template.setYCoordinate(toProfile, heading.getHeight() + heading.getLocation().y + 75);
        toProfile.setFocusable(false);

        right.add(searchDatabase);
        Template.setFontSize(searchDatabase, 20);
        Template.setDefault(searchDatabase);
        Template.setBorder(searchDatabase, 2);
        searchDatabase.setSize(toProfile.getSize());
        Template.setCenter(searchDatabase, right);
        Template.setYCoordinate(searchDatabase, toProfile.getHeight() + toProfile.getLocation().y + 50);
        searchDatabase.setFocusable(false);

        toProfile.addActionListener(this);
        searchDatabase.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == toProfile) {
            this.dispose();
            new Profile(user);
        }

        if(e.getSource() == searchDatabase) {
            this.dispose();
            new Search(user);
        }
    }
}
