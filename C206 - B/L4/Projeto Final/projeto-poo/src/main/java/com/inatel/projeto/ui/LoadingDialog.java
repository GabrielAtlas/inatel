package com.inatel.projeto.ui;

import javax.swing.*;
import java.awt.*;
public class LoadingDialog extends JDialog {

    public LoadingDialog(Frame owner, String message) {
        super(owner, "Loading", true);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(200, 100);
        setLocationRelativeTo(owner);

        JLabel label = new JLabel(message);
        add(label);
    }

}
