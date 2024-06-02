package com.jsorant.kata;

import java.util.List;

public class WardRobe {

  private final int wallWidth;
  private final List<Element> elements;

  public WardRobe(int wallWidth, List<Element> elements) {
    this.wallWidth = wallWidth;
    this.elements = elements;
  }

  public Combinations combinations() {
    Combinations result = Combinations.EMPTY;

    for (Element element : elements) {
      result = findCombination(element, wallWidth, Combination.EMPTY, result);
    }

    return result;
  }

  private Combinations findCombination(Element element, int wallWidth, Combination currentCombination, Combinations result) {
    if (wallWidth >= element.width()) {
      currentCombination = currentCombination.add(element);

      if (fitsExactly(wallWidth, element)) {
        return result.add(currentCombination);
      }

      for (Element anotherElement : elements) {
        int remainingWallWidth = wallWidth - element.width();
        result = findCombination(anotherElement, remainingWallWidth, currentCombination, result);
      }
    }

    return result;
  }

  private static boolean fitsExactly(int wallWidth, Element element) {
    return wallWidth == element.width();
  }
}
