Employee Management System — JDBC Console Application
This is a Java console-based application to perform CRUD operations (Create, Read, Update, Delete) on an employee table in a MySQL database using JDBC.

🛠 Features
✅ Add New Employee

❌ Delete Existing Employee

📃 Display All Employees

🔁 Update Employee Details

🔍 View Single Employee by ID

🚪 Exit Application

🗃 Database Setup
📌 Table: employee
sql
Copy
Edit
CREATE DATABASE jdbc_db;

USE jdbc_db;

CREATE TABLE employee (
  emp_id INT PRIMARY KEY,
  emp_name VARCHAR(100),
  designation VARCHAR(100),
  department VARCHAR(100),
  salary DOUBLE
);
🧾 Prerequisites
Java 17+

MySQL Server

MySQL JDBC Driver (already included in com.mysql.cj.jdbc.Driver)

IDE or command-line setup

💻 How to Run
Open the project in your IDE (like IntelliJ or Eclipse) or any text editor.

Ensure MySQL server is running.

Modify the connection line in the code if your credentials or DB name are different:

java
Copy
Edit
con = DriverManager.getConnection(
  "jdbc:mysql://localhost:3306/jdbc_db", "root", "your_password"
);
Compile and run:

bash
Copy
Edit
javac EmployeManagement.java
java com.project.EmployeManagement
📷 Screenshots (Optional)
text
Copy
Edit
1. Add Employee
2. Delete a Student
3. Display All Employee
4. Update Student
5. View a Particular Employee
6. Exist
Choose One Option :
⚠️ Notes
"Delete a Student" and "Update Student" options should be renamed to "Delete Employee" and "Update Employee" for clarity.

SQL Injection risk is avoided using PreparedStatement.

Handles user confirmation for deletion.

ResultSet cursor is consumed inside searchEmployee() — so don’t try to reuse it outside unless repositioned or fetched again.

📄 License
This project is for educational purposes only.
