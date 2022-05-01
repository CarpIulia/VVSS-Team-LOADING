package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;
import tasks.validators.TaskValidator;

import java.util.Date;

public class TasksService {

    private ArrayTaskList tasks;

    public TasksService(ArrayTaskList tasks){
        this.tasks = tasks;
    }

    //de modificat lista observable
    public void modifObservableList(Task task_v,Task task_n){
        tasks.remove(task_v);
        tasks.add(task_n);
        //TaskIO.rewriteFile(tasks);
    }

    //pentru adaugata in lista

    /**
     * The function adds a new task in the task list
     * @param task_n(titlu, start, end, interval)
     * start, end >= 0 interval >= 0
     */
    public void addObservableList(Task task_n){
        if(!TaskValidator.ValidateTask(task_n))
            throw new IllegalArgumentException("Time or interval can't be negative");
        tasks.add(task_n);
        //TaskIO.rewriteFile(tasks);
    }

    public void deleteObservableList(Task task) {
        tasks.remove(task);
        //TaskIO.rewriteFile(tasks);
    }
    ArrayTaskList getAllTasks()
    {
        return tasks;
    }



    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }

    /**
     * The function transforms the given interval in a hh:MM formatt where
     * hh = hours and MM = minutes
     * @param task - the task that repeats after an interval ( repeatInterval > 0 )
     * @return formatted interval
     */
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }
    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }

    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int result = (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
        return result;
    }

    public Iterable<Task> filterTasks(Date start, Date end){
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        Iterable<Task> filtered = tasksOps.incoming(start,end);

        return filtered;
    }
}
