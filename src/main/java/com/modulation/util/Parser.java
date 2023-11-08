package com.modulation.util;

public class Parser {
    private Parser() {

    }

    public static int[] getArrayOnString(String text){
        int[] arr = new int[8];
        for (int x = 0; x < text.length(); x++){
            if (isNumeric(String.valueOf(text.charAt(x)))) {
                arr[x] = Integer.parseInt(String.valueOf(text.charAt(x)));
               if (arr[x] != 0 && arr[x] != 1){
                   arr[x] = 0;
               }
            } else arr[x] = 0;
            }
        return arr;
    }
    public static double parseDouble(String text){
        if (isNumeric(text)) return Double.parseDouble(text);
        else return 2;
    }
    public static int parseInt(String text){
        if (isNumeric(text)) return Integer.parseInt(text);
        else return 2;
    }
    private static boolean isNumeric(String text) {
        if (text == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(text);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
