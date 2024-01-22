import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jsorant.kata.UnitTest;
import com.jsorant.kata.shared.error.domain.NumberValueTooLowException;
import org.junit.jupiter.api.Test;

@UnitTest
public class AmountTest {

  @Test
  void shouldNotBuildWithNegativeValue() {
    assertThatThrownBy(() -> Amount.ofValue(-1)).isExactlyInstanceOf(NumberValueTooLowException.class).hasMessageContaining("value");
  }

  @Test
  void shouldHaveTheGivenValue() {
    assertThat(Amount.ofValue(5).value).isEqualTo(5);
  }
}
