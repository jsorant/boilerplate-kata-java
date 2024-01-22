import java.time.LocalDate;

public record Statement(LocalDate date, int amount, int balance) {
  static StatementBuilder builder() {
    return new StatementBuilder();
  }

  public static final class StatementBuilder {

    LocalDate _date;
    int _amount;
    int _balance;

    public StatementBuilder withDate(LocalDate date) {
      this._date = date;
      return this;
    }

    public StatementBuilder withAmount(int amount) {
      this._amount = amount;
      return this;
    }

    public StatementBuilder withBalance(int balance) {
      this._balance = balance;
      return this;
    }

    public Statement build() {
      return new Statement(_date, _amount, _balance);
    }
  }
}
