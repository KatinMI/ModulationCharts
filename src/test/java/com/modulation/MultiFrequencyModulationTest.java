package com.modulation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MultiFrequencyModulationTest {
private MultiFrequencyModulation mfm;
@BeforeEach
    public void init(){
    mfm = new MultiFrequencyModulation(2,5,new int[]{1,1,0,0,1,1,0,0}, 2);
}
@ParameterizedTest
    @CsvSource({"1,1","2,0","5,1"})
    public void getCoefficient(int num, double expected){
    try {
        Method method = mfm.getClass().getDeclaredMethod("getCoefficient", int.class);
        method.setAccessible(true);
        assertEquals(expected,method.invoke(mfm, num));
    } catch (Exception e){
    e.printStackTrace();
    }
}
    @ParameterizedTest
    @CsvSource({"1,2","2,0","5,2"})
    public void getSecondCoefficient(int num, double expected){
        try {
            Method method = mfm.getClass().getDeclaredMethod("getSecondCoefficient", int.class);
            method.setAccessible(true);
            assertEquals(expected,method.invoke(mfm, num));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @CsvSource({"1,5","2,1","5,5"})
    public void getThirdCoefficient(int num, double expected){
        try {
            Method method = mfm.getClass().getDeclaredMethod("getThirdCoefficient", int.class);
            method.setAccessible(true);
            assertEquals(expected,method.invoke(mfm, num));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @CsvSource({"1,9","2,5","5,1"})
    public void getFrame(double time, double expected){
    assertEquals(expected,mfm.getFrame(time));
    }
    @ParameterizedTest
    @CsvSource({"0,0","1000,1.0","4001,4.0"})
    public void createDatasetCheckX(int value, double expectedX){
        assertEquals(expectedX,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",mfm.createDataset().getXValue(0,value))));
    }
    @ParameterizedTest
    @CsvSource({"0,2.0","1000,2.0","3500,-2.0"})
    public void createDatasetCheckY(int value, double expectedY){
        assertEquals(expectedY,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",mfm.createDataset().getYValue(0,value))));
    }
}