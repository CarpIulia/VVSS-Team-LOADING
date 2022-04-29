package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class TasksServiceTestWithMockito {

    //@Mock
    private ArrayTaskList arrayTaskList;
    //@InjectMocks
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
       // this.tasksService = new TasksService(new ArrayTaskList());
        arrayTaskList=mock(ArrayTaskList.class);
        tasksService=new TasksService(arrayTaskList);
      //  MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addObservableList() {
        Task task=new Task("TestTask",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Task task2=new Task("TestTask",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task2));
        Mockito.doNothing().when(arrayTaskList).add(task);

        tasksService.addObservableList(task);
        Mockito.verify(arrayTaskList,times(1)).add(task);
        Mockito.verify(arrayTaskList,Mockito.never()).getAll();
        assert true;
        assertEquals(1,tasksService.getObservableList().size());
        assert  1==tasksService.getObservableList().size();
        //Mockito.verify(arrayTaskList,times(2)).getAll();

    }

    @Test
    void deleteObservableList() {
    }
}
