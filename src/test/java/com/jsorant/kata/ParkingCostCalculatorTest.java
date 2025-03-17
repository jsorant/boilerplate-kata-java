package com.jsorant.kata;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.time.api.DateTimes;

@UnitTest
public class ParkingCostCalculatorTest {

  ParkingCostCalculator calculator = new ParkingCostCalculator();

  @Property
  boolean shouldNotCalculateParkingCostWithInvalidLeavingDates(@ForAll Instant leavingDate) {
    Instant now = Instant.now();

    try {
      calculator.parkingCostForLeavingDate(leavingDate);
    } catch (RuntimeException e) {
      if (leavingDate.isBefore(now)) return e.getMessage().equals("Cannot have a leaving date in the past"); else if (
        leavingDate.isAfter(now.plus(7, ChronoUnit.DAYS))
      ) return e.getMessage().equals("Cannot have a leaving date in more than seven days");
    }

    return leavingDate.isAfter(now) && leavingDate.isBefore(now.plus(7, ChronoUnit.DAYS));
  }

  @Property
  boolean shouldNotCalculateParkingCostWithLeavingDatesInThePast(@ForAll("leavingDatesInThePast") Instant leavingDate) {
    try {
      calculator.parkingCostForLeavingDate(leavingDate);
    } catch (RuntimeException e) {
      return e.getMessage().equals("Cannot have a leaving date in the past");
    }

    return false;
  }

  @Provide
  Arbitrary<Instant> leavingDatesInThePast() {
    return DateTimes.instants().atTheLatest(Instant.now());
  }

  @Property
  boolean shouldNotCalculateParkingCostWithLeavingDatesInMoreThanSevenDays(@ForAll("leavingDatesInMoreThanSevenDays") Instant leavingDate) {
    try {
      calculator.parkingCostForLeavingDate(leavingDate);
    } catch (RuntimeException e) {
      return e.getMessage().equals("Cannot have a leaving date in more than seven days");
    }

    return false;
  }

  @Provide
  Arbitrary<Instant> leavingDatesInMoreThanSevenDays() {
    return DateTimes.instants().atTheEarliest(Instant.now().plus(7, ChronoUnit.DAYS).plus(1, ChronoUnit.SECONDS));
  }

  @Property
  boolean shouldCalculateParkingCostWithValidLeavingDates(@ForAll("validLeavingDates") Instant leavingDate) {
    calculator.parkingCostForLeavingDate(leavingDate);
    return true; // should not throw
  }

  @Provide
  Arbitrary<Instant> validLeavingDates() {
    return DateTimes
      .instants()
      .atTheEarliest(Instant.now().plus(1, ChronoUnit.SECONDS))
      .atTheLatest(Instant.now().plus(7, ChronoUnit.DAYS));
  }
}
