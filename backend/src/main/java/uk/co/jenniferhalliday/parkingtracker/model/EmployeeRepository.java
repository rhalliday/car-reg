package uk.co.jenniferhalliday.parkingtracker.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
  List<Employee> findByPermitNumber(int permitNumber);

  @Query(
      value = "SELECT * FROM employees e WHERE concat(e.first_name, e.last_name) ILIKE concat('%',:name,'%')",
      nativeQuery = true
  )
  List<Employee> findByName(@Param("name") String name);

  @Query(
      value = "SELECT * FROM employees e LEFT JOIN employees_vehicles ev ON e.employee_id = ev.employee_id LEFT JOIN vehicles v ON ev.vehicle_id = v.vehicle_id WHERE v.reg_number ILIKE CONCAT('%', :reg, '%')",
      nativeQuery = true
  )
  List<Employee> findByRegNumber(@Param("reg") String reg);
}
