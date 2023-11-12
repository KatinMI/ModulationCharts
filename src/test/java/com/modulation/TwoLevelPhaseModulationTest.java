package com.modulation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoLevelPhaseModulationTest {
    private TwoLevelPhaseModulation tlpm;
    @BeforeEach
    public void init(){
        tlpm = new TwoLevelPhaseModulation(2,5,new int[]{1,1,0,0,1,1,0,0});
    }
    @ParameterizedTest
    @CsvSource({"0,1","2,0","5,1"})
    public void getSecondLevel(double time, double expected){
        try{
            Method method = tlpm.getClass().getDeclaredMethod("getSecondLevel", double.class);
            method.setAccessible(true);
            assertEquals(expected, method.invoke(tlpm, time));
        } catch (Exception e){
        e.printStackTrace();
        }
    }
    @ParameterizedTest
    @CsvSource({"0,0","1000,1.0","4001,4.0"})
    public void createDatasetCheckX(int value, double expectedX){
        assertEquals(expectedX,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",tlpm.createDataset().getXValue(0,value))));
    }
    @ParameterizedTest
    @CsvSource({"0,-2","500,2","3500,-2"})
    public void createDatasetCheckY(int value, double expectedY){
        assertEquals(expectedY,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",tlpm.createDataset().getYValue(0,value))));
    }
}