package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class TaskTestUnit {

    private Task task;

    @BeforeEach
    void setUp() {
        task=mock(Task.class);


    }

//    @Test
//    void testTaskCreation() throws ParseException {
//       assert task.getTitle() == "new task";
//        System.out.println(task.getFormattedDateStart());
//        System.out.println(task.getDateFormat().format(Task.getDateFormat().parse("2021-02-12 10:10")));
//       assert task.getFormattedDateStart().equals(task.getDateFormat().format(Task.getDateFormat().parse("2021-02-12 10:10")));
//
//    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getsetTitle() {

        Mockito.when(task.getTitle()).thenReturn("TestTask");
        assert( task.getTitle().equals("TestTask"));
        Mockito.doNothing().when(task).setTitle("NewTitle");
        task.setTitle("NewTitle");
        Mockito.when(task.getTitle()).thenReturn("NewTitle");
        assert( task.getTitle().equals("NewTitle"));
        Mockito.verify(task,times(2)).getTitle();


    }

    @Test
    void isRepeated() {
        Mockito.when(task.isRepeated()).thenReturn(true);
        assert(task.isRepeated());
        Mockito.when(task.isRepeated()).thenReturn(false);
        assertFalse(task.isRepeated());
        Mockito.verify(task,times(2)).isRepeated();


    }


}
