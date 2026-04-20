package org.example.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    private JTextField display;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    private DefaultListModel<String> memoryModel;
    private JList<String> memoryList;

    public CalculatorFrame() {
        setTitle("Калькулятор");
        setSize(820, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(18, 18, 18));

        JPanel contentPanel = new JPanel(new BorderLayout(14, 0));
        contentPanel.setBackground(new Color(18, 18, 18));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        JPanel calculatorPanel = createCalculatorPanel();
        JPanel memoryPanel = createMemoryPanel();

        contentPanel.add(calculatorPanel, BorderLayout.CENTER);
        contentPanel.add(memoryPanel, BorderLayout.EAST);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private JPanel createCalculatorPanel() {
        JPanel calculatorPanel = new JPanel(new BorderLayout(0, 10));
        calculatorPanel.setBackground(new Color(18, 18, 18));

        JPanel topWrapper = new JPanel(new BorderLayout(0, 8));
        topWrapper.setBackground(new Color(18, 18, 18));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(18, 18, 18));

        JLabel modeLabel = new JLabel("☰  Программист");
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerPanel.add(modeLabel, BorderLayout.WEST);

        JPanel systemsPanel = new JPanel(new GridLayout(4, 2, 6, 4));
        systemsPanel.setBackground(new Color(18, 18, 18));
        systemsPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        systemsPanel.add(createSmallLabel("HEX"));
        systemsPanel.add(createSmallValue("0"));
        systemsPanel.add(createSmallLabel("DEC"));
        systemsPanel.add(createSmallValue("0"));
        systemsPanel.add(createSmallLabel("OCT"));
        systemsPanel.add(createSmallValue("0"));
        systemsPanel.add(createSmallLabel("BIN"));
        systemsPanel.add(createSmallValue("0"));

        display = new JTextField("0");
        display.setEditable(false);
        display.setBackground(new Color(18, 18, 18));
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Consolas", Font.BOLD, 38));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(new LineBorder(new Color(120, 120, 120), 1));
        display.setPreferredSize(new Dimension(0, 62));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(18, 18, 18));
        displayPanel.add(display, BorderLayout.CENTER);

        topWrapper.add(headerPanel, BorderLayout.NORTH);
        topWrapper.add(displayPanel, BorderLayout.CENTER);
        topWrapper.add(systemsPanel, BorderLayout.SOUTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 4, 4));
        buttonsPanel.setBackground(new Color(18, 18, 18));

        String[] buttons = {
                "C", "⌫", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        for (String text : buttons) {
            buttonsPanel.add(createButton(text));
        }

        calculatorPanel.add(topWrapper, BorderLayout.NORTH);
        calculatorPanel.add(buttonsPanel, BorderLayout.CENTER);

        return calculatorPanel;
    }

    private JPanel createMemoryPanel() {
        JPanel memoryPanel = new JPanel(new BorderLayout());
        memoryPanel.setBackground(new Color(24, 24, 24));
        memoryPanel.setPreferredSize(new Dimension(220, 0));
        memoryPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        JPanel memoryHeader = new JPanel(new BorderLayout());
        memoryHeader.setBackground(new Color(24, 24, 24));

        JLabel memoryTitle = new JLabel("Память");
        memoryTitle.setForeground(Color.WHITE);
        memoryTitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(90, 90, 90));
        separator.setBackground(new Color(90, 90, 90));

        JPanel titleWrap = new JPanel(new BorderLayout(0, 6));
        titleWrap.setBackground(new Color(24, 24, 24));
        titleWrap.add(memoryTitle, BorderLayout.NORTH);
        titleWrap.add(separator, BorderLayout.SOUTH);

        memoryHeader.add(titleWrap, BorderLayout.NORTH);

        memoryModel = new DefaultListModel<>();
        memoryList = new JList<>(memoryModel);
        memoryList.setBackground(new Color(24, 24, 24));
        memoryList.setForeground(new Color(220, 220, 220));
        memoryList.setFont(new Font("Consolas", Font.PLAIN, 14));
        memoryList.setSelectionBackground(new Color(50, 50, 50));
        memoryList.setBorder(BorderFactory.createEmptyBorder(8, 4, 8, 4));

        JScrollPane scrollPane = new JScrollPane(memoryList);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(24, 24, 24));
        scrollPane.setBackground(new Color(24, 24, 24));

        JLabel emptyMemoryLabel = new JLabel("В памяти ничего не сохранено");
        emptyMemoryLabel.setForeground(new Color(210, 210, 210));
        emptyMemoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emptyMemoryLabel.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));

        JPanel memoryContentPanel = new JPanel(new BorderLayout());
        memoryContentPanel.setBackground(new Color(24, 24, 24));
        memoryContentPanel.add(emptyMemoryLabel, BorderLayout.NORTH);

        memoryModel.addListDataListener(new javax.swing.event.ListDataListener() {
            @Override
            public void intervalAdded(javax.swing.event.ListDataEvent e) {
                updateMemoryView(memoryContentPanel, scrollPane, emptyMemoryLabel);
            }

            @Override
            public void intervalRemoved(javax.swing.event.ListDataEvent e) {
                updateMemoryView(memoryContentPanel, scrollPane, emptyMemoryLabel);
            }

            @Override
            public void contentsChanged(javax.swing.event.ListDataEvent e) {
                updateMemoryView(memoryContentPanel, scrollPane, emptyMemoryLabel);
            }
        });

        memoryPanel.add(memoryHeader, BorderLayout.NORTH);
        memoryPanel.add(memoryContentPanel, BorderLayout.CENTER);

        return memoryPanel;
    }

    private void updateMemoryView(JPanel memoryContentPanel, JScrollPane scrollPane, JLabel emptyMemoryLabel) {
        memoryContentPanel.removeAll();

        if (memoryModel.isEmpty()) {
            memoryContentPanel.add(emptyMemoryLabel, BorderLayout.NORTH);
        } else {
            memoryContentPanel.add(scrollPane, BorderLayout.CENTER);
        }

        memoryContentPanel.revalidate();
        memoryContentPanel.repaint();
    }

    private JLabel createSmallLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return label;
    }

    private JLabel createSmallValue(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(220, 220, 220));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);

        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBorder(new LineBorder(new Color(55, 55, 55), 1));
        button.setForeground(Color.WHITE);

        if (text.matches("[0-9]") || text.equals(".") || text.equals("+/-")) {
            button.setBackground(new Color(36, 36, 36));
        } else if (text.equals("=")) {
            button.setBackground(new Color(88, 88, 88));
        } else {
            button.setBackground(new Color(47, 47, 47));
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

        if (text.equals("⌫")) {
            backspace();
            return;
        }

        if (text.equals("+/-")) {
            toggleSign();
            return;
        }

        if (text.equals("%")) {
            applyPercent();
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

    private void backspace() {
        if (display.getText().equals("Ошибка")) {
            display.setText("0");
            startNewNumber = true;
            return;
        }

        String text = display.getText();

        if (text.length() <= 1 || (text.length() == 2 && text.startsWith("-"))) {
            display.setText("0");
            startNewNumber = true;
        } else {
            display.setText(text.substring(0, text.length() - 1));
        }
    }

    private void toggleSign() {
        if (display.getText().equals("Ошибка") || display.getText().equals("0")) {
            return;
        }

        if (display.getText().startsWith("-")) {
            display.setText(display.getText().substring(1));
        } else {
            display.setText("-" + display.getText());
        }
    }

    private void applyPercent() {
        if (display.getText().equals("Ошибка")) {
            return;
        }

        double value = Double.parseDouble(display.getText());
        value = value / 100.0;
        display.setText(formatNumber(value));
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

        String firstText = formatNumber(firstNumber);
        String secondText = display.getText();

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

            String resultText = formatNumber(result);
            display.setText(resultText);

            addToMemory(firstText + " " + operator + " " + secondText + " = " + resultText);

        } catch (ArithmeticException e) {
            display.setText("Ошибка");
        }

        operator = "";
        startNewNumber = true;
    }

    private void addToMemory(String record) {
        memoryModel.add(0, record);
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}