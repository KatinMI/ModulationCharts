package com.modulation.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private DataDialog dataDialog;
    private MainPanel panel;
    public MainFrame() {
        super("Графики модуляций");
        setSize(1280,720);
        setVisible(true);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenuItem dataMenu = new JMenuItem("Ввести данные");
        dataMenu.setBackground(Color.gray);
        dataMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if (dataDialog == null) dataDialog = new DataDialog(MainFrame.this,panel);
            dataDialog.setVisible(true);
            }
        });
        menuBar.add(dataMenu);
        panel = new MainPanel();
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }


}
