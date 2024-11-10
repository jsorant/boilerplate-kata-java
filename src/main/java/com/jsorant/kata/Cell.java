package com.jsorant.kata;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public record Cell(int x, int y) {
  public long neighboursCount(List<Cell> otherCells) {
    return otherCells.stream()
      .filter(this::isNeighbourOf)
      .count();
  }

  public Stream<Cell> possibleNeighbours() {
    return Stream.of(
      new Cell(x - 1, y + 1), new Cell(x, y + 1), new Cell(x + 1, y + 1),
      new Cell(x - 1, y), new Cell(x + 1, y),
      new Cell(x - 1, y - 1), new Cell(x, y - 1), new Cell(x + 1, y - 1)
    );
  }

  private boolean isNeighbourOf(Cell other) {
    return Optional.of(this)
      .filter(cell -> !cell.equals(other))
      .map(cell -> Math.abs(cell.x - other.x) <= 1 && Math.abs(cell.y - other.y) <= 1)
      .orElse(false);
  }
}
