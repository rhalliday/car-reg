package uk.co.jenniferhalliday.parkingtracker.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long employee_id;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "skype_id")
  private String skypeId;
  private String email;
  private String dept;
  @Column(name = "permit_number")
  private int permitNumber;

  @ManyToMany
  @JoinTable(
      name = "employees_vehicles",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
  Set<Vehicle> vehicles;

  protected Employee () {}

  public Employee (String firstName, String lastName, String skypeId, String email, String dept, int permitNo) {
    this.dept = dept;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.permitNumber = permitNo;
    this.skypeId = skypeId;
  }

  @Override
  public String toString() {
    return String.format(
        "Employee[employee_id=%d, firstName='%s', lastName='%s', skypeId='%s', email='%s', dept='%s', permitNumber='%d']",
        employee_id, firstName, lastName, skypeId, email, dept, permitNumber);
  }

  public Long getEmployeeId() {
    return employee_id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getSkypeId() {
    return skypeId;
  }

  public String getEmail() {
    return email;
  }

  public String getDept() {
    return dept;
  }

  public Integer getPermitNumber() {
    return permitNumber;
  }

  public Set<Vehicle> getVehicles() {
    return vehicles;
  }
}
