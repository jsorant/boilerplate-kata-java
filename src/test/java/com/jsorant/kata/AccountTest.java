package com.jsorant.kata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.jsorant.kata.shared.error.domain.MissingMandatoryValueException;
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

  @BeforeEach
  void initDateProvider() {
    lenient().when(dateProvider.today()).thenReturn(today());
  }

  // TODO : Balance should not be negative
  // TODO : Amount should not be negative

  @Test
  void shouldHaveEmptyStatementForAnEmptyAccount() {
    Account account = Account.EmptyWithDateProvider(dateProvider);

    Statement statement = account.getStatement();

    assertThat(statement.linesCount()).isEqualTo(0);
  }

  @Test
  void shouldHaveTwoLinesForTwoDeposits() {
    Account account = Account.EmptyWithDateProvider(dateProvider);

    final Statement statement = account.deposit(500).deposit(100).getStatement();

    assertThat(statement.linesCount()).isEqualTo(2);
  }

  @Test
  void shouldHaveLinesWithCorrectAmounts() {
    Account account = Account.EmptyWithDateProvider(dateProvider);

    final Statement statement = account.deposit(500).withdraw(100).withdraw(200).getStatement();

    assertThat(statement.line(0).amount()).isEqualTo(500);
    assertThat(statement.line(1).amount()).isEqualTo(-100);
    assertThat(statement.line(2).amount()).isEqualTo(-200);
  }

  @Test
  void shouldHaveLinesWithCorrectBalances() {
    Account account = Account.EmptyWithDateProvider(dateProvider);

    final Statement statement = account.deposit(500).withdraw(100).withdraw(200).getStatement();

    assertThat(statement.line(0).balance()).isEqualTo(Balance.of(500));
    assertThat(statement.line(1).balance()).isEqualTo(Balance.of(400));
    assertThat(statement.line(2).balance()).isEqualTo(Balance.of(200));
  }

  @Test
  void shouldHaveLineWithDate() {
    Account account = Account.EmptyWithDateProvider(dateProvider);

    final Statement statement = account.deposit(500).getStatement();

    assertThat(statement.line(0).date()).isEqualTo(LocalDate.of(2023, 1, 23));
  }

  @Test
  void shouldNotBuildWithoutADateProvider() {
    assertThatThrownBy(() -> Account.builder().build())
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("dateProvider");
  }

  @Test
  void shouldHaveLinesWithDifferentDates() {
    Account account = Account.EmptyWithDateProvider(dateProvider);

    final Account endOfTodayAccount = makeADepositToday(account);
    final Account endOfTomorrowAccount = makeAWithdrawTomorrow(endOfTodayAccount);

    final Statement statement = endOfTomorrowAccount.getStatement();

    assertThat(statement.line(0).date()).isEqualTo(today());
    assertThat(statement.line(1).date()).isEqualTo(tomorrow());
  }

  private Account makeADepositToday(Account account) {
    when(dateProvider.today()).thenReturn(today());
    return account.deposit(500);
  }

  private Account makeAWithdrawTomorrow(Account endOfTodayAccount) {
    when(dateProvider.today()).thenReturn(tomorrow());
    return endOfTodayAccount.withdraw(100);
  }

  private static LocalDate today() {
    return LocalDate.of(2023, 1, 23);
  }

  private static LocalDate tomorrow() {
    return LocalDate.of(2023, 1, 24);
  }
}
