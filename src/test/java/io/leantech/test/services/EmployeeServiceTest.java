package io.leantech.test.services;

import io.leantech.test.daos.EmployeeDao;
import io.leantech.test.daos.PositionDao;
import io.leantech.test.entities.Candidate;
import io.leantech.test.entities.Employee;
import io.leantech.test.entities.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private PositionDao positionDao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveEmployeeTest() {
        String positionName = "developer";
        Employee employee = new Employee();
        Position position = new Position();
        position.setName(positionName);
        employee.setPosition(position);
        Employee employeeDb = new Employee();
        employeeDb.setPosition(position);
        Mockito.when(positionDao.findByName(positionName)).thenReturn(null);
        Mockito.when(employeeDao.save(employee)).thenReturn(employeeDb);

        employeeService.saveEmployee(employee);
    }

    @Test
    public void saveEmployeeWithPositionExistTest() {
        String positionName = "developer";
        Employee employee = new Employee();
        Position position = new Position();
        position.setName(positionName);
        employee.setPosition(position);
        Employee employeeDb = new Employee();
        employeeDb.setPosition(position);
        Mockito.when(positionDao.findByName(positionName)).thenReturn(Collections.singletonList(position));
        Mockito.when(employeeDao.save(employee)).thenReturn(employeeDb);

        employeeService.saveEmployee(employee);
    }

    @Test
    public void updateEmployeeTest() {
        Long idEmployee = 1L;
        String positionName = "developer";
        Position position = new Position();
        position.setName(positionName);
        Employee employeeRequest = new Employee();
        employeeRequest.setId(idEmployee);
        employeeRequest.setSalary(100.0);
        employeeRequest.setPosition(position);
        Employee employeeDb = new Employee();
        employeeDb.setId(1L);
        employeeDb.setSalary(50.0);
        Mockito.when(employeeDao.findById(idEmployee)).thenReturn(Optional.of(employeeDb));
        Mockito.when(positionDao.findByName(positionName)).thenReturn(Collections.singletonList(position));
        Mockito.when(employeeDao.save(employeeRequest)).thenReturn(employeeRequest);

        employeeService.updateEmployee(idEmployee, employeeRequest);
    }

    @Test
    public void updateEmployeeNullTest() {
        Long idEmployee = 1L;
        String positionName = "developer";
        Position position = new Position();
        position.setName(positionName);
        Employee employeeRequest = new Employee();
        employeeRequest.setId(idEmployee);
        employeeRequest.setSalary(100.0);
        employeeRequest.setPosition(position);
        Mockito.when(employeeDao.findById(idEmployee)).thenReturn(Optional.empty());

        employeeService.updateEmployee(idEmployee, employeeRequest);
    }

    @Test
    public void deleteEmployeeTest() {
        Long idEmployee = 1L;
        employeeService.deleteEmployee(idEmployee);
    }

    @Test
    public void getEmployeesTest() {
        employeeService.getEmployees(null, null);
    }

    @Test
    public void getEmployeesByPositionTest() {
        String positionName = "developer";
        Position position = new Position();
        position.setName(positionName);
        List<Employee> employees = new ArrayList<>();
        Mockito.when(employeeDao.findByPositionName(positionName)).thenReturn(employees);

        employeeService.getEmployees(positionName, null);
    }

    @Test
    public void getEmployeesByNameTest() {
        String candidateName = "Carlos";
        Candidate candidate = new Candidate();
        candidate.setName(candidateName);
        List<Employee> employees = new ArrayList<>();
        Mockito.when(employeeDao.findByCandidateName(candidateName)).thenReturn(employees);

        employeeService.getEmployees(null, candidateName);
    }
}
