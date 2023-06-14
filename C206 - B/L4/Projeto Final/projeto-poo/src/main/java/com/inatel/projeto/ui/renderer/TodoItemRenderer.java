package com.inatel.projeto.ui.renderer;

import com.inatel.projeto.objects.Todo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TodoItemRenderer extends JPanel implements ListCellRenderer<Todo> {
    private JLabel label;
    private JButton deleteButton;

    public TodoItemRenderer() {
        setLayout(new BorderLayout(10, 0));

        label = new JLabel();
        try {
            deleteButton = new JButton(scaleImage(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
                    "/delete.png"))), 20, 20));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        deleteButton.addActionListener(e -> {
            System.out.println("Edit clicked");
        });

        add(label, BorderLayout.CENTER);
        add(deleteButton, BorderLayout.WEST);

        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


    }

    public Component getListCellRendererComponent(JList<? extends Todo> list, Todo value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        label.setText(value.getMessage());
        label.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setEnabled(list.isEnabled());
        deleteButton.setEnabled(list.isEnabled());
        putClientProperty("item", value);

        return this;
    }

    public ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w)
        {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h)
        {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
}
