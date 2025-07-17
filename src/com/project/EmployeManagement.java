package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeManagement {
	static Connection con;
	 static Scanner sc;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
	     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db", "root","hasmashaik@2310");
	     
			sc=new Scanner(System.in);
			boolean isExist=false;
			while(!isExist) {
				System.out.println("1.Add Employee"); 
				System.out.println("2.Delete a Student ");	
				System.out.println("3.Display All Employee ");
				System.out.println("4.Update Student ");
				System.out.println("5.view a perticularEmploye ");
				System.out.println("6.Exist");
				System.out.print("Choose One Option :");
				int op=sc.nextInt();
				if(op==1) {
					addEmployee();
				}else if(op ==2) {
					deleteEmployee();
				}else if(op == 3) {
					displyAllEmployee();
				}else if(op == 4) {
					updateEmployee();
				}else if(op == 5) {
					System.out.print("Enter Employee ID :");
					int emp_id=sc.nextInt();
					ResultSet rs=searchEmployee(emp_id);
					if(rs !=null) {
						System.out.println("Employee Id = "+rs.getInt(1));
						System.out.println("Employee Name = "+rs.getString(2));
						System.out.println("Employee Designation = "+rs.getString(3));
						System.out.println("Employee Department = "+rs.getString(4));
						System.out.println("Empoyee Salary = "+rs.getDouble(5));
						System.out.println("");
					}else {
						System.out.println("No Employee Avilable");
					}
				}else if(op == 6) {
					System.out.println("............THANK YoU FOR VISITING.......TaaTaaa...........");
					break;
				}else {
					System.out.println("Invalid option.......");
				}
			}
	}
			static void addEmployee() throws SQLException {
				PreparedStatement ps=con.prepareStatement("insert into employee((emp_id,emp_name,designation,department,salary) values(?,?,?,?,?) ");
				System.out.println("Enter Employee ID :");
				int id=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Employee Name :");
				String name=sc.nextLine();
				System.out.println("Enter Employee Designation :");
				String des=sc.nextLine();
				System.out.println("Enter Department Name:");
				String dep=sc.nextLine();
				System.out.println("Enter Employee Salary :");
				double salary=sc.nextDouble();
				ps.executeUpdate("insert into employee(emp_id,emp_name,designation,department,salary) values("+id+",'"+name+"','"+des+"','"+dep+"',"+salary+")");
				System.out.println("Employee Inserted Successfully .........");
			}
			static void deleteEmployee() throws SQLException {
			    System.out.print("Enter Employee ID to delete: ");
			    int id = sc.nextInt();

			    ResultSet rs = searchEmployee(id);
			    if (rs == null) {
			        System.out.println("Employee Not Found.");
			        return;
			    }

			    System.out.print("Are you sure you want to delete this employee? (Y/N): ");
			    String conform = sc.next();

			    if (conform.equalsIgnoreCase("Y")) {
			        String query = "delete from employee where emp_id = ?";
			        try (PreparedStatement ps = con.prepareStatement(query)) {
			            ps.setInt(1, id);
			            int rows = ps.executeUpdate();
			            if (rows > 0) {
			                System.out.println("Employee deleted successfully.");
			            } else {
			                System.out.println("Deletion failed.");
			            }
			        }
			    } else {
			        System.out.println("Deletion cancelled.");
			    }
			}

			static void updateEmployee() throws SQLException {
		        System.out.print("Enter Employee ID to Update: ");
		        int id = sc.nextInt();
		        ResultSet rs = searchEmployee(id);
		        if (rs == null) {
		            System.out.println("Student Not Found");
		        } else {
		            sc.nextLine(); 
		            System.out.print("Enter New Employee Name: ");
		            String name = sc.nextLine();
		            System.out.print("Enter New Employee Designation: ");
		            String des = sc.nextLine();
		            System.out.println("Enter Employee Department: ");
		            String dep=sc.nextLine();
		            System.out.println("Enter employee Salary: ");
		            Double salary=sc.nextDouble();

		            String query = "update employee set emp_name = ?, designation = ?,department = ?,salary = ? where emp_id = ?";
		            PreparedStatement ps = con.prepareStatement(query);
		            ps.setString(1, name);
		            ps.setString(2, des);
		            ps.setString(3, dep);
		            ps.setDouble(4, salary);
		            ps.setInt(5, id);
		            ps.executeUpdate();
		            System.out.println("Empployee Updated Successfully........");
		        }
		    }
			static void displyAllEmployee() throws SQLException {
				String query = "select * from employee";
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				int count =0;
				while(rs.next()) {
					count ++;
					System.out.println("Employee Id = "+rs.getInt(1));
					System.out.println("Employee Name = "+rs.getString(2));
					System.out.println("Employee Designation = "+rs.getString(3));
					System.out.println("Employee Department = "+rs.getString(4));
					System.out.println("Empoyee Salary = "+rs.getDouble(5));
					System.out.println("*********************************************************");
				}
				if(count ==0) {
					System.out.println("...........................Employee Not Found..........");
				}
			}
			
			static ResultSet searchEmployee(int emp_id) throws SQLException {
				String query = "select * from employee where emp_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, emp_id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return rs;
				}else {
					return null;
				}
			}
			

}
