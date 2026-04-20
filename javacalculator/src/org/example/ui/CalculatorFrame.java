package org.example.ui;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    public CalculatorFrame() {
        setTitle("Калькулятор");
        setSize(780, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(24, 24, 24));
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(24, 24, 24));
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(18, 18, 10, 18));

        JLabel modeLabel = new JLabel("Программист");
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel memoryLabel = new JLabel("Память");
        memoryLabel.setForeground(Color.WHITE);
        memoryLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        memoryLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel rightTopPanel = new JPanel(new BorderLayout());
        rightTopPanel.setBackground(new Color(24, 24, 24));
        rightTopPanel.setPreferredSize(new Dimension(180, 50));
        rightTopPanel.add(memoryLabel, BorderLayout.NORTH);

        topPanel.add(modeLabel, BorderLayout.WEST);
        topPanel.add(rightTopPanel, BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(24, 24, 24));
        centerPanel.setLayout(new GridLayout(1, 2, 12, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 18, 18, 18));

        JPanel calculatorArea = new JPanel();
        calculatorArea.setBackground(new Color(24, 24, 24));

        JPanel memoryArea = new JPanel();
        memoryArea.setBackground(new Color(24, 24, 24));
        memoryArea.setLayout(new BorderLayout());

        JLabel memoryInfo = new JLabel("В памяти ничего не сохранено");
        memoryInfo.setForeground(new Color(210, 210, 210));
        memoryInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        memoryInfo.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

        memoryArea.add(memoryInfo, BorderLayout.NORTH);

        centerPanel.add(calculatorArea);
        centerPanel.add(memoryArea);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }
}