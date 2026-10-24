package employee;

import java.math.BigDecimal;
import java.util.Objects;

public class ContractEmployee extends Employee{

    private final BigDecimal contractPayment;

    public ContractEmployee(String empId, String name, BigDecimal contractPayment) {
        super(empId, name);
        this.contractPayment = validateContractPayment(contractPayment);
    }

    public BigDecimal getContractPayment() {
        return contractPayment;
    }

    @Override
    public BigDecimal calculatePay() {
        return contractPayment;
    }

    private BigDecimal validateContractPayment(BigDecimal payment) {
        Objects.requireNonNull(payment, "Contract payment cannot be null.");

        if (payment.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Contract payment should be positive");
        }

        return  payment;
    }
}
