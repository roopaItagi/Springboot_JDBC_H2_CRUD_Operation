package com.learning.SpringJdbcH2.jdbc;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
public class courseCommandLineRunner implements CommandLineRunner{

	@Autowired
	private SpringJdbcApp app;
	@Override
	public void run(String... args) throws Exception {
		
		int id ;
		String courseName;
		boolean again=true;
		CourseInfo course;
		do {


			System.out.println("=============================================");
			System.out.println("CRUD operations demo using Springboot-H2");
			System.out.println("=============================================");
			System.out.println("Select your choice from below options:");
			System.out.println("1. Insert new record");
			System.out.println("2. update existing record");
			System.out.println("3. delete a record");
			System.out.println("4. fetch by id");
			System.out.println("5. Show all records");
			System.out.println("6. exit");
			
			
			Scanner scan=new Scanner(System.in);
			int choice= scan.nextInt();
		
		switch(choice){
		case 1:
			System.out.println("Please provide following information to insert into database");
			System.out.println("Enter id:");
			id= scan.nextInt();
			System.out.println("Enter Coursename:");
			courseName= scan.next();
			
			try {
			app.insert(new CourseInfo(id,courseName));
			System.out.println("Data inserted successfully");
			break;
			}catch(DuplicateKeyException e) {
				System.out.println("Data insertion failed.duplicate key not allowed.\nOnly unique key allowed");
				break;
			}
		case 2:
			System.out.println("Enter the record id that needs to be updated : ");
			try {
			id= scan.nextInt();

			course =app.findById(id);
			if(course!=null) {
				System.out.println("Record exists with record id :"+ id );
				System.out.println("Enter the new course name : ");
				courseName =scan.next();
				app.updateById(new CourseInfo(id,courseName));
				}

			break;
			}catch(InputMismatchException misMatch){
				
			System.out.println("Enter valid input.");
			break;
			}
			catch(Exception e){
				
				System.out.println("Updation failed");
				break;
				}
		case 3:
			System.out.println("Enter the record id that needs to be deleted : ");
			id= scan.nextInt();
			app.deleteById(id);

			break;
		case 4:
			System.out.println("Enter the record id that needs to be fetched : ");
			id= scan.nextInt();
			try {
			course =app.findById(id);
			if(course!=null) {
			System.out.println("Here is the record ");
			System.out.println(course);
			break;
			}
			else {
				System.out.println("No Record found with the id: "+id);
				break;
			}
			}catch(Exception e){
				System.out.println("No Record found with the id: "+id);
				break;
			}

		case 5:	
				List<CourseInfo> list=app.fetchAll();
				if(list !=null) {
					System.out.println("Here is the list of courses");
				for (CourseInfo courseInfo : list) {
					System.out.println(courseInfo.getId()+" "+courseInfo.getCname());
					
				}
				}
				break;
		case 6:
			again=false;
			
			System.out.println("See you again ");
			break;
		default: 
			System.out.println("Invalid Entry");
		}
		
		}while(again);
		
		
	}

}
