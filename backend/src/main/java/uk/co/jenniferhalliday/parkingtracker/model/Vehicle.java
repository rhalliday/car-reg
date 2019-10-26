package uk.co.jenniferhalliday.parkingtracker.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long vehicle_id;
  private String reg_number;
  private String make;
  private String model;
  private String colour;

  @ManyToMany(mappedBy = "vehicles")
  Set<Employee> employees;

  protected Vehicle () {}

  public Vehicle (String regNumber, String make, String model, String colour) {
    this.colour = colour;
    this.reg_number = regNumber;
    this.make = make;
    this.model = model;
  }

  @Override
  public String toString() {
    return String.format(
        "Vehicle[vehicle_id=%d, reg_number='%s', colour='%s', make='%s', model='%s']",
        vehicle_id, reg_number, colour, make, model);
  }

  public Long getVehicleId() {
    return vehicle_id;
  }

  public String getRegNumber() {
    return reg_number;
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public String getColour() {
    return colour;
  }
}
