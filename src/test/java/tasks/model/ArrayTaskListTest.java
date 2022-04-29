package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.Array;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;

class ArrayTaskListTest {
    @Spy
    private ArrayTaskList arrayTaskList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        Task task=new Task("TestTask",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),300);
        Mockito.doNothing().when(arrayTaskList).add(task);
        //Mockito.verify(arrayTaskList, never()).getAll();
        //assertEquals(1,arrayTaskList.getAll().size());

    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
    }
}
