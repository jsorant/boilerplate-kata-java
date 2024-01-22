package com.jsorant.kata;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@UnitTest
public class BowlingTest {

  BowlingGame game = new BowlingGame();

  @Test
  void shouldScoreZeroForAGutterGame() {
    makeRolls(20, 0);

    assertThat(game.score()).isEqualTo(0);
  }

  @Test
  void shouldScoreTwentyForAGameOfOnes() {
    makeRolls(20, 1);

    assertThat(game.score()).isEqualTo(20);
  }

  @Test
  void shouldScoreASpare() {
    game.roll(6);
    game.roll(4);
    game.roll(5);
    makeRolls(17, 0);

    assertThat(game.score()).isEqualTo(20);
  }

  @Test
  void shouldScoreAStrike() {
    game.roll(10);
    game.roll(4);
    game.roll(5);
    makeRolls(16, 0);

    assertThat(game.score()).isEqualTo(28);
  }

  private void makeRolls(int rolls, int pins) {
    for (int roll = 0; roll < rolls; roll++) {
      game.roll(pins);
    }
  }
}
