import java.util.ArrayList;
import java.util.List;

public class Account {

  private final List<Statement> statements = new ArrayList<>();
  private Balance balance = Balance.ZERO();
  private final DateProvider dateProvider;

  public Account(DateProvider dateProvider) {
    this.dateProvider = dateProvider;
  }

  public List<Statement> printStatement() {
    return statements;
  }

  public void deposit(int amount) {
    balance = balance.add(Amount.ofValue(amount));
    addStatement(amount);
  }

  public void withdraw(int amount) {
    balance = balance.remove(Amount.ofValue(amount));
    addStatement(-amount);
  }

  private void addStatement(int amount) {
    statements.add(Statement.builder().withDate(dateProvider.today()).withAmount(amount).withBalance(balance.value).build());
  }
}
