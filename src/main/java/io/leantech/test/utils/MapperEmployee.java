package io.leantech.test.utils;

import io.leantech.test.entities.Employee;

public class MapperEmployee {

    public static void updateEmployee(Employee employeeDb, Employee newEmployee) {
        if (newEmployee.getCandidate() != null) {
            if (newEmployee.getCandidate().getName() != null && !newEmployee.getCandidate().getName().equals("")) {
                employeeDb.getCandidate().setName(newEmployee.getCandidate().getName());
            }
            if (newEmployee.getCandidate().getLastName() != null && !newEmployee.getCandidate().getLastName().equals("")) {
                employeeDb.getCandidate().setLastName(newEmployee.getCandidate().getLastName());
            }
            if (newEmployee.getCandidate().getAddress() != null && !newEmployee.getCandidate().getAddress().equals("")) {
                employeeDb.getCandidate().setAddress(newEmployee.getCandidate().getAddress());
            }
            if (newEmployee.getCandidate().getCellphone() != null && !newEmployee.getCandidate().getCellphone().equals("")) {
                employeeDb.getCandidate().setCellphone(newEmployee.getCandidate().getCellphone());
            }
            if (newEmployee.getCandidate().getCityName() != null && !newEmployee.getCandidate().getCityName().equals("")) {
                employeeDb.getCandidate().setCityName(newEmployee.getCandidate().getCityName());
            }
        }
        if (newEmployee.getSalary() != null && newEmployee.getSalary() > 0) {
            employeeDb.setSalary(newEmployee.getSalary());
        }
    }
}
