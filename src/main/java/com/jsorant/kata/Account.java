package com.jsorant.kata;

import com.jsorant.kata.shared.error.domain.Assert;

public class Account {

  private final Statement statement;
  private final Balance balance;
  private final DateProvider dateProvider;

  private Account(DateProvider dateProvider, Balance balance, Statement statement) {
    Assert.notNull("dateProvider", dateProvider);

    this.dateProvider = dateProvider;
    this.statement = statement;
    this.balance = balance;
  }

  public static AccountBuilder builder() {
    return new AccountBuilder();
  }

  public static Account EmptyWithDateProvider(DateProvider dateProvider) {
    return Account.builder().withDateProvider(dateProvider).build();
  }

  public Statement getStatement() {
    return statement;
  }

  public Account deposit(int amount) {
    final Balance newBalance = balance.add(amount);
    final Statement newStatement = addLineToStatementFromDeposit(amount, newBalance);

    return Account.builder().withDateProvider(dateProvider).withBalance(newBalance).withStatement(newStatement).build();
  }

  public Account withdraw(int amount) {
    final Balance newBalance = balance.remove(amount);
    final Statement newStatement = addLineToStatementFromWithdraw(amount, newBalance);

    return Account.builder().withDateProvider(dateProvider).withBalance(newBalance).withStatement(newStatement).build();
  }

  private Statement addLineToStatementFromDeposit(int amount, Balance balance) {
    final Line newLine = makeLineFromDeposit(amount, balance);
    return statement.addLine(newLine);
  }

  private Statement addLineToStatementFromWithdraw(int amount, Balance balance) {
    final Line newLine = makeLineFromWithdraw(amount, balance);
    return statement.addLine(newLine);
  }

  private Line makeLineFromDeposit(int amount, Balance balance) {
    return Line.builder().withDeposit(amount).withBalance(balance).withDate(dateProvider.today()).build();
  }

  private Line makeLineFromWithdraw(int amount, Balance balance) {
    return Line.builder().withWithdraw(amount).withBalance(balance).withDate(dateProvider.today()).build();
  }

  public static final class AccountBuilder {

    private Balance balance = Balance.EMPTY();
    private Statement statement = Statement.EMPTY();
    private DateProvider dateProvider = null;

    public AccountBuilder withBalance(Balance balance) {
      this.balance = balance;
      return this;
    }

    public AccountBuilder withStatement(Statement statement) {
      this.statement = statement;
      return this;
    }

    public AccountBuilder withDateProvider(DateProvider dateProvider) {
      this.dateProvider = dateProvider;
      return this;
    }

    public Account build() {
      return new Account(dateProvider, balance, statement);
    }
  }
}
