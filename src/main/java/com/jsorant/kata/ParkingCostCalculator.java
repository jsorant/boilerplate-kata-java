package com.jsorant.kata;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ParkingCostCalculator {

  public Amount parkingCostForLeavingDate(Instant leavingDate) {
    if (leavingDate.isBefore(Instant.now())) throw new RuntimeException("Cannot have a leaving date in the past");

    if (leavingDate.isAfter(Instant.now().plus(7, ChronoUnit.DAYS))) throw new RuntimeException(
      "Cannot have a leaving date in more than seven days"
    );

    return Amount.of(15, Currency.EURO);
  }
}
