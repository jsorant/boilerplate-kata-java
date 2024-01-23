package com.jsorant.kata;

import java.time.LocalDate;

public record Line(int amount, Balance balance, LocalDate date) {
  public static LineBuilder builder() {
    return new LineBuilder();
  }

  public static final class LineBuilder {

    private int amount;
    private Balance balance;
    private LocalDate date;

    public LineBuilder withDeposit(int amount) {
      this.amount = amount;
      return this;
    }

    public LineBuilder withWithdraw(int amount) {
      this.amount = -amount;
      return this;
    }

    public LineBuilder withBalance(Balance balance) {
      this.balance = balance;
      return this;
    }

    public LineBuilder withDate(LocalDate date) {
      this.date = date;
      return this;
    }

    public Line build() {
      return new Line(amount, balance, date);
    }
  }
}
