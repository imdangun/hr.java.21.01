package kimyongtae.hr.dao;

import java.time.LocalDate;
import java.util.List;

import kimyongtae.hr.domain.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	private List<Employee> empList;
	private int empNumSeq;
	
	public EmployeeDaoImpl(List<Employee> empList) {
		this.empList = empList;
		this.empNumSeq = 1;
	}
	
	@Override
	public List<Employee> selectEmployees() {
		return empList;
	}
	
	@Override
	public Employee selectEmployee(int empNum) {
		Employee emp = null;
		
		for(Employee temp: empList) {
			if(temp.getEmpNum() == empNum) {
				emp = temp;
				break;
			}
		}
		
		return emp;
	}
	
	@Override
	public boolean insertEmployee(String empName, LocalDate hireDate) {		
		return empList.add(new Employee(empNumSeq++, empName, hireDate));
	}
	
	@Override
	public boolean updateEmployee(Employee emp) {
		boolean isUpdated = false;
		Employee employee = null;		
		
		for(Employee temp: empList) {
			if(temp.getEmpNum() == emp.getEmpNum()) {
				employee = temp;					
				employee.setEmpName(emp.getEmpName());
				employee.setHireDate(emp.getHireDate());
				
				isUpdated = true;
				break;
			}
		}
		
		return isUpdated;
	}
	
	@Override
	public boolean deleteEmployee(int empNum) {
		boolean isDeleted = false;
		Employee emp = null;
		
		for(int i = 0; i < empList.size(); i++) {
			emp = empList.get(i);
			if(emp.getEmpNum() == empNum) {
				isDeleted = empList.remove(emp);
				break;
			}
		}
		
		return isDeleted;
	}
}
