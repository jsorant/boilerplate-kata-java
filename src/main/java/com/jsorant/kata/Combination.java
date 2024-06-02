package com.jsorant.kata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public record Combination(ArrayList<Element> elements) {
  public static final Combination EMPTY = Combination.of(List.of());

  public static Combination of(List<Element> elements) {
    return new Combination(new ArrayList<>(elements));
  }

  public Combination add(Element element) {
    List<Element> list = new ArrayList<>(Stream.concat(elements.stream(), Stream.of(element)).toList());
    list.sort(Comparator.comparing(Element::width));
    return Combination.of(list);
  }
}
