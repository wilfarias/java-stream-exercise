package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		String path = "C:\\projetos\\ws-eclipse\\curso_java_exercicio_stream\\in.txt";
		Scanner scan = new Scanner(System.in);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			List<Employee> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.valueOf(fields[2])));				
				line = br.readLine();
			}
			
			System.out.println("Forneça um valor de salário base: ");
			double valorBase = scan.nextDouble();
			
			//Comparator<String> comp = (name1, name2) -> name1.toUpperCase().compareTo(name2.toUpperCase());
			List<String> emailList = list.stream().
											filter(s -> s.getSalary() > valorBase).
											map(s -> s.getEmail()).
											sorted().
											collect(Collectors.toList());
			
			double sumSalary = list.stream().
								filter(e -> e.getName().startsWith("M")).
								map(p -> p.getSalary()).
								reduce(0.0, (x,y) -> x + y);
	
			System.out.println("Email of people whose salary is more than "+ valorBase +":");
			emailList.forEach(System.out:: println);
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sumSalary));
			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
