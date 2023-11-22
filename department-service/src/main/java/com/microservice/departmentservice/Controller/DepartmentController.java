package com.microservice.departmentservice.Controller;

import com.microservice.departmentservice.client.EmployeeClient;
import com.microservice.departmentservice.model.Department;
import com.microservice.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private static  final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

    // localhost:8081/api/department/create
    @PostMapping("/create")
    public Department add(@RequestBody Department department){
        LOGGER.info("Department add: {}", department);
        return departmentRepository.addDepartment(department);
    }

    // localhost:8081/api/department/all
    @GetMapping("/all")
    public List<Department> findAll(){
        LOGGER.info("Department find");
        return departmentRepository.findAll();
    }

    // localhost:8081/api/department/search/{id}
    @GetMapping("/search/{id}")
    public Department findById(@PathVariable Long id){
        LOGGER.info("Department find: id={}", id);
        return departmentRepository.findById(id);
    }

    // localhost:8081/api/department/with-employees
    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        LOGGER.info("Department find");
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(department ->
                department.setEmployees(employeeClient.findByDepartment(department.getId())));

        return  departments;
    }

}
