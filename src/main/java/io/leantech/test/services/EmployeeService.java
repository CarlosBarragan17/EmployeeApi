package io.leantech.test.services;

import io.leantech.test.daos.EmployeeDao;
import io.leantech.test.daos.PositionDao;
import io.leantech.test.entities.Employee;
import io.leantech.test.entities.Position;
import io.leantech.test.utils.MapperEmployee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PositionDao positionDao;

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Transactional
    public Employee saveEmployee(Employee employee) {
        logger.info("[Lean Tech]--- EmployeeService:saveEmployee--- employee:[{}]", employee.toString());
        List<Position> position = positionDao.findByName(employee.getPosition().getName());
        if (position != null && position.size() > 0) {
            employee.setPosition(position.get(0));
        }
        return employeeDao.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        logger.info("[Lean Tech]--- EmployeeService:updateEmployee--- employee id:[{}]", id);
        Employee employeeDb = employeeDao.findById(id).orElse(null);
        if (employeeDb != null) {
            MapperEmployee.updateEmployee(employeeDb, employee);
            if (employee.getPosition() != null && !employee.getPosition().getName().equals("")) {
                List<Position> position = positionDao.findByName(employee.getPosition().getName());
                if (position != null) {
                    employeeDb.setPosition(position.get(0));
                }
            }
            return employeeDao.save(employeeDb);
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteEmployee(Long id) {
        logger.info("[Lean Tech]--- EmployeeService:updateEmployee--- employee id:[{}]", id);
        employeeDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployees(String position, String name) {
        logger.info("[Lean Tech]--- EmployeeService:getEmployees--- position: [{}] name[{}]", position, name);
        if (position != null && !position.equals("")) {
            return employeeDao.findByPositionName(position);
        } else if (name != null && !name.equals("")) {
            return employeeDao.findByCandidateName(name);
        } else {
            return employeeDao.findAll();
        }
    }
}
