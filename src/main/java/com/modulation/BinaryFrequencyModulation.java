package com.modulation;

import com.modulation.base.BaseModulation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BinaryFrequencyModulation extends BaseModulation {
    public BinaryFrequencyModulation(double amplitude, double frame, int[] code) {
        super(amplitude, frame, code);
    }
    private double getFrame(double time){
        double frame = 0;
        for (int x = 0; x < 8; x++){
            if(time >= x && time < (x+1)){
                if(code[x] == 1) frame = 3;
                else frame = 1;
            }
            if (time == 8.0){
                if(code[7] == 1) frame = 3;
                else frame = 1;
            }
        }
        return frame;
    }

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
