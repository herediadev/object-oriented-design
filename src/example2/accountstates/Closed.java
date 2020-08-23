package example2.accountstates;

import example2.AccountState;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class Closed implements AccountState {

    @Override
    public AccountState deposit(BigDecimal amount, Consumer<BigDecimal> addToBalance) {
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
        return this;
    }

    @Override
    public AccountState closeAccount() {
        return this;
    }
}
