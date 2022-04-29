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

class IntegrareTaskTest {

    private Task task;
    private ArrayTaskList arrayTaskList;
    // //@InjectMocks
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        task=mock(Task.class);
        arrayTaskList=mock(ArrayTaskList.class);

        tasksService= new TasksService(arrayTaskList);

    }

    @AfterEach
    void tearDown() {
    }


//    @Test
//    void addObservableList() {
//        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task));
//        Mockito.doNothing().when(arrayTaskList).add(task);
//        tasksService.addObservableList(task);
//        Mockito.verify(arrayTaskList,times(1)).add(task);
//        Mockito.verify(arrayTaskList,Mockito.never()).getAll();
//        assert true;
//        assertEquals(1,tasksService.getObservableList().size());
//        assert  1==tasksService.getObservableList().size();
//        Mockito.verify(arrayTaskList,times(2)).getAll();
//
//    }
}
