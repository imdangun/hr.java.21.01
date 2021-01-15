package kimyongtae.hr.domain;

import java.time.LocalDate;

public class Employee {
	private int empId;
	private String empName;
	private LocalDate hireDate;
	
	public Employee(int empId, String empName, LocalDate hireDate) {
		this.empId = empId;
		this.empName = empName;
		this.hireDate = hireDate;
	}

	public int getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	
	@Override
	public String toString() {
		return String.format("%4d %-6s %s", empId, empName, hireDate);
	}
}
