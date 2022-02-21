import java.io.*;
import java.util.*;

class Employee {
	int Employee_Id;
	String Employee_Name;
	int Salary;
	public Employee(int Employee_Id, String Employee_Name, int Salary) {
		this.Employee_Id = Employee_Id;
		this.Employee_Name = Employee_Name;
		this.Salary = Salary;
	}
}

public class Program1 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, Employee> map = new HashMap<Integer, Employee>();
		for(int i=0;i<3;i++) {
			System.out.print("Enter employee id, name and salary:");
			String[] input = sc.nextLine().split("[\\s\\t]");
			Employee emp = new Employee(Integer.valueOf(input[0]), input[1], Integer.valueOf(input[2]));
			map.put(i+1, emp);
		}
		for(Map.Entry<Integer, Employee> m: map.entrySet()) {
			Employee emp = m.getValue();
			int sal = emp.Salary;
			double it;
			if(sal >= 0 && sal <= 50000) {
				it = 0;
			}
			else if(sal > 50000 && sal <= 60000) {
				it = (int) (sal * 0.10);
			}
			else if(sal > 60000 && sal <= 150000) {
				it = (int) (sal * 0.20);
			}
			else {
				it = (int) (sal * 0.30);
			}
			System.out.print("Employee Id: "+emp.Employee_Id);
			System.out.print(", Employee Id: "+emp.Employee_Name);
			System.out.print(", Salary: "+emp.Salary);
			System.out.println(", IT Amount: "+it);
		}
	}
}