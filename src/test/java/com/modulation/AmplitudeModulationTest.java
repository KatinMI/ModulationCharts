package com.modulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AmplitudeModulationTest {
private AmplitudeModulation am;
@BeforeEach
    public void init(){
    am = new AmplitudeModulation(2,5,new int[]{1,1,0,0,1,1,0,0});
}

@ParameterizedTest
@CsvSource({ "0,2.0",
        "1.0,2.0",
        "8.0,2.0"})
    public void getSignalBase(double time, double expected) {
    try {
        Method method = am.getClass().getDeclaredMethod("getSignalBase", double.class);
        method.setAccessible(true);
        assertEquals(expected, method.invoke(am, time));
    } catch (Exception e){
        e.printStackTrace();
    }
}
@ParameterizedTest
@CsvSource({"0,1","3.5, 0.2","5.2,1"})
public void getAmplitudeMultiplier(double time, double expected){
try {
    Method method = am.getClass().getDeclaredMethod("getAmplitudeMultiplier", double.class);
    method.setAccessible(true);
    assertEquals(expected, method.invoke(am, time));
}catch (Exception e){
    e.printStackTrace();
}
}
@ParameterizedTest
    @CsvSource({"0,0","1000,1.0","4001,4.0"})
    public void createDatasetCheckX(int value, double expectedX){
    assertEquals(expectedX,
            Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",am.createDataset().getXValue(0,value))));
    }
    @ParameterizedTest
    @CsvSource({"0,2.0","1000,2.0","3500,-0.4"})
    public void createDatasetCheckY(int value, double expectedY){
        assertEquals(expectedY,
                Double.parseDouble(String.format(Locale.ENGLISH,"%.2f",am.createDataset().getYValue(0,value))));
    }
}