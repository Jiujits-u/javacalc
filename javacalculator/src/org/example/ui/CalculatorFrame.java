package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    private JTextField display;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

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

        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buttonsPanel.setBackground(new Color(24, 24, 24));

        String[] buttons = {
                "C", "", "", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", ""
        };

        for (String text : buttons) {
            if (text.isEmpty()) {
                JPanel empty = new JPanel();
                empty.setBackground(new Color(24, 24, 24));
                buttonsPanel.add(empty);
            } else {
                JButton button = createButton(text);
                buttonsPanel.add(button);
            }
        }

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 22));

        if (text.matches("[0-9.]")) {
            button.setBackground(new Color(45, 45, 45));
        } else {
            button.setBackground(new Color(60, 60, 60));
        }

        button.addActionListener(e -> onButtonClick(text));

        return button;
    }

    private void onButtonClick(String text) {
        if (text.matches("[0-9]")) {
            appendDigit(text);
            return;
        }

        if (text.equals(".")) {
            appendDot();
            return;
        }

        if (text.equals("C")) {
            clearAll();
            return;
        }

        if (text.equals("=")) {
            calculateResult();
            return;
        }

        if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
            setOperator(text);
        }
    }

    private void appendDigit(String digit) {
        if (startNewNumber || display.getText().equals("0")) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    private void appendDot() {
        if (startNewNumber) {
            display.setText("0.");
            startNewNumber = false;
            return;
        }

        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void clearAll() {
        display.setText("0");
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }

    private void setOperator(String op) {
        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        startNewNumber = true;
    }

    private void calculateResult() {
        if (operator.isEmpty()) {
            return;
        }

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        try {
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        throw new ArithmeticException();
                    }
                    result = firstNumber / secondNumber;
                    break;
            }

            display.setText(formatNumber(result));

        } catch (ArithmeticException e) {
            display.setText("Ошибка");
        }

        operator = "";
        startNewNumber = true;
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}