package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    private JTextField display;

    public CalculatorFrame() {
        setTitle("Калькулятор");
        setSize(520, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBackground(new Color(24, 24, 24));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(24, 24, 24));

        JLabel modeLabel = new JLabel("Программист");
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headerPanel.add(modeLabel, BorderLayout.WEST);

        display = new JTextField("0");
        display.setEditable(false);
        display.setBackground(new Color(24, 24, 24));
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Consolas", Font.BOLD, 42));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
        display.setPreferredSize(new Dimension(0, 80));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(24, 24, 24));
        displayPanel.add(display, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout(0, 12));
        topPanel.setBackground(new Color(24, 24, 24));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(displayPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        buttonsPanel.setBackground(new Color(24, 24, 24));

        String[] buttons = {
                "7", "8", "9",
                "4", "5", "6",
                "1", "2", "3",
                "0", ".", "C"
        };

        for (String text : buttons) {
            JButton button = createButton(text);
            buttonsPanel.add(button);
        }

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setBackground(new Color(45, 45, 45));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 22));

        button.addActionListener(e -> onButtonClick(text));

        return button;
    }

    private void onButtonClick(String text) {
        if (text.equals("C")) {
            display.setText("0");
            return;
        }

        if (text.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            return;
        }

        if (display.getText().equals("0")) {
            display.setText(text);
        } else {
            display.setText(display.getText() + text);
        }
    }
}