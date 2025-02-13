package es.uca.iw.fullstackwebapp.employee.repositories;

import es.uca.iw.fullstackwebapp.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByUsername(String username);
}
