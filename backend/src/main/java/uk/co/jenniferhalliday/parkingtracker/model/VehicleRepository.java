package uk.co.jenniferhalliday.parkingtracker.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Employee,Long> {

}
