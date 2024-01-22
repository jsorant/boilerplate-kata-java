import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.jsorant.kata.UnitTest;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@UnitTest
@ExtendWith(MockitoExtension.class)
public class AccountTest {

  @Mock
  DateProvider dateProvider;

  Account sut;

  @BeforeEach
  void initSut() {
    lenient().when(dateProvider.today()).thenReturn(today());
    sut = new Account(dateProvider);
  }

  @Test
  void shouldDisplayEmptyStatement() {
    assertThat(sut.printStatement()).isEmpty();
  }

  @Test
  void shouldDisplayTwoStatementsForOneDepositAndOneWithdraw() {
    sut.deposit(500);
    sut.withdraw(100);

    assertThat(sut.printStatement().size()).isEqualTo(2);
    assertThat(sut.printStatement().get(0)).isEqualTo(Statement.builder().withDate(today()).withAmount(500).withBalance(500).build());
    assertThat(sut.printStatement().get(1)).isEqualTo(Statement.builder().withDate(today()).withAmount(-100).withBalance(400).build());
  }

  @Test
  void shouldDisplayTwoStatementsForTwoDepositsOnDifferentDates() {
    makeDepositOnFirstDate();
    makeDepositOnSecondDate();

    assertThat(sut.printStatement().get(0).date()).isEqualTo(today());
    assertThat(sut.printStatement().get(1).date()).isEqualTo(tomorrow());
  }

  private void makeDepositOnFirstDate() {
    when(dateProvider.today()).thenReturn(today());
    sut.deposit(500);
  }

  private void makeDepositOnSecondDate() {
    when(dateProvider.today()).thenReturn(tomorrow());
    sut.withdraw(100);
  }

  private static LocalDate today() {
    return LocalDate.of(2015, 12, 24);
  }

  private static LocalDate tomorrow() {
    return LocalDate.of(2015, 12, 25);
  }
}
