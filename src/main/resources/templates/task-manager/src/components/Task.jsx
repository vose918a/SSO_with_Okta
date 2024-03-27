import React from 'react';
import {Button, Card, Col} from 'react-bootstrap'

const Task = ({task, onModify, onDelete}) => {
    return (
        <Col md={4} sm={6}  lg={3}>
            <Card className="task" style={{width:'18rem'}}>
                <Card.Body>
                    <Card.Text>
                        <h2>{task.description}</h2>
                        <p>{task.createdDate}</p>
                        <h4>{task.status}</h4>
                    </Card.Text>
                    <Button variant="primary" onClick={onModify}>Modify</Button>
                    <Button variant="danger" onClick={onDelete}>Delete</Button>
                </Card.Body>
            </Card>
        </Col>
    );
}

export default Task;