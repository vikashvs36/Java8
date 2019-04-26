package com.dao;

import com.domain.Department;
import com.domain.Employee;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Create EmployeeDao Object
public class EmployeeDao implements EmpIntf {

    // Get All Employee
    @Override
    public List<Employee> list() {
        // Create list type Empoyee Object to store all Employee object and return it. We can say it will behave like Container.
        List<Employee> employees=new ArrayList<>();

        try {
            // Get Connection.
            Connection connection = DbConnection.getConnection();
            //Create Url
            String url="select * from employee";
            // Create PreparedStatement reference to execute query.
            PreparedStatement statement = (PreparedStatement) connection.prepareStatement(url);
            // Create resultSet reference to execute query.
            ResultSet rs =statement.executeQuery();

            // while loop for get all object element one by one and insert into the Department object.
            while (rs.next()){      // next() method is used to check next element is persist or not. It will always return boolean.

                // Create Employee object to set element one by one
                Employee employee=new Employee();
                employee.setId(rs.getLong("id"));                       // getLong() method to get long type element from db.
                employee.setName(rs.getString("name"));                 // getString() method to get string type element from db.
                employee.setAge(rs.getInt("age"));                      // getInt() method to get int type element from db.
                employee.setSalary(rs.getDouble("salary"));             // getDouble() method to get Double type element from db.
                employee.setCreatedAt(rs.getDate("created_at"));        // getDate() method to get Date type element from db.
                employee.setUpdatedAt(rs.getDate("updated_at"));
                Long department_id=rs.getLong("department_id");

                // Create department object to store of relative object.
                Department department=DepartmentDao.findbyId(department_id);
                // set department details into Employee object.
                employee.setDepartment(department);
                // store employee object to the list type data.
                employees.add(employee);
            }
        }
        catch (Exception e){ System.out.println("Error : "+e.getMessage()); }

        // return list type employee objects.
        return employees;
    }

    // Create main() method to test method which is given above.
    public static void main(String[] args) {

        // Create EmployeeDao object to call methods.
        EmployeeDao employeeDao=new EmployeeDao();
        // call list() method
        List<Employee> employeeList=employeeDao.list();

        // Iterate list of employee object
        for (Employee employee: employeeList) { System.out.println(employee); }

    }
}
