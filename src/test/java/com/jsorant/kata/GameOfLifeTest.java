package com.jsorant.kata;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
public class GameOfLifeTest {

  @Test
  void shouldHaveNoLivingCells() {
    GameOfLife game = new GameOfLife(List.of());

    assertThat(game.aliveCells()).isEmpty();
  }

  @Test
  void shouldHaveALivingCell() {
    GameOfLife game = new GameOfLife(List.of(new Cell(50, 50)));

    assertThat(game.aliveCells()).containsExactly(new Cell(50, 50));
  }

  @Test
  void oneLivingCellShouldDie() {
    GameOfLife game = new GameOfLife(List.of(new Cell(50, 50)));

    assertThat(game.next().aliveCells()).isEmpty();
  }

  @Test
  void oneLivingCellShouldRemainFromDiagonalLivingCells() {
    GameOfLife game = new GameOfLife(List.of(
      new Cell(49, 49),
      new Cell(50, 50),
      new Cell(51, 51)
    ));

    assertThat(game.next().aliveCells()).containsExactly(
      new Cell(50, 50)
    );
  }

  @Test
  void squareOfLivingCellsShouldRemains() {
    GameOfLife game = new GameOfLife(List.of(
      new Cell(50, 50),
      new Cell(50, 51),
      new Cell(51, 50),
      new Cell(51, 51)
    ));

    assertThat(game.next().aliveCells()).containsExactlyInAnyOrder(
      new Cell(50, 50),
      new Cell(50, 51),
      new Cell(51, 50),
      new Cell(51, 51)
    );
  }

  @Test
  void lineOfLivingCellsShouldMakeAColumn() {
    GameOfLife game = new GameOfLife(List.of(
      new Cell(49, 50),
      new Cell(50, 50),
      new Cell(51, 50)
    ));

    assertThat(game.next().aliveCells()).containsExactlyInAnyOrder(
      new Cell(50, 49),
      new Cell(50, 50),
      new Cell(50, 51)
    );
  }
}

