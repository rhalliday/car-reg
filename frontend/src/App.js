import React from 'react';
import Employees from './components/Employees';
import SearchBar from './components/SearchBar';
import './app.css';

const api_endpoint = "http://localhost:8080/api/employees";

function makeLike (search) {
  // substitute spaces with %'s for the like
  return search.replace(/ /g, '%25');
}

const actions = {
  permitNumber: {
    displayName: "Permit Number",
    endpoint: value => "searchByPermit?number=" + parseInt(value, 10),
  },
  regNumber: {
    displayName: "Reg Number",
    endpoint: value => "searchByReg?reg=" + makeLike(value),
  },
  name: {
    displayName: "Name",
    endpoint: value => "searchByName?name=" + makeLike(value),
  }
};

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      employees: [],
      searchAction: actions.regNumber,
      searched: false,
    };
  }

  callAPI(endpoint) {
    console.log(api_endpoint + "/" + endpoint);
    fetch(api_endpoint + "/" + endpoint)
      .then(res => res.text())
      .then(res => this.setState({ employees: JSON.parse(res) }));
  }

  handleSearchActionClick(action) {
    document.getElementById('form-control-input').value = '';
    this.setState({ searchAction: action });
  }

  handleSearchSubmit(search) {
    let action = this.state.searchAction;
    this.callAPI(action.endpoint(search));
  }

  handleKeyDownEvent(event) {
    // 'keypress' event misbehaves on mobile so we track 'Enter' key via 'keydown' event
    if (event.key === 'Enter') {
      this.setState({ searched: true });

      event.preventDefault();
      event.stopPropagation();
      this.handleSearchSubmit(event.target.value);
    }
  }

  render() {
    let employees = this.state.employees;
    let searchAction = this.state.searchAction;
    let searched = this.state.searched;
    return (
      <div className="container">
        <div className="row">
          <div className="col"></div>
          <div className="col-10">
            <h1>Employee Vehicle Database</h1>
            <p className="introduction">Enter your search term below to track down the employee you need</p>
          </div>
          <div className="col"></div>
        </div>
        <div className="row">
          <div className="col"></div>
            <SearchBar
              className="col-10"
              searchAction={searchAction}
              actions={actions}
              handleSearchActionClick={this.handleSearchActionClick.bind(this)}
              handleKeyDownEvent={this.handleKeyDownEvent.bind(this)}
            />
          <div className="col"></div>
        </div>
        <div className="row">
          <div className="col"></div>
            <Employees
              className="col-10"
              employees={employees}
              searched={searched}
            />
          <div className="col"></div>
        </div>
      </div>
    );
  }
}

export default App;
