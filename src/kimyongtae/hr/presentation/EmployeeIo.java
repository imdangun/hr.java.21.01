package kimyongtae.hr.presentation;

import java.time.LocalDate;
import java.util.List;

import kimyongtae.hr.domain.Employee;
import kimyongtae.hr.service.EmployeeService;

public class EmployeeIo {
	private EmployeeService empService;	
	private int menuLength;
	private String menu;
	
	public EmployeeIo(EmployeeService empService, String[] menuItem) {
		this.empService = empService;
		this.menuLength = menuItem.length;
		this.menu = Console.menu(menuItem);
	}
	
	public void play() {
		Job job = null;
		while((job = Console.choose(menu, menuLength)) != Job.EXIT) {
			switch(job) {
			case LIST: this.listEmployees(); break;
			case ADD:  this.addEmployee(); break;
			case FIX:  this.fixEmployee(); break;
			case DEL:  this.delEmployee(); 
			}
		}
	}
	
	private void listEmployees() {
		List<Employee> empList = empService.getEmployees();
		
		System.out.println("사번 이름   입사일");
		System.out.println("----------------------");
		
		if(empList.size() != 0)
			for(Employee emp: empList) Console.info(emp);
		else Console.info("사원이 없습니다.");
	}
	
	private void addEmployee() {
		String empName = Console.inStr("사원의 이름을 입력하세요.");
		
		if(!empName.equals("0")) {
			LocalDate hireDate = Console.inDate("입사일을 입력하세요.");
			
			if(empService.addEmployee(empName, hireDate))
				Console.success("사원 추가");
			else Console.fail("사원 추가");
		} else Console.info("사원 추가를 취소합니다.");
	}
	
	private void fixEmployee() {
		if(empService.getEmployees().size() != 0) {
			Employee emp = null;
			
			do {
				int empNum = Console.inNum("수정할 사원번호를 입력하세요.");
				if(empNum == 0) {
					Console.info("사원 수정을 취소합니다.");
					return;
				}
				
				emp = empService.getEmployee(empNum);
				if(emp == null) Console.err("입력한 사원번호의 사원이 없습니다.");
			} while(emp == null);
			
			emp.setEmpName(Console.inStr("사원 이름을 입력하세요."));
			emp.setHireDate(Console.inDate("입사일을 입력하세요."));
			
			if(empService.fixEmployee(emp)) Console.success("사원 수정");
			else Console.fail("사원 수정");
		} else Console.info("사원이 없습니다");
	}
	
	private void delEmployee() {
		if(empService.getEmployees().size() != 0) {
			Employee emp = null;
			int empNum = 0;
			
			do {
				empNum = Console.inNum("삭제할 사원번호를 입력하세요.");
				if(empNum == 0) {
					Console.info("사원 삭제를 취소합니다.");
					return;
				}
				
				emp = empService.getEmployee(empNum);
				if(emp == null) Console.err("입력한 사원번호의 사원이 없습니다.");
			} while(emp == null);
			
			if(empService.delEmployee(empNum)) Console.success("사원 삭제");
			else Console.fail("사원 삭제");
		} else Console.info("사원이 없습니다.");
	}
}
