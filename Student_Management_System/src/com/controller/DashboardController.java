package com.controller;

import java.util.Scanner;

import com.model.DashboardModel;
import com.repository.DashboardRepository;

public class DashboardController {

	public static void main(String[] args) {
		DashboardModel dashboardmodel=new DashboardModel();
		DashboardRepository dashboardrepository= new DashboardRepository(); 
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("----------------------------");
			System.out.println("Student Management System");
			System.out.println("----------------------------");
			System.out.println("1. View Student Details");
			System.out.println("2. Add Student Details");
			System.out.println("3. Delete Student Details");
			System.out.println("4. Edit Student Details");
			
			System.out.println("Enter your option");
			int input=sc.nextInt();
			System.out.println("Enter Student Id : ");
			dashboardmodel.setsId(sc.nextInt());
			
			if(input>0 && input<5) {
				boolean result;
				switch(input) {
				case 1:
					result=dashboardrepository.viewDetails(dashboardmodel);
					if(result) {
						System.out.println("Student ID "+dashboardmodel.getsId());
						System.out.println("Student Name "+ dashboardmodel.getName());
						System.out.println("Student Department "+ dashboardmodel.getMajor());
					}
					else
						System.out.println("Details not available...");
					break;
					
				case 2:
					result=dashboardrepository.viewDetails(dashboardmodel);
					if(result) {
						System.out.println("Student ID already exists......\nYou can view the details........");
					}
					else {
						System.out.println("Enter Student Name ");
						dashboardmodel.setName(sc.next());
						System.out.println("Enter Department ");
						dashboardmodel.setMajor(sc.next());
						
						result=dashboardrepository.addDetails(dashboardmodel);
						if(result) {
							System.out.println("Details added.....");
						}
						else 
							System.out.println("Details not added......");
					}
					break;
					
				case 3:					
					result=dashboardrepository.deleteDetails(dashboardmodel);
					if(result)
						System.out.println("Deleted.....");
					else
						System.out.println("Not Deleted....");
					break;
					
				case 4:
					System.out.println("Enter Student Name ");
					dashboardmodel.setName(sc.next());
					System.out.println("Enter Student Department ");
					dashboardmodel.setMajor(sc.next());
					
					result=dashboardrepository.editDetails(dashboardmodel);
					if(result) {
						System.out.println("Details updated.........");
					}
					else
						System.out.println("Details Not Updated.....");
					break;
					
				default:
						System.out.println("Invalid Choice............");
				}
			}
		}
	}

}
