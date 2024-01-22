package com.jsorant.kata;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

  public static final int FRAMES = 10;
  private final List<Integer> rolls = new ArrayList<>();

  public void roll(int pins) {
    rolls.add(pins);
  }

  public int score() {
    int result = 0;
    int firstRollOfFrameIndex = 0;

    for (int frame = 0; frame < FRAMES; frame++) {
      result += frameScore(firstRollOfFrameIndex);
      firstRollOfFrameIndex += incrementToNextFrameIndex(firstRollOfFrameIndex);
    }

    return result;
  }

  private int incrementToNextFrameIndex(int firstRollOfFrameIndex) {
    if (isStrike(firstRollOfFrameIndex)) {
      return 1;
    }
    return 2;
  }

  private int frameScore(int firstRollOfFrameIndex) {
    if (isStrike(firstRollOfFrameIndex)) {
      return 10 + strikeBonus(firstRollOfFrameIndex);
    } else if (isSpare(firstRollOfFrameIndex)) {
      return 10 + spareBonus(firstRollOfFrameIndex);
    }
    return rolls.get(firstRollOfFrameIndex) + rolls.get(firstRollOfFrameIndex + 1);
  }

  private boolean isStrike(int firstRollOfFrameIndex) {
    return rolls.get(firstRollOfFrameIndex) == 10;
  }

  private int strikeBonus(int firstRollOfFrameIndex) {
    return rolls.get(firstRollOfFrameIndex + 1) + rolls.get(firstRollOfFrameIndex + 2);
  }

  private boolean isSpare(int firstRollOfFrameIndex) {
    return rolls.get(firstRollOfFrameIndex) + rolls.get(firstRollOfFrameIndex + 1) == 10;
  }

  private Integer spareBonus(int firstRollOfFrameIndex) {
    return rolls.get(firstRollOfFrameIndex + 2);
  }
}
