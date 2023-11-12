package com.modulation;

import com.modulation.base.BaseFrequencyModulation;

public class BinaryFrequencyModulation extends BaseFrequencyModulation {
    public BinaryFrequencyModulation(double amplitude, double frame, int[] code) {
        super(amplitude, frame, code);
    }
    @Override
    protected double getFrame(double time){
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

}
