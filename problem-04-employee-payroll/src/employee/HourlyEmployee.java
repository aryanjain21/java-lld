package employee;

import java.math.BigDecimal;

public class HourlyEmployee extends Employee{

    private final BigDecimal hourlyRate;
    private final BigDecimal hoursWorked;

    public HourlyEmployee(String empId, String name, BigDecimal hourlyRate, BigDecimal hoursWorked) {
        super(empId, name);

        if (hourlyRate == null || hourlyRate.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Hourly rate cannot be negative or null");
        }

        if (hoursWorked == null || hoursWorked.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Hours worked cannot be negative or null");
        }

        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public BigDecimal calculatePay() {
        return this.hourlyRate.multiply(hoursWorked);
    }
}
