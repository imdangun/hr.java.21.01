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
		while((job = choose(menu, menuLength)) != Job.EXIT) {
			switch(job) {
			case LIST: this.listEmployees(); break;
			case ADD:  this.addEmployee(); break;
			case FIX:  this.fixEmployee(); break;
			case DEL:  this.delEmployee(); 
			}
		}
	}
	
	private Job choose(String menu, int length) {
		boolean isGood = false;
		int choice = 0;
		
		do {
			System.out.println();
			choice = Console.inNum(menu);
			if(choice < 0 || choice > length)
				Console.err("존재하는 메뉴 번호를 입력하세요.");
			else isGood = true;
		} while(!isGood);
		
		return toJob(choice);
	}
	
	private Job toJob(int num) {
		Job job = null;
		
		switch(num) {
		case 0: job = Job.EXIT; break;
		case 1: job = Job.LIST; break;
		case 2: job = Job.ADD; break;
		case 3: job = Job.FIX; break;
		case 4: job = Job.DEL;
		}
		
		return job;
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
				int empId = Console.inNum("수정할 사원번호를 입력하세요.");
				if(empId == 0) {
					Console.info("사원 수정을 취소합니다.");
					return;
				}
				
				emp = empService.getEmployee(empId);
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
			int empId = 0;
			
			do {
				empId = Console.inNum("삭제할 사원번호를 입력하세요.");
				if(empId == 0) {
					Console.info("사원 삭제를 취소합니다.");
					return;
				}
				
				emp = empService.getEmployee(empId);
				if(emp == null) Console.err("입력한 사원번호의 사원이 없습니다.");
			} while(emp == null);
			
			if(empService.delEmployee(empId)) Console.success("사원 삭제");
			else Console.fail("사원 삭제");
		} else Console.info("사원이 없습니다.");
	}
}
