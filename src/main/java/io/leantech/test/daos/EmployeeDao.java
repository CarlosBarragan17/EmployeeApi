package io.leantech.test.daos;

import io.leantech.test.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

    List<Employee> findByCandidateName(String name);

    List<Employee> findByPositionName(String position);
}
