package com.jsorant.kata;

import java.util.Objects;

// public record Conversion(int arabicNumber, String romanNumber) {}

// Cette classe est l'équivalent du record ci dessus
// Oui souvent utilisé pour DTO
public final class Conversion {
    //      👇 ne peut pas être muté après initialisation dans le constructeur
    private final int arabicNumber;
    private final String romanNumber;

    public Conversion(int arabicNumber, String romanNumber) {
        this.arabicNumber = arabicNumber;
        this.romanNumber = romanNumber;
    }

    public int arabicNumber() {
        return arabicNumber;
    }

    public String romanNumber() {
        return romanNumber;
    }

    // Egalité par valeurs 👇
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Conversion) obj;
        // Egalité par valeurs 👇
        return this.arabicNumber == that.arabicNumber &&
                Objects.equals(this.romanNumber, that.romanNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arabicNumber, romanNumber);
    }

    @Override
    public String toString() {
        return "Conversion[" +
                "arabicNumber=" + arabicNumber + ", " +
                "romanNumber=" + romanNumber + ']';
    }

}

