package com.modulation.base;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public abstract class BaseFrequencyModulation extends BaseModulation{
    public BaseFrequencyModulation(double amplitude, double frame, int[] code) {
        super(amplitude, frame, code);
    }
    protected abstract double getFrame(double time);
    @Override
    public XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries signal = new XYSeries("Сигнал");
        for(double t = 0; t <= 8.0 ;t+= 0.001){
            signal.add(t,amplitude * Math.cos(2*Math.PI * getFrame(t) * t));
        }
        dataset.addSeries(signal);
        return dataset;
    }
}

