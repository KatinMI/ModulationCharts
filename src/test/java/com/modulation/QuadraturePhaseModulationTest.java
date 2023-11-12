package com.modulation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraturePhaseModulationTest {
    private QuadraturePhaseModulation qpm;
@BeforeEach
    public void init(){
    qpm = new QuadraturePhaseModulation(2,5,new int[]{1,1,0,0,1,1,0,0},2);
}
    @ParameterizedTest
    @CsvSource({"1,1","2,0","5,1"})
    public void getCoefficient(int num, double expected){
        try {
            Method method = qpm.getClass().getDeclaredMethod("getCoefficient", int.class);
            method.setAccessible(true);
            assertEquals(expected,method.invoke(qpm, num));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @CsvSource({"1,2","2,0","5,2"})
    public void getSecondCoefficient(int num, double expected){
        try {
            Method method = qpm.getClass().getDeclaredMethod("getSecondCoefficient", int.class);
            method.setAccessible(true);
            assertEquals(expected,method.invoke(qpm, num));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @CsvSource({"1,3.93","2,0.79","5,3.93"})
    public void getThirdCoefficient(int num, double expected){
        try {
            Method method = qpm.getClass().getDeclaredMethod("getThirdCoefficient", int.class);
            method.setAccessible(true);
            assertEquals(expected,
                    Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",method.invoke(qpm,num))));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @CsvSource({"1,7.07","2.5,3.93","5,0.79"})
    public void getPhase(double time, double expected){
    try{
        Method method = qpm.getClass().getDeclaredMethod("getPhase", double.class);
        method.setAccessible(true);
        assertEquals(expected,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",method.invoke(qpm,time))));
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    @ParameterizedTest
    @CsvSource({"0,0","1000,1.0","4001,4.0"})
    public void createDatasetCheckX(int value, double expectedX){
        assertEquals(expectedX,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",qpm.createDataset().getXValue(0,value))));
    }
    @ParameterizedTest
    @CsvSource({"0,1.41","1110,-0.91","3500,1.41"})
    public void createDatasetCheckY(int value, double expectedY){
        assertEquals(expectedY,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",qpm.createDataset().getYValue(0,value))));
    }
}