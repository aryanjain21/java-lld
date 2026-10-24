package service;

import employee.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PayrollService {
    private final List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee cannot be null");

        boolean isExist = employeeList.stream().anyMatch(emp -> emp.getEmpId().equals(employee.getEmpId()));
        if(isExist) {
            throw new IllegalArgumentException("Employee already exists");
        }

        employeeList.add(employee);
    }

    public BigDecimal calculateTotalPayroll() {
        BigDecimal total = BigDecimal.ZERO;

        for (Employee emp: employeeList) {
            total = total.add(emp.calculatePay());
        }

        return total;
    }
}
