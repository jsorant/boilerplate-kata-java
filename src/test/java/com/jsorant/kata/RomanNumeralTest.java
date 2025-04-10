package com.jsorant.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.jsorant.kata.RomanNumeral.convertRomanNumeral;
import static org.assertj.core.api.Assertions.assertThat;

// 15        10    X
// 15 - 10         X
// 5         5     XV
// 5 - 5           XV

@DisplayName("RomanNumeral")
public class RomanNumeralTest {
    @ParameterizedTest
    @CsvSource({
            "1,I",
            "2,II",
            "3,III",
            "4,IV",
            "5,V",
            "6,VI",
            "7,VII",
            "8,VIII",
            "9,IX",
            "10,X",
            "11,XI",
            "12,XII",
            "15,XV",
    })
    void should_convert_to_roman(int arabicNumber, String expectedRoman) {
        String result = convertRomanNumeral(arabicNumber);
        assertThat(result).isEqualTo(expectedRoman);
    }


}
