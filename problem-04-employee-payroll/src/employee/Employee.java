package employee;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Employee {
    private final String empId;
    private final String name;

    public Employee(String empId, String name) {
        this.empId = validateEmpId(empId);
        this.name = validateName(name);
    }

    public abstract BigDecimal calculatePay();

    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    private String validateEmpId(String empId) {
        Objects.requireNonNull(empId, "Employee id should not be null.");

        String trimmedEmpId = empId.trim();

        if(trimmedEmpId.isBlank()) {
            throw new IllegalArgumentException("Employee id cannot be empty.");
        }

        return trimmedEmpId;
    }

    private String validateName(String name) {
        Objects.requireNonNull(name, "Name should not be null.");

        String trimmedName = name.trim();

        if(trimmedName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        return  trimmedName;
    }
}
