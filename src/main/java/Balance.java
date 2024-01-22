public class Balance {

  public final int value;

  private Balance(int value) {
    this.value = value;
  }

  static Balance ZERO() {
    return new Balance(0);
  }

  public Balance add(Amount amount) {
    return new Balance(value + amount.value);
  }

  public Balance remove(Amount amount) {
    return new Balance(value - amount.value);
  }
}
