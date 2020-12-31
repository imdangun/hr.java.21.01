package kimyongtae.hr.service;

import java.time.LocalDate;
import java.util.List;

import kimyongtae.hr.domain.Employee;

public interface EmployeeService {
	List<Employee> getEmployees();
	Employee getEmployee(int empNum);
	boolean addEmployee(String empName, LocalDate hireDate);
	boolean fixEmployee(Employee emp);
	boolean delEmployee(int empNum);
}
