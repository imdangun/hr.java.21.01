package kimyongtae.hr;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import kimyongtae.hr.dao.EmployeeDao;
import kimyongtae.hr.dao.EmployeeDaoImpl;
import kimyongtae.hr.domain.Employee;
import kimyongtae.hr.presentation.EmployeeIo;
import kimyongtae.hr.service.EmployeeService;
import kimyongtae.hr.service.EmployeeServiceImpl;

public class Main {
	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getGlobal();
		logger.addHandler(new FileHandler("c:/DEV/kimyongtae.hr.xml"));		
		logger.info("HR 앱을 시작합니다.");
		
		String[] menuItem = {"목록", "추가", "수정", "삭제"};
		
		List<Employee> empList = new ArrayList<Employee>();		
		
		EmployeeDao empDao = new EmployeeDaoImpl(empList);
		EmployeeService empService = new EmployeeServiceImpl(empDao);
		EmployeeIo empIo = new EmployeeIo(empService, menuItem);
		
		empIo.play();
		
		logger.info("HR 앱을 종료합니다.");
	}
}
