package com.modulation;

import com.modulation.base.BaseModulation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TwoLevelPhaseModulation extends BaseModulation {
    public TwoLevelPhaseModulation(double amplitude, double frame, int[] code) {
        super(amplitude, frame, code);
    }
    private double getSecondLevel(double time){
        double level = 0;
        for (int x = 0; x < 8; x++){
            if(time >= x && time < (x+1)){
                level = code[x];
            }
            if (time == 8.0){
                level = code[7];
            }
        }
        return level;
    }
    @Override
    public XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries signal = new XYSeries("Сигнал");
        for(double t = 0; t <= 8.0 ;t+= 0.001){
            signal.add(t,amplitude * Math.cos(2*Math.PI * frame * t + Math.PI * getSecondLevel(t)));
        }
        dataset.addSeries(signal);
        return dataset;
    }
}
