package com.modulation;

import com.modulation.base.BaseFrequencyModulation;

public class MultiFrequencyModulation extends BaseFrequencyModulation {
    private int bits;

    public MultiFrequencyModulation(double amplitude, double frame, int[] code, int bits) {
        super(amplitude, frame, code);
        this.bits = bits;
    }
    @Override
    protected double getFrame(double time){
        double frame = 0;
        for (int x = 0; x < 8 / bits; x++){
            if(time >= x * bits && time < (x * bits + bits)){
            frame = getThirdCoefficient(x);
            }
            if (time == 8.0){
            frame = getThirdCoefficient(x) * bits;
            }
        }
        return frame;
    }
    private double getCoefficient(int num){
        return code[num] * Math.pow(2,bits - num % bits - 1);
    }
    private double getSecondCoefficient(int num){
        double one = 0;
    for(int x = num * bits; x < num * bits + bits;x++ ){
    one += getCoefficient(num);
    }
    return one;
    }
    private double getThirdCoefficient(int num){
    return 4 + (2 * (getSecondCoefficient(num)+1) - 1 - Math.pow(2,bits));
    }
}
