package com.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.controller.Student_Controller;
import com.model.Student;

public class Student_View {

	private static Scanner sc = new Scanner(System.in);

	public static Student getStudentObject() {

		Student s = new Student();

		System.out.println("Enter the Id: ");
		int id = sc.nextInt();

		System.out.println("Enter the name: ");
		sc.nextLine();
		String name = sc.nextLine();

		System.out.println("Enter the mobile number: ");
		long mob = sc.nextLong();

		System.out.println("Enter Gender: ");
		sc.nextLine();
		String gender = sc.nextLine();

		System.out.println("Enter Email: ");
		String email = sc.nextLine();

		System.out.println("Enter DOB: ");
		String dob = sc.nextLine();

		System.out.println("Enter password: ");
		String pswd = sc.nextLine();

		s.setId(id);
		s.setName(name);
		s.setMob(mob);
		s.setGender(gender);
		s.setEmail(email);
		s.setDob(dob);
		s.setPassword(pswd);

		return s;

	}

	public static void main(String[] args) {

		Connection con = null;

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Student_Data", "postgres", "root");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Student_Controller controller = new Student_Controller();

		System.out.println("...Welcome to Student Portal...");

		while (true) {

			System.out.println("\n---Choose What to Perform---\n");
			System.out.println("1. Save Student.");
			System.out.println("2. Find Student By Id.");
			System.out.println("3. Update Student By Id.");
			System.out.println("4. Delete Student By Id.");
			System.out.println("5. Find All.");
			System.out.println("6. Exit.");

			System.out.println("\n Enter Your choice... ");
			int choice = sc.nextInt();

			switch (choice) {

			case 1: {
				System.out.println("Enter the No. of Records to Enter: ");
				int no = sc.nextInt();

				int count = 1;
				while (count <= no) {
					System.out.println("Enter Data for student " + count);
					Student student = getStudentObject();
					
					int s = controller.saveStudent(student, con);

					if (s != 0) {
						System.out.println("Data saved Successfully!\n");
					} else {
						System.out.println("Data not saved!...");
					}
					count++;
				}

				break;
			}

			case 2: {
				System.out.println("Enter Student Id: ");
				int id = sc.nextInt();
				ResultSet rs = controller.findStudentById(id, con);

				try {
					if (rs.next() != false) {
						System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | "
								+ rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getLong(6) + " | "
								+ rs.getString(7));

					} else {
						System.out.println("Record Not Found....");
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;

			}

			case 3: {
				System.out.println("Enter Student ID: ");
				int id2 = sc.nextInt();

				System.out.println("Enter new Email: ");
				sc.nextLine();
				String email = sc.nextLine();

				int n = controller.updateStudentByID(id2, email, con);

				if (n != 0) {
					System.out.println("\nStudent details updated!");
				} else {
					System.out.println("Record not found!\n");
				}
				break;

			}
			
			case 4: {
				System.out.println("Enter Student ID");
				int id3 = sc.nextInt();

				int b = controller.deleteStudentByID(id3, con);
				if (b != 0) {
					System.out.println("Record deleted!..\n");
				} else {
					System.out.println("Record not Found!..\n");
				}
				break;

			}
			
			case 5: {
				ResultSet rs = controller.findAll(con);

				try {

					while (rs.next()) {
						System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | "
								+ rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getLong(6) + " | "
								+ rs.getString(7));
					}

				} catch (SQLException e) {

					e.printStackTrace();
				}

				break;

			}
			
			case 6: {
				System.out.println("Thank You!..");
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
				return;

			}
			default:
				System.out.println("Invalid choice..\n");
				break;
			}
		}

	}
}
