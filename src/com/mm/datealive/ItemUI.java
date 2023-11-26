package com.mm.datealive;

import com.intellij.openapi.ui.popup.JBPopup;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * description:
 * @author: humian49712
 * @date: 2023/11/17
 */
public class ItemUI {
    private JLabel name;
    private JPanel mainPanel;
    private JLabel img;

    private MyAction myAction;

    public ItemUI() {

    }

    public JLabel getName() {
        return name;
    }

    public void setName(JLabel name) {
        this.name = name;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JLabel getImg() {
        return img;
    }

    public void setImg(JLabel img) {
        this.img = img;
    }

    public MyAction getMyAction() {
        return myAction;
    }

    public void setMyAction(MyAction myAction) {
        this.myAction = myAction;
    }

}
