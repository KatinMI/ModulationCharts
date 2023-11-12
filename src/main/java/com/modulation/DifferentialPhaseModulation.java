package com.modulation;

import com.modulation.base.BaseModulation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DifferentialPhaseModulation extends BaseModulation {
    private int h = 0;
    private double timePrev;
    public DifferentialPhaseModulation(double amplitude, double frame, int[] code) {
        super(amplitude, frame, code);
    }
    private int changePhase(double time){

        for (int x = 0; x < 8; x++) {
            if (time >= x && time < (x + 1) && Math.floor(time) > Math.floor(timePrev)) {
                if (code[x] == 1 ) {
                    h += 1;
                }
            }
            if (time == 8.0) {
                if (code[7] == 1 ) {
                    h += 1;
                }
            }
        }
        timePrev = time;
        return h;
    }
    @Override
    public XYDataset createDataset() {
        XYSeriesCollection dataset = new  XYSeriesCollection();
        XYSeries signal = new XYSeries("Сигнал");
        for(double t = 0; t <= 8.0 ;t+= 0.001){
            signal.add(t,amplitude * Math.cos(2*Math.PI * frame * t + Math.PI * changePhase(t)));
        }
        dataset.addSeries(signal);
        return dataset;
    }
}
