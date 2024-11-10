package com.jsorant.kata;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record GameOfLife(List<Cell> aliveCells) {
  public GameOfLife next() {
    return new GameOfLifeBuilder()
      .appendCells(cellsThatStayAlive())
      .appendCells(cellsThatRebirth())
      .build();
  }

  private Stream<Cell> cellsThatStayAlive() {
    return this.aliveCells.stream()
      .filter(this::cellShouldStayAlive);
  }

  private Stream<Cell> cellsThatRebirth() {
    return this.aliveCells.stream()
      .flatMap(Cell::possibleNeighbours)
      .filter(this::cellShouldRebirth);
  }

  private boolean cellShouldRebirth(Cell cell) {
    return cell.neighboursCount(this.aliveCells) == 3;
  }

  private boolean cellShouldStayAlive(Cell cell) {
    return cell.neighboursCount(this.aliveCells) == 2 || cell.neighboursCount(this.aliveCells) == 3;
  }

  private static final class GameOfLifeBuilder {
    private Stream<Cell> cells = Stream.of();

    GameOfLifeBuilder appendCells(Stream<Cell> cells) {
      this.cells = Stream.concat(this.cells, cells);
      return this;
    }

    public GameOfLife build() {
      Set<Cell> uniqueCells = cells.collect(Collectors.toSet());
      return new GameOfLife(uniqueCells.stream().toList());
    }
  }
}
