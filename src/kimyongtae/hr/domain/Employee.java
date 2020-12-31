package kimyongtae.hr.domain;

import java.time.LocalDate;

public class Employee {
	private int empNum;
	private String empName;
	private LocalDate hireDate;
	
	public Employee(int empNum, String empName, LocalDate hireDate) {
		this.empNum = empNum;
		this.empName = empName;
		this.hireDate = hireDate;
	}

	public int getEmpNum() {
		return empNum;
	}

	public String getEmpName() {
		return empName;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	
	@Override
	public String toString() {
		return String.format("%4d %-6s %s", empNum, empName, hireDate);
	}
}
