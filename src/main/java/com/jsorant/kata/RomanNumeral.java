package com.jsorant.kata;

import java.util.List;
import java.util.Map;

public class RomanNumeral {

    private static Map<Integer, String> conv = Map.of(10, "X");

    private static List<Conversion> conversions = List.of(
            new Conversion(10, "X"),
            new Conversion(9, "IX"),
            new Conversion(5, "V"),
            new Conversion(4, "IV"),
            new Conversion(1, "I")
    );

    // Immuabilité
    // Tell don't ask : avoid setters and getters
    // Demeter law : avoid foo.getBar().getBaz()
    public static String convertRomanNumeral(int arabicNumber) {
        int remaining = arabicNumber;
        StringBuilder result = new StringBuilder();
//        conv.entrySet().forEach(conversion -> {
//            var arabicNumber = conversion.getKey();
//            var romanNumber = conversion.getValue();
//
//        });
        for (Conversion conversion : conversions) {
            while (remaining >= conversion.arabicNumber()) {
                result.append(conversion.romanNumber());
                //        👇 mutation ici
                remaining -= conversion.arabicNumber();
            }
        }

        return result.toString();
    }

    record Conversion(int arabicNumber, String romanNumber) {
    }
}
