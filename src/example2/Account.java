package example2;

import example2.accountstates.Active;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    private AccountState state;

    public Account(AccountUnfrozen onUnfrozen) {
        balance = BigDecimal.ZERO;
        state = new Active(onUnfrozen);
    }

    public void closeAccount() {
        state = state.closeAccount();
    }

    public void holderVerified() {
        state = state.holderVerified();
    }

    public void freezeAccount() {
        state = state.freezeAccount();
    }

    public void deposit(BigDecimal amount) {
        state = state.deposit(amount, this::addToBalance);
    }

    public void withdraw(BigDecimal amount) {
        state = state.withdraw(this.balance, amount, this::subtractFromBalance);
    }

    private void addToBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }

    private void subtractFromBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}
