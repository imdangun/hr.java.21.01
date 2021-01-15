package kimyongtae.hr.dao;

import java.time.LocalDate;
import java.util.List;

import kimyongtae.hr.domain.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	private List<Employee> empList;
	private int empIdSeq;
	
	public EmployeeDaoImpl(List<Employee> empList) {
		this.empList = empList;
		this.empIdSeq = 1;
	}
	
	@Override
	public List<Employee> selectEmployees() {
		return empList;
	}
	
	@Override
	public Employee selectEmployee(int empId) {
		Employee emp = null;
		
		for(Employee tEmp: empList) {
			if(tEmp.getEmpId() == empId) {
				emp = tEmp;
				break;
			}
		}
		
		return emp;
	}
	
	@Override
	public boolean insertEmployee(String empName, LocalDate hireDate) {		
		return empList.add(new Employee(empIdSeq++, empName, hireDate));
	}
	
	@Override
	public boolean updateEmployee(Employee emp) {
		boolean isUpdated = false;
		Employee employee = null;		
		
		for(Employee tEmp: empList) {
			if(tEmp.getEmpId() == emp.getEmpId()) {
				employee = tEmp;					
				employee.setEmpName(emp.getEmpName());
				employee.setHireDate(emp.getHireDate());
				
				isUpdated = true;
				break;
			}
		}
		
		return isUpdated;
	}
	
	@Override
	public boolean deleteEmployee(int empId) {
		boolean isDeleted = false;
		Employee emp = null;
		
		for(int i = 0; i < empList.size(); i++) {
			emp = empList.get(i);
			if(emp.getEmpId() == empId) {
				isDeleted = empList.remove(emp);
				break;
			}
		}
		
		return isDeleted;
	}
}
