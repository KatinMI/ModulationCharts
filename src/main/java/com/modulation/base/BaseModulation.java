package com.modulation.base;

import org.jfree.data.xy.XYDataset;

public abstract class BaseModulation {
    protected double amplitude;
    protected double frame;
    protected int[] code;

    public BaseModulation(double amplitude, double frame, int[] code) {
        this.amplitude = amplitude;
        this.frame = frame;
        this.code = code;
    }
    public abstract XYDataset createDataset();
}
