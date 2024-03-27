import React from 'react';

const Task = ({task, onModify, onDelete}) => {
    return (
        <div className="task">
            <h2>{task.description}</h2>
            <p>{task.createdDate}</p>
            <h3>{task.status}</h3>
            <button onClick={() => onModify(task)}>Modify</button>
            <button onClick={() => onDelete(task)}>Delete</button>
        </div>
    );
}

export default Task;