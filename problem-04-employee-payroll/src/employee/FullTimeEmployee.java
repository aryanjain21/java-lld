package employee;

import java.math.BigDecimal;
import java.util.Objects;

public class FullTimeEmployee extends Employee{

    private final BigDecimal monthlySalary;

    public FullTimeEmployee(String empId, String name, BigDecimal monthlySalary) {
        super(empId, name);
        this.monthlySalary = validateMonthlySalary(monthlySalary);
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public BigDecimal calculatePay() {
        return monthlySalary;
    }

    private BigDecimal validateMonthlySalary(BigDecimal salary) {
        Objects.requireNonNull(salary, "Monthly salary cannot be null.");

        if (salary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monthly salary should be positive.");
        }

        return salary;
    }
}
