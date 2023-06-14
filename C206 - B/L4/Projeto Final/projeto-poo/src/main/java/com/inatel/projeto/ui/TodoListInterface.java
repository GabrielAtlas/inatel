package com.inatel.projeto.ui;

import com.inatel.projeto.objects.Todo;
import com.inatel.projeto.storager.datasources.MySQLDataSource;
import com.inatel.projeto.ui.renderer.TodoItemRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.UUID;

public class TodoListInterface extends JFrame {
    private DefaultListModel<Todo> todoListModel;
    private JList<Todo> todoList;
    private JTextField inputField;
    private JButton addButton;
    private MySQLDataSource mySQLDataSource;

    public TodoListInterface() {
        super("Todo List");

        todoListModel = new DefaultListModel<>();
        todoList = new JList<>(todoListModel);
        todoList.setCellRenderer(new TodoItemRenderer());
        JScrollPane scrollPane = new JScrollPane(todoList);
        inputField = new JTextField(20);
        addButton = new JButton("Adicionar");

        addButton.addActionListener(e -> {
            String item = inputField.getText().trim();
            if (!item.isEmpty()) {
                Todo todo = new Todo(UUID.randomUUID(), item);
                todoListModel.addElement(todo);
                inputField.setText("");
                mySQLDataSource.insert(todo);
            }
        });

        setLayout(new BorderLayout());
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(scrollPane);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        this.connectToDatabase();

    }

    private void connectToDatabase() {
        this.mySQLDataSource = new MySQLDataSource("localhost", "database", "root", "",
                () -> JOptionPane.showMessageDialog(this, "A conexão com banco de dados foi estabelecida"),
                () -> JOptionPane.showMessageDialog(this, "Não foi possível estabelecer a conexão com DB", "Error", JOptionPane.ERROR_MESSAGE));
    }

}
