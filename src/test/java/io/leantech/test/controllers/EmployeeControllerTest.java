package io.leantech.test.controllers;

import io.leantech.test.entities.Employee;
import io.leantech.test.services.EmployeeService;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveEmployeeTest() {
        Employee employee = new Employee();
        Mockito.when(employeeService.saveEmployee(employee)).thenReturn(employee);
        ResponseEntity<?> response = employeeController.saveEmployee(employee);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void updateEmployeeTest() {
        Employee employeeRequest = new Employee();
        employeeRequest.setSalary(1000.0);
        Employee employeeDb = new Employee();
        employeeDb.setId(1L);
        Employee employeeUpdated = new Employee();
        employeeUpdated.setId(1L);
        employeeUpdated.setSalary(1000.0);
        Mockito.when(employeeService.updateEmployee(employeeDb.getId(), employeeRequest)).thenReturn(employeeUpdated);
        ResponseEntity<?> response = employeeController.updateEmployee(1L, employeeRequest);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void updateEmployeeNullTest() {
        Employee employeeRequest = new Employee();
        employeeRequest.setSalary(1000.0);
        Mockito.when(employeeService.updateEmployee(1L, employeeRequest)).thenReturn(null);
        ResponseEntity<?> response = employeeController.updateEmployee(1L, employeeRequest);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteEmployeeTest() {
        employeeController.deleteEmployee(1L);
    }

    @Test
    public void getEmployeesTest() {
        List<Employee> employees = Collections.singletonList(new Employee());
        Mockito.when(employeeService.getEmployees(null, null)).thenReturn(employees);
        employeeController.getEmployees(null, null);
    }
}
