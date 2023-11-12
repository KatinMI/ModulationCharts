package com.modulation;

import com.modulation.base.BaseModulation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static java.lang.Math.*;

public class QuadraturePhaseModulation extends BaseModulation {
    private int bits;
    public QuadraturePhaseModulation(double amplitude, double frame, int[] code, int bits) {
        super(amplitude, frame, code);
        this.bits = bits;
    }
    private double getCoefficient(int num){
        return code[num] * Math.pow(2,bits - num % bits - 1);
    }
    private double getSecondCoefficient(int num) {
        double one = 0;
        for (int x = num * bits; x < num * bits + bits; x++) {
            one += getCoefficient(num);
        }
        return one;
    }
    private double getThirdCoefficient(int num){
        return  PI/pow(2,bits) + (PI / bits) * getSecondCoefficient(num);
    }
    private double getPhase(double time){
        double phase = 0;
        for (int x = 0; x < 8 / bits - 1; x++){
            if(time >= x * bits && time < (x * bits+bits)){
                phase = getThirdCoefficient(x);
            }
            if (time == 8.0){
                phase = getThirdCoefficient(8 / bits) * bits;
            }
        }
        return phase;
    }
    @Override
    public XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries signal = new XYSeries("Сигнал");
        for(double t = 0; t <= 8.0 ;t+= 0.001){
            signal.add(t,amplitude * Math.cos(2*Math.PI * frame * t + getPhase(t)));
        }
        dataset.addSeries(signal);
        return dataset;
    }
}
