package com.modulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
class BinaryFrequencyModulationTest {
private BinaryFrequencyModulation bfm;
@BeforeEach
    public void init(){
    bfm = new BinaryFrequencyModulation(2,5,new int[]{1,1,0,0,1,1,0,0});
}
@ParameterizedTest
    @CsvSource({"0,3","2,1","5,3"})
    public void getFrame(double time, double expected){
    assertEquals(expected,bfm.getFrame(time));
}
    @ParameterizedTest
    @CsvSource({"0,0","1000,1.0","4001,4.0"})
    public void createDatasetCheckX(int value, double expectedX){
        assertEquals(expectedX,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",bfm.createDataset().getXValue(0,value))));
    }
    @ParameterizedTest
    @CsvSource({"0,2.0","1000,2.0","3500,-2.0"})
    public void createDatasetCheckY(int value, double expectedY){
        assertEquals(expectedY,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",bfm.createDataset().getYValue(0,value))));
    }
}