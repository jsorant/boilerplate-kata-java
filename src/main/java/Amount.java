import com.jsorant.kata.shared.error.domain.Assert;

public class Amount {

  public final int value;

  private Amount(int value) {
    this.value = value;
  }

  public static Amount ofValue(int value) {
    Assert.field("value", value).min(0);

    return new Amount(value);
  }
}
