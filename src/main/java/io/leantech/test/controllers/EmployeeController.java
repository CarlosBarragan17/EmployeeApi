package io.leantech.test.controllers;

import io.leantech.test.entities.Employee;
import io.leantech.test.services.EmployeeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
        Employee employeeDB = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employeeDB, HttpStatus.CREATED);
    }

    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Employee does not exist.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/employee/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<?> getEmployees(@RequestParam(required = false) String position, @RequestParam(required = false) String name) {
        List<Employee> employees = employeeService.getEmployees(position,name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
