package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import tasks.model.ArrayTaskList;
import tasks.model.Task;


import java.security.Provider;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;


class TaskServiceTestUnit {

    //@Mock
    private ArrayTaskList arrayTaskList;
   // //@InjectMocks
    private TasksService tasksService;

    @BeforeEach
    void setUp() {

        arrayTaskList=mock(ArrayTaskList.class);

        tasksService= new TasksService(arrayTaskList);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void addObservableList() {
        Task task=new Task("TestTask",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task));
        Mockito.doNothing().when(arrayTaskList).add(task);
        tasksService.addObservableList(task);
        Mockito.verify(arrayTaskList,times(1)).add(task);
        Mockito.verify(arrayTaskList,Mockito.never()).getAll();
        assert true;
        assertEquals(1,tasksService.getObservableList().size());
        assert  1==tasksService.getObservableList().size();
        Mockito.verify(arrayTaskList,times(2)).getAll();

    }

    @Test
    void deleteObservableList() {
        Task task=new Task("TestTask",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Task task2=new Task("TestTask2",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task2,task));
        Mockito.when(arrayTaskList.remove(task)).thenReturn(true);
        tasksService.deleteObservableList(task);
        Mockito.verify(arrayTaskList,times(1)).remove(task);
        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task2));
        assert true;
        assertEquals(1,tasksService.getObservableList().size());
        assert  1==tasksService.getObservableList().size();
        assert (this.tasksService.getObservableList().contains(task2));
        Mockito.verify(arrayTaskList,times(3)).getAll();


    }
}
