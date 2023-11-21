package com.microservice.employeeservice.controller;

import com.microservice.employeeservice.model.Employee;
import com.microservice.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private static  final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // localhost:8081/api/employee/create
    @PostMapping("/create")
    public Employee add(@RequestBody Employee employee){
        LOGGER.info("Employee add: {}", employee);
        return employeeRepository.addEmployee(employee);
    }

    // localhost:8081/api/employee/all
    @GetMapping("/all")
    public List<Employee> findAll(){
        LOGGER.info("Employee find");
        return employeeRepository.findAll();
    }

    // localhost:8081/api/employee/search/{id}
    @GetMapping("/search/{id}")
    public Employee findById(@PathVariable Long id){
        LOGGER.info("Employee find: id={}", id);
        return employeeRepository.findById(id);
    }

    // localhost:8081/api/employee/department/{departmentId}
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return employeeRepository.findByDepartment(departmentId);
    }
}
