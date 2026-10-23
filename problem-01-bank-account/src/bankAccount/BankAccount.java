package bankAccount;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class BankAccount {
    private final UUID accountId;
    private String ownerName;
    private BigDecimal balance;
    private final Currency currency;

    public BankAccount(String ownerName) {
        this.accountId = UUID.randomUUID();
        this.ownerName = validateOwnerName(ownerName);
        this.balance = BigDecimal.ZERO;
        this.currency = Currency.INR;
    }

    private String validateOwnerName(String ownerName) {
        Objects.requireNonNull(ownerName, "Owner name cannot be null");

        String trimmedName = ownerName.trim();

        if (trimmedName.isBlank()) {
            throw new IllegalArgumentException(
                    "Owner name cannot be blank"
            );
        }

        return trimmedName;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = validateOwnerName(ownerName);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        Objects.requireNonNull(amount, "Deposit amount cannot be null");
        validatePositiveAmount(amount);

        balance = balance.add(amount);
        System.out.println("Deposit: " + balance);
    }

    public void withdraw(BigDecimal amount) {
        Objects.requireNonNull(amount, "Withdrawal amount cannot be null");
        validatePositiveAmount(amount);
        if(this.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance!!");
        }

        balance = balance.subtract(amount);
        System.out.println("Withdraw: " + balance);
    }

    private void validatePositiveAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }
    }
}
