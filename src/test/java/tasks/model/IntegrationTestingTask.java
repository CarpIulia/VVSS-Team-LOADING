package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.services.TasksService;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class IntegrationTestingTask {

    private TasksService tasksService;
    private ArrayTaskList arrayTaskList;
    private Task task;

    @BeforeEach
    void setUp() {
        task=new Task("Task1", new Date(), new Date(), 10);
        arrayTaskList=new ArrayTaskList();
        tasksService= new TasksService(arrayTaskList);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void addObservableListGoodTask() {
        this.tasksService.addObservableList(task);
        assert(this.tasksService.getObservableList().contains(task));
        assert 1==(this.tasksService.getObservableList().size());
    }

    @Test
    void removeObservableList() {
        this.tasksService.addObservableList(task);
        task.setTitle("Task2");
        this.tasksService.addObservableList(task);
        task.setTitle("Task1");
        assert 2==(tasksService.getObservableList().size());
        this.tasksService.deleteObservableList(task);
        assert 1==(tasksService.getObservableList().size());
        task.setTitle("Task2");
        assert(this.tasksService.getObservableList().contains(task));
    }

    @Test
    void modifyObservableList() {

        this.tasksService.addObservableList(task);
        assert(this.tasksService.getObservableList().contains(task));
        Task task2=task;
        task2.setTitle("Task2");
        this.tasksService.modifObservableList(task,task2);
        assert(this.tasksService.getObservableList().contains(task2));

    }

}
