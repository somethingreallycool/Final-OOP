package com.group.user.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class Template {
    public static final Color RED = new Color(140, 21, 21);
    public static final Color BLUE = new Color(32, 42, 68);
    public static final int PADDING = 5;

    public static void setFontSize(JComponent component, int fontSize) {
        component.setFont(new Font("Arial", Font.PLAIN, fontSize));
    }

    public static void setDefault(JComponent component) {
        component.setSize(component.getPreferredSize());
        component.setSize(component.getWidth()+PADDING, component.getHeight()+PADDING);
        component.setBackground(BLUE);
        component.setForeground(Color.WHITE);
    }

    public static void setBorder(JComponent component, int borderSize){
        component.setBorder(BorderFactory.createLineBorder(Color.WHITE, borderSize));
    }

    public static void enlarge(JComponent component, int magNum) {
        component.setSize(component.getWidth()*magNum, component.getHeight()*magNum);
    }

    public static void setCenter(JComponent content, JComponent container) {
        content.setLocation((container.getWidth() - content.getWidth())/2, (container.getHeight() - content.getHeight())/2);
    }

    public static void setYCoordinate(JComponent component, int y) {
        component.setLocation(component.getLocation().x, y);
    }

    public static void setXCoordinate(JComponent component, int x) {
        component.setLocation(x, component.getLocation().y);
    }
}
