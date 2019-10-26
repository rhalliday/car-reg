package uk.co.jenniferhalliday.parkingtracker.exception;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Long id) {
    super("Could not find employee " + id);
  }

  public EmployeeNotFoundException(int permitNumber) {
    super("Could not find employee with permit number " + permitNumber);
  }
}
