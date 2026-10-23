package bankAccount;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountService {
    private BankAccount bankAccount;

    public AccountService(BankAccount bankAccount) {
//        this.bankAccount = bankAccount;
        this.bankAccount = Objects.requireNonNull(
                bankAccount,
                "Bank account cannot be null"
        );
    }

    public void deposit(BigDecimal amount) {
        this.bankAccount.deposit(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.bankAccount.withdraw(amount);
    }
}
