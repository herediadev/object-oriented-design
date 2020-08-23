package example2.accountstates;

import example2.AccountState;
import example2.AccountUnfrozen;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class NotVerified implements AccountState {

    private final AccountUnfrozen onUnfrozen;

    public NotVerified(AccountUnfrozen onUnfrozen) {
        this.onUnfrozen = onUnfrozen;
    }

    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
        addToBalance.accept(amount);
        return this;
    }

    @Override
    public AccountState withdraw(BigDecimal balance, BigDecimal amount, Consumer<BigDecimal> subtractFromBalance) {
        return this;
    }

    @Override
    public AccountState freezeAccount() {
        return this;
    }

    @Override
    public AccountState holderVerified() {
        return new Active(onUnfrozen);
    }

    @Override
    public AccountState closeAccount() {
        return new Closed();
    }
}
