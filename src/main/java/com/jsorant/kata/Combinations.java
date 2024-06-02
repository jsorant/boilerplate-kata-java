package com.jsorant.kata;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Combinations(List<Combination> values) {
  public static final Combinations EMPTY = Combinations.of(List.of());

  public static Combinations of(List<Combination> combinations) {
    return new Combinations(combinations);
  }

  public Combinations add(Combination combination) {
    return Combinations.of(Stream.concat(values.stream(), Stream.of(combination)).distinct().collect(Collectors.toList()));
  }
}
