package com.group.window.window.panel;

import javax.swing.JPanel;

import com.group.window.component.Template;

public final class Left extends JPanel{
    private JPanel crossContainer = new JPanel();

    private JPanel top = new JPanel();
    private JPanel left = new JPanel();
    private JPanel center = new JPanel();
    private JPanel right = new JPanel();
    private JPanel bottom = new JPanel();
    
    public Left() {
        this.setSize(250, 500);
        this.setBackground(Template.RED);
        this.setLayout(null);

        this.add(crossContainer);
        crossContainer.setSize(150, 150);
        crossContainer.setBackground(Template.RED);
        crossContainer.setLayout(null);
        Template.setCenter(crossContainer, this);

        crossContainer.add(top);
        top.setBounds(50, 0, 50, 50);

        crossContainer.add(left);
        left.setBounds(0, 50, 50, 50);
        
        crossContainer.add(center);
        center.setBounds(50, 50, 50, 50);
        
        crossContainer.add(right);
        right.setBounds(100, 50, 50, 50);
        
        crossContainer.add(bottom);
        bottom.setBounds(50, 100, 50, 50);

    }
}
