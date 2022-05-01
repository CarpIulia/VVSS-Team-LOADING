package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ArrayTaskListTestUnit {
    @Mock
    private ArrayTaskList arrayTaskList;
    @Mock
    private Task task;
    @Mock
    private Task task2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {

        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task));
        Mockito.doNothing().when(arrayTaskList).add(task);
        arrayTaskList.add(task);
        Mockito.verify(arrayTaskList,times(1)).add(task);
        Mockito.verify(arrayTaskList,Mockito.never()).getAll();
        assert true;
        assertEquals(1,arrayTaskList.getAll().size());
        Mockito.verify(arrayTaskList,times(1)).getAll();



    }

    @Test
    void remove() {

        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task));
        Mockito.when(arrayTaskList.remove(task)).thenReturn(true);
        Mockito.when(arrayTaskList.remove(task2)).thenReturn(false);
        assert (!arrayTaskList.remove(task2));
        arrayTaskList.remove(task);
        assert true;
        Mockito.verify(arrayTaskList,times(1)).remove(task);
       Mockito.verify(arrayTaskList,times(1)).remove(task2);


    }


}
