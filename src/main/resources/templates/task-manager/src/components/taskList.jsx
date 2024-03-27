import React,{useState,useEffect} from "react";
import Task from "./Task";
import {fetchTask} from "../utils/api";
import {Col, Container, Row} from "react-bootstrap";

const TaskList = ({onModify, onDelete}) => {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        const getTasks = async () => {
            try {
                const fetchedTasks = await fetchTask();
                setTasks(fetchedTasks);
            } catch (err) {
                throw new Error(err)
            }
        }
        getTasks();
    }, []);

    const getColumnSize = () => {
        const numTasks = tasks.length;
        if(numTasks >= 5){
            return 12 / numTasks;
        }
        return 4;
    }
    
    return (
        <Container>
            <Row>
                {tasks.map(task =>(
                    <Task task={task} key={task.id} onModify={onModify} onDelete={onDelete} colSize={getColumnSize()}/>
                ))}
            </Row>
        </Container>
  );
}

export default TaskList;