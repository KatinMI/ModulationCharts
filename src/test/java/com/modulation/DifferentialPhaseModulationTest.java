package com.modulation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DifferentialPhaseModulationTest {
private DifferentialPhaseModulation dpm;
@BeforeEach
    public void init(){
    dpm = new DifferentialPhaseModulation(2,5,new int[]{1,1,0,0,1,1,0,0});
}
@Test
public void changePhase(){
    try {
        Method method = dpm.getClass().getDeclaredMethod("changePhase", double.class);
        method.setAccessible(true);
        assertEquals(1, method.invoke(dpm,1.0));
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    @ParameterizedTest
    @CsvSource({"0,0","1000,1.0","4001,4.0"})
    public void createDatasetCheckX(int value, double expectedX){
        assertEquals(expectedX,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",dpm.createDataset().getXValue(0,value))));
    }
    @ParameterizedTest
    @CsvSource({"0,2.0","1000,-2.0","3500,2.0"})
    public void createDatasetCheckY(int value, double expectedY){
        assertEquals(expectedY,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",dpm.createDataset().getYValue(0,value))));
    }
}