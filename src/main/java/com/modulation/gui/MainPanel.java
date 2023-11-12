package com.modulation.gui;

import com.modulation.*;
import com.modulation.base.BaseModulation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JRadioButton am;
    private JRadioButton bfm;
    private JRadioButton dpm;
    private JRadioButton mfm;
    private JRadioButton qpm;
    private JRadioButton tlpm;

    private BaseModulation modulation;
    private JPanel chartPanel;
    private ButtonGroup selectors = new ButtonGroup();
    private double amplitude = 2;
    private double frame = 5;
    private int[] code = {1,1,1,0,0,0,0,1};
    private int bits = 2;
    public MainPanel() {
        modulation = new AmplitudeModulation(amplitude,frame,code);
        chartPanel = createChartsPanel(modulation.createDataset());
        am = new JRadioButton("Амплитудная модуляция",true);
        bfm= new JRadioButton("Бинарная частотная модуляция",false);
        mfm = new JRadioButton("Многочастотная модуляция",false);
        tlpm = new JRadioButton("Двухуровневая фазовая модуляция",false);
        dpm = new JRadioButton("Дифференциальная фазовая модуляция",false);
        qpm = new JRadioButton("Квадратурная фазовая модуляция",false);
        setLayout(new BorderLayout());
        add(chartPanel,BorderLayout.CENTER);
        add(createMenuPanel(),BorderLayout.NORTH);
        setVisible(true);
    }
    public JPanel createMenuPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        selectors.add(am);
        selectors.add(bfm);
        selectors.add(mfm);
        selectors.add(tlpm);
        selectors.add(dpm);
        selectors.add(qpm);
        panel.add(am);
        panel.add(bfm);
        panel.add(mfm);
        panel.add(tlpm);
        panel.add(dpm);
        panel.add(qpm);
        ActionListener listener = e -> {
            if (am.isSelected()) {
                modulation = new AmplitudeModulation(amplitude, frame, code);
                this.remove(chartPanel);
                chartPanel = createChartsPanel(modulation.createDataset());
                this.add(chartPanel);
            }
            if (bfm.isSelected()) {
                modulation = new BinaryFrequencyModulation(amplitude, frame, code);
                this.remove(chartPanel);
                chartPanel = createChartsPanel(modulation.createDataset());
                this.add(chartPanel);
            }
            if (mfm.isSelected()) {
                modulation = new MultiFrequencyModulation(amplitude, frame, code, bits);
                this.remove(chartPanel);
                chartPanel = createChartsPanel(modulation.createDataset());
                this.add(chartPanel);
            }
            if (tlpm.isSelected()) {
                modulation = new TwoLevelPhaseModulation(amplitude, frame, code);
                this.remove(chartPanel);
                chartPanel = createChartsPanel(modulation.createDataset());
                this.add(chartPanel);
            }
            if (dpm.isSelected()) {
                modulation = new DifferentialPhaseModulation(amplitude, frame, code);
                this.remove(chartPanel);
                chartPanel = createChartsPanel(modulation.createDataset());
                this.add(chartPanel);
            }
            if (qpm.isSelected()) {
                modulation = new QuadraturePhaseModulation(amplitude, frame, code, bits);
                this.remove(chartPanel);
                chartPanel = createChartsPanel(modulation.createDataset());
                this.add(chartPanel);
            }
            chartPanel.revalidate();
            chartPanel.repaint();
        };
        am.addActionListener(listener);
        bfm.addActionListener(listener);
        mfm.addActionListener(listener);
        tlpm.addActionListener(listener);
        dpm.addActionListener(listener);
        qpm.addActionListener(listener);
        return panel;
    }
    public void setData(double amplitude, double frame, int[] code, int bits){
        this.amplitude = amplitude;
        this.frame = frame;
        this.code = code;
        this.bits = bits;
        modulation = new AmplitudeModulation(amplitude,frame,code);
        this.remove(chartPanel);
        chartPanel = createChartsPanel(modulation.createDataset());
        this.add(chartPanel);
        chartPanel.revalidate();
        chartPanel.repaint();
        am.setSelected(true);
    }
    public JPanel createChartsPanel(XYDataset dataset) {
        String chartTitle = "График";
        String axisX = "t";
        String axisY = "s(t)";
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, axisX, axisY, dataset);
        return new ChartPanel(chart);
    }

}
