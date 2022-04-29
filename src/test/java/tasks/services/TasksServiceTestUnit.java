package tasks.services;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class TasksServiceTestUnit {

    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        tasksService=mock( TasksService.class);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void modifObservableList() {
        Task task=new Task("TestTask",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Task task2=new Task("TestTask2",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Mockito.when(tasksService.getObservableList()).thenReturn(FXCollections.observableArrayList(task));
        Mockito.doNothing().when(tasksService).modifObservableList(task,task2);
        assert (tasksService.getObservableList().contains(task));
        tasksService.modifObservableList(task,task2);
        Mockito.when(tasksService.getObservableList()).thenReturn(FXCollections.observableArrayList(task2));
        assert (tasksService.getObservableList().contains(task2));
        Mockito.verify(tasksService,times(2)).getObservableList();

    }

    @Test
    void addObservableList() {
        Task task=new Task("TestTask",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Mockito.when(tasksService.getObservableList()).thenReturn(FXCollections.observableArrayList(task));
        Mockito.doNothing().when(tasksService).addObservableList(task);
        tasksService.addObservableList(task);
        Mockito.verify(tasksService,times(1)).addObservableList(task);
        assertEquals(1,tasksService.getObservableList().size());
        assert  1==tasksService.getObservableList().size();
    }
}
