package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    private JTextField display;

    public CalculatorFrame() {
        setTitle("Калькулятор");
        setSize(780, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(24, 24, 24));
        mainPanel.setLayout(new BorderLayout());

        // ===== ВЕРХ =====
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(24, 24, 24));
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        JLabel modeLabel = new JLabel("Программист");
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setFont(new Font("Arial", Font.BOLD, 22));

        topPanel.add(modeLabel, BorderLayout.WEST);

        // ===== ДИСПЛЕЙ =====
        display = new JTextField("0");
        display.setEditable(false);
        display.setBackground(new Color(30, 30, 30));
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Consolas", Font.BOLD, 36));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(24, 24, 24));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 15));
        displayPanel.add(display, BorderLayout.CENTER);

        // ===== ЦЕНТР =====
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(24, 24, 24));
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(displayPanel, BorderLayout.NORTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }
}