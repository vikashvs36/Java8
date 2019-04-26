package com.dao;

import com.domain.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Create class.
public class DepartmentDao {

    // Create method to find Department by id.
    public static Department findbyId(Long id) {

        // Create Url.
        String url="Select * from department where id=?";
        // Create Department object to return as container.
        Department department=new Department();

        try {
            // Call getConnection() to make connection.
            Connection connection=DbConnection.getConnection();
            // Create preparedStatement to execute parameterize query.
            PreparedStatement ps = connection.prepareStatement(url);
            // Set parameterize query.
            ps.setLong(1,id);
            // Create ResultSet interface to execute query.
            ResultSet rs=ps.executeQuery();

            // Check element exist or not.
            if(rs.next()){
                // If element exist then we can get and set to the Object.

                department.setId(rs.getLong("id"));         // getLong() method to get long type element from db.
                department.setName(rs.getString("name"));   // getString() method to get string type element from db.
            }
        }catch (Exception e){ System.out.println("Error : "+e.getMessage()); }

        // return department object.
        return department;
    }

    // Create list() method to return all department object.
    public static List<Department> list(){

        // Create list type Department object to store all element and return it as container.
        List<Department> departments=new ArrayList<>();
        // Create url.
        String url="Select * from department";

        try {
            // get connection .
            Connection connection=DbConnection.getConnection();
            // Create PreparedStatement reference to execute query.
            PreparedStatement ps = connection.prepareStatement(url);
            // Create resultSet reference to execute query.
            ResultSet rs=ps.executeQuery();

            // while loop for get all object element one by one and insert into the Department object.
            while (rs.next()){      // next() method is used to check next element is persist or not. It will always return boolean.

                // Create Department object to set element.
                Department department=new Department();
                department.setId(rs.getLong("id"));             // getLong() method to get long type element from db.
                department.setName(rs.getString("name"));       // getString() method to get string type element from db.
                departments.add(department);                               // add department object into list type department.
            }

        }catch (Exception e){ System.out.println("Error : "+e.getMessage()); }

        // return department object.
        return departments;
    }

    // Create main() method to test method which is given above.
    public static void main(String[] args) {

        // call findbyId() method to test
        findbyId(Long.valueOf(4));
        // call list() method to get all Department Objects.
        List<Department> departmentList=list();

        // Iterate list type Department object to print all elements
        for (Department department: departmentList) { System.out.println(department); }
    }
}
