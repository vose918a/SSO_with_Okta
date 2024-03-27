import React from 'react';
import TaskList from "./taskList";
import {Container} from "react-bootstrap";

const Principal = () => {
    return(
      <Container className="container">
          <h1 className="mt-4 mb-4">Task Manager</h1>
          <TaskList/>
      </Container>
  );
}

export default Principal;