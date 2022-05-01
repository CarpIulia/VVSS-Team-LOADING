package tasks.services;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class IntegrationTestingArrayTask {


    private TasksService tasksService;
    private Task task;
    private Task task2;


    @BeforeEach
    void setUp() {
        task=mock(Task.class);
        task2=mock(Task.class);
        this.tasksService = new TasksService(new ArrayTaskList());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addObservableListGoodTask() {
        Mockito.when(task.getStartTime()).thenReturn(new Date());
        Mockito.when(task.getEndTime()).thenReturn(new Date());
        this.tasksService.addObservableList(task);
        assert(this.tasksService.getObservableList().contains(task));
    }

    @Test
    void removeObservableList() {
        Mockito.when(task.getStartTime()).thenReturn(new Date());
        Mockito.when(task.getEndTime()).thenReturn(new Date());
        Mockito.when(task.getTitle()).thenReturn("Task1");
        this.tasksService.addObservableList(task);
        Mockito.when(task.getTitle()).thenReturn("Task2");
        this.tasksService.addObservableList(task);
        assert 2==(tasksService.getObservableList().size());
        this.tasksService.deleteObservableList(task);
        assert 1==(tasksService.getObservableList().size());
        Mockito.when(task.getTitle()).thenReturn("Task1");
        assert(this.tasksService.getObservableList().contains(task));
    }

    @Test
    void modifyObservableList() {
        Mockito.when(task.getStartTime()).thenReturn(new Date());
        Mockito.when(task.getEndTime()).thenReturn(new Date());
        Mockito.when(task.getTitle()).thenReturn("Task1");
        Mockito.when(task2.getStartTime()).thenReturn(new Date());
        Mockito.when(task2.getEndTime()).thenReturn(new Date());
        Mockito.when(task2.getTitle()).thenReturn("Task2");
        this.tasksService.addObservableList(task);
        assert(this.tasksService.getObservableList().contains(task));
        this.tasksService.modifObservableList(task,task2);
        assert(this.tasksService.getObservableList().contains(task2));

    }
}
