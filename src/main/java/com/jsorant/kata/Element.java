package com.jsorant.kata;

public record Element(int width) {
  public static Element of(int width) {
    return new Element(width);
  }
}
