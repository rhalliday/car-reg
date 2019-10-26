CREATE TABLE employees_vehicles (
      employee_id INTEGER NOT NULL REFERENCES employees(employee_id)
    , vehicle_id INTEGER NOT NULL REFERENCES vehicles(vehicle_id)
    , PRIMARY KEY (employee_id, vehicle_id)
);
