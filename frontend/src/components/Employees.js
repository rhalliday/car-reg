import React from 'react';
import Card from 'react-bootstrap/Card';
import Table from 'react-bootstrap/Table';

function Employees(props) {
  if (props.searched) {
    let cards = [];
    if (props.employees.length > 0) {
      cards = props.employees.map((employee) => {
        let employeeCars = employee.vehicles.map((vehicle) => {
          return (
            <tr key={vehicle.vehicleId}>
              <td>{vehicle.regNumber}</td>
              <td>{vehicle.make}</td>
              <td>{vehicle.model}</td>
              <td>{vehicle.colour}</td>
            </tr>
          )
        });
        return (
          <Card key={employee.employeeId} className="card-spacer">
            <Card.Body>
              <Card.Title>{employee.firstName} {employee.lastName} ({employee.email})</Card.Title>
              <Card.Text>Permit Number: {employee.permitNumber}</Card.Text>
              <Table responsive="sm">
                <thead>
                  <tr>
                    <th>Reg</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Colour</th>
                  </tr>
                </thead>
                <tbody>
                  {employeeCars}
                </tbody>
              </Table>
            </Card.Body>
          </Card>
        )
      });
    }
    if (cards.length > 0) {
      return (
        <div className={props.className}>{cards}</div>
      );
    }
    let className = "alert alert-warning " + props.className;
    return (
      <div className={className} role="alert">
        Unable to find any employees matching that search criteria.
      </div>
    );
  }
  return (<div></div>);
}

export default Employees;