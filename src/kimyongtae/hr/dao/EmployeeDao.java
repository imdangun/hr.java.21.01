package kimyongtae.hr.dao;

import java.time.LocalDate;
import java.util.List;

import kimyongtae.hr.domain.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployees();
	Employee selectEmployee(int empId);
	boolean insertEmployee(String empName, LocalDate hireDate);
	boolean updateEmployee(Employee emp);
	boolean deleteEmployee(int empId);
}
