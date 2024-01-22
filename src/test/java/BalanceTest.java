import static org.assertj.core.api.Assertions.assertThat;

import com.jsorant.kata.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
public class BalanceTest {

  Balance sut = Balance.ZERO();

  @Test
  void shouldInitializeWithZero() {
    assertThat(sut.value).isEqualTo(0);
  }

  @Test
  void shouldAddAnAmount() {
    assertThat(sut.add(Amount.ofValue(5)).value).isEqualTo(5);
  }

  @Test
  void shouldRemoveAnAmount() {
    assertThat(sut.remove(Amount.ofValue(5)).value).isEqualTo(-5);
  }
}
