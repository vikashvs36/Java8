package com.service;

import com.dao.EmployeeDao;
import com.domain.Employee;

import java.util.List;

// Create Employee Service.
public class EmployeeServiceImpl {

    // Create EmployeeDao object to call database method.
    static EmployeeDao employeeDao=new EmployeeDao();

    // Create list() method to print all Employee object.
    public static List<Employee> list(){ return employeeDao.list(); }
}
