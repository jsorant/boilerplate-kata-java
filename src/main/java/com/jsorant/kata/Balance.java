package com.jsorant.kata;

public record Balance(int value) {
  public static Balance EMPTY() {
    return new Balance(0);
  }

  public static Balance of(int value) {
    return new Balance(value);
  }

  public Balance add(int amount) {
    return new Balance(this.value + amount);
  }

  public Balance remove(int amount) {
    return new Balance(this.value - amount);
  }
}
