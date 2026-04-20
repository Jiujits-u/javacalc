package org.example.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

        JPanel mainPanel = new JPanel(new BorderLayout(0, 12));
        mainPanel.setBackground(new Color(18, 18, 18));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(18, 18, 18));

        JLabel modeLabel = new JLabel("Программист");
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerPanel.add(modeLabel, BorderLayout.WEST);

        JLabel memoryLabel = new JLabel("Память");
        memoryLabel.setForeground(new Color(220, 220, 220));
        memoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        headerPanel.add(memoryLabel, BorderLayout.EAST);

        display = new JTextField("0");
        display.setEditable(false);
        display.setBackground(new Color(18, 18, 18));
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Consolas", Font.BOLD, 42));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(new LineBorder(new Color(90, 90, 90), 1));
        display.setPreferredSize(new Dimension(0, 82));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(18, 18, 18));
        displayPanel.add(display, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout(0, 12));
        topPanel.setBackground(new Color(18, 18, 18));
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(displayPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonsPanel.setBackground(new Color(18, 18, 18));

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
                empty.setBackground(new Color(18, 18, 18));
                buttonsPanel.add(empty);
            } else {
                buttonsPanel.add(createButton(text));
            }
        }

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setBorder(new LineBorder(new Color(70, 70, 70), 1));

        if (text.matches("[0-9]") || text.equals(".")) {
            button.setBackground(new Color(45, 45, 45));
        } else if (text.equals("=")) {
            button.setBackground(new Color(88, 88, 88));
        } else {
            button.setBackground(new Color(58, 58, 58));
        }

        button.setForeground(Color.WHITE);
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
        if (startNewNumber || display.getText().equals("0") || display.getText().equals("Ошибка")) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    private void appendDot() {
        if (display.getText().equals("Ошибка")) {
            display.setText("0.");
            startNewNumber = false;
            return;
        }

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
        if (display.getText().equals("Ошибка")) {
            return;
        }

        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        startNewNumber = true;
    }

    private void calculateResult() {
        if (operator.isEmpty() || display.getText().equals("Ошибка")) {
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