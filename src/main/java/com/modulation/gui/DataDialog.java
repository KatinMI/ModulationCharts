package com.modulation.gui;

import com.modulation.util.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataDialog extends JDialog {

    private MainPanel panel;
    private JTextField amplitude, frame, code, bits;
    private JButton confirm;
    public DataDialog(Frame owner, MainPanel panel) {
        super(owner, "Ввод данных",true);
        this.panel = panel;
        amplitude = new JTextField(10);
        frame = new JTextField(10);
        code = new JTextField(8);
        bits = new JTextField(1);
        confirm = new JButton("Подтвердить");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setData(Parser.parseDouble(amplitude.getText()),
                        Parser.parseDouble(frame.getText()),
                        Parser.getArrayOnString(code.getText()),
                        Parser.parseInt(bits.getText()));
                setVisible(false);
            }
        });
        setLayout(new FlowLayout());
        add(new JLabel("Амплитуда:"));
        add(amplitude);
        add(new JLabel("Частота:"));
        add(frame);
        add(new JLabel("Сообщение:"));
        add(code);
        add(new JLabel("L:"));
        add(bits);
        add(confirm);
        setSize(720,100);
    }
}
