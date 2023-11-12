package com.modulation;

import com.modulation.util.Parser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class ParserTest {
    @ParameterizedTest
    @CsvSource({"'2',2","'5',5","'abc',2"})
    public void parseInt(String text, int excepted){
    assertEquals(excepted, Parser.parseInt(text));
    }
    @ParameterizedTest
    @CsvSource({"'2.0',2.0","'5.0',5","'abc',2.0"})
    public void parseDouble(String text, double excepted){
    assertEquals(excepted,Parser.parseDouble(text));
    }
    @ParameterizedTest
    @CsvSource({"'11001100','1,1,0,0,1,1,0,0'","'11111111','1,1,1,1,1,1,1,1'","'aa11aa11','0,0,1,1,0,0,1,1'"})
    public void getArrayOnString(String text, @ConvertWith(IntArrayConvert.class) int[] excepted){
    assertArrayEquals(excepted,Parser.getArrayOnString(text));
    }
    @ParameterizedTest
    @ValueSource(strings = {"1","3.5","1000"})
    public void isNumeric(String text){
        try {
            Method method = Parser.class.getDeclaredMethod("isNumeric", String.class);
            method.setAccessible(true);
            assertTrue((Boolean) method.invoke(null, text));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"a","2ab4","33.a5"})
    public void isNotNumeric(String text){
        try {
            Method method = Parser.class.getDeclaredMethod("isNumeric", String.class);
            method.setAccessible(true);
            assertFalse((Boolean) method.invoke(null, text));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    static class IntArrayConvert extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
            if (o instanceof String && int[].class.isAssignableFrom(aClass)){
                return Arrays.stream(((String)o).split(",")).mapToInt(Integer::parseInt).toArray();

            } else {
                throw new IllegalArgumentException("Conversion error: " + o.getClass() + " to " + aClass + " not supported");
            }
        }
    }
}
