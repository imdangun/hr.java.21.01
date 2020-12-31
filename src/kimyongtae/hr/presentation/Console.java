package kimyongtae.hr.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public interface Console {
	Scanner sc = new Scanner(System.in);
	
	static void in(String msg) {
		System.out.print(msg + "\n> ");
	}
	
	static void err(String msg) {
		System.out.println("ERROR] " + msg);
	}
	
	static void info(String msg) {
		System.out.println(msg);
	}
	
	static void info(Object obj) {
		System.out.println(obj);
	}
	
	static void success(String msg) {
		System.out.println("SUCCESS] " + msg + " 성공했습니다.");
	}
	
	static void fail(String msg) {
		System.out.println("FAIL] " + msg + " 실패했습니다.");
	}
	
	static String inStr(String msg) {
		boolean isGood = false;
		String line = "";
		
		do {
			in(msg);
			line = sc.nextLine();
			
			isGood = line.length() > 0; 
		} while(!isGood);
		
		return line;
	}
	
	static int inNum(String msg) {
		boolean isGood = false;
		String line = "";
		int num = 0;
		
		do {
			in(msg);
			line = sc.nextLine();
			                                 
			if(line.length() > 0 && line.matches("[0-9]*")) {
				num = Integer.parseInt(line);
				isGood = true;
			}
			
			if(!isGood) err("0 이상의 정수를 입력하세요.");			
		} while(!isGood);
		
		return num;
	}
	
	static LocalDate inDate(String msg) {
		String line = "";
		LocalDate date = null;
		
		do {
			in(msg);
			line = sc.nextLine();
			
			if(line.length() > 0) {
				try {                                         // yyyy-MM-dd
					date = LocalDate.parse(line, DateTimeFormatter.ISO_DATE);
				} catch(DateTimeParseException e) {}
			}
			
			if(date == null) err("실제 날짜를 yyyy-MM-dd 형식으로 입력하세요.");
		} while(date == null);
		
		return date;
	}
	
	static Job choose(String menu, int length) {
		boolean isGood = false;
		int choice = 0;
		
		do {
			System.out.println();
			choice = inNum(menu);
			if(choice < 0 || choice > length)
				Console.err("존재하는 메뉴 번호를 입력하세요.");
			else isGood = true;
		} while(!isGood);
		
		return toJob(choice);
	}
	
	static Job toJob(int num) {
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
	
	static String menu(String[] menuItem) {
		String menu = "";
		
		for(int i = 0; i < menuItem.length; i++)
			menu += (i + 1) + "." + menuItem[i] + ", ";
		menu += "0.종료";
		
		return menu;
	}
}
