import React from "react";
import Task from "./Task";

const TaskList = ({tasks, onModify, onDelete}) => {
  return (
      <div className="taskList">
          {tasks.map(task =>(
              <Task task={task} key={task.id} onModify={onModify} onDelete={onDelete}/>
          ))}
      </div>
  );
}

export default TaskList;