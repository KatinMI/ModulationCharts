package com.modulation;

import com.modulation.base.BaseModulation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AmplitudeModulation extends BaseModulation {
    public AmplitudeModulation(double amplitude, double frame, int[] code) {
        super(amplitude, frame, code);
    }
    private double getSignalBase(double time){
        return amplitude * Math.cos(2 * Math.PI * frame * time);
    }
    private double getAmplitudeMultiplier(double time){
        double multiplier = 0;
        for (int x = 0; x < 8; x++){
        if(time >= x && time < (x+1)){
            if(code[x] == 1) multiplier = 1;
            else multiplier = 0.2;
        }
        if (time == 8.0){
            if(code[7] == 1) multiplier = 1;
            else multiplier = 0.2;
        }
        }
        return multiplier;
    }
    @Override
    public XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries signal = new XYSeries("Сигнал");
        for(double t = 0; t <= 8.0 ;t+= 0.001){
        signal.add(t,getSignalBase(t)*getAmplitudeMultiplier(t));
        }
        dataset.addSeries(signal);
        return dataset;
    }

}
