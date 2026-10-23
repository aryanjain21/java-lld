import bankAccount.AccountService;
import bankAccount.BankAccount;

import java.math.BigDecimal;

public class Main {
    static void main() {
        BankAccount bankAccount = new BankAccount("Aryan");
        AccountService accountService = new AccountService(bankAccount);
        try {
            System.out.println("Account Id: " + bankAccount.getAccountId() + "Balance is: " + bankAccount.getBalance());

            accountService.deposit(new BigDecimal(400));
            System.out.println("After Deposit current Balance is: " + bankAccount.getBalance());

            accountService.withdraw(new BigDecimal(200));
            System.out.println("After Withdraw current Balance is: " + bankAccount.getBalance());

            accountService.withdraw(new BigDecimal(400));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
