package uk.co.jenniferhalliday.parkingtracker.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.jenniferhalliday.parkingtracker.exception.EmployeeNotFoundException;
import uk.co.jenniferhalliday.parkingtracker.model.Employee;
import uk.co.jenniferhalliday.parkingtracker.model.EmployeeRepository;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class EmployeeController {

  private final EmployeeRepository repository;

  EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  // Aggregate root

  @GetMapping("/api/employees")
  List<Employee> all() {
    return repository.findAll();
  }

  @PostMapping("/api/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item

  @GetMapping("/api/employees/{id}")
  Employee one(@PathVariable Long id) {

    return repository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @GetMapping("/api/employees/searchByPermit")
  List<Employee> permitNumber(@RequestParam int number) {
    return repository.findByPermitNumber(number);
  }

  @GetMapping("/api/employees/searchByReg")
  List<Employee> regNumber(@RequestParam String reg) {
    return repository.findByRegNumber(reg);
  }

  @GetMapping("/api/employees/searchByName")
  List<Employee> nameSearch(@RequestParam String name) {
    return repository.findByName(name);
  }

  /*
  @PutMapping("/api/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

    return repository.findById(id)
        .map(employee -> {
          employee.setName(newEmployee.getName());
          employee.setRole(newEmployee.getRole());
          return repository.save(employee);
        })
        .orElseGet(() -> {
          newEmployee.setId(id);
          return repository.save(newEmployee);
        });
  }
  */

  @DeleteMapping("/api/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
