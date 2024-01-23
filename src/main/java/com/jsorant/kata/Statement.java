package com.jsorant.kata;

import java.util.List;
import java.util.stream.Stream;

public class Statement {

  private final List<Line> lines;

  private Statement(List<Line> lines) {
    this.lines = lines;
  }

  private static Statement withLines(List<Line> lines) {
    return new Statement(lines);
  }

  public static Statement EMPTY() {
    return new Statement(List.of());
  }

  public int linesCount() {
    return this.lines.size();
  }

  public Statement addLine(Line line) {
    List<Line> newLines = Stream.concat(this.lines.stream(), Stream.of(line)).toList();

    return Statement.withLines(newLines);
  }

  public Line line(int index) {
    return this.lines.get(index);
  }
}
