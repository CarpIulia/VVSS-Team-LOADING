package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class TasksOperationsTest {

    @Test
    void incomingP01() {
        TasksOperations to = new TasksOperations(new ArrayList<Task>());
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        Iterable<Task> result = to.incoming(new Date(start), new Date(end));
        List<Task> tasks = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());

        assert (tasks.size() == 0);
    }

    @Test
    void incomingP03() {
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(new Task("Task1", new Date(1), new Date(1), 1));
        TasksOperations to = new TasksOperations(tasksList);
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        Iterable<Task> result = to.incoming(new Date(start), new Date(end));
        List<Task> tasks = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());

        assert (tasks.size() == 0);

    }

    @Test
    void incomingDCNextDateNullBeforeEnd() {
        List<Task> tasksList = new ArrayList<>();
        long start = 1;
        long end = System.currentTimeMillis();
        Task badTask = new Task("Task1", new Date(start));
        Task goodTask = new Task("Task1", new Date(start), new Date(end), 1);
        badTask.setActive(false);
        goodTask.setActive(true);
        tasksList.add(badTask);
        tasksList.add(goodTask);
        TasksOperations to = new TasksOperations(tasksList);

        Iterable<Task> result = to.incoming(new Date(start), new Date(start + end));
        List<Task> tasks = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());

        assert (tasks.contains(goodTask));
        assert (!tasks.contains(badTask));
    }

    @Test
    void incomingDCEqualsEnd() {
        List<Task> tasksList = new ArrayList<>();
        long start = 1;
        long end = System.currentTimeMillis();
        Task goodTask = new Task("Task1", new Date(start), new Date(start + 86400000), 86400000);
        goodTask.setActive(true);
        tasksList.add(goodTask);
        TasksOperations to = new TasksOperations(tasksList);

        Iterable<Task> result = to.incoming(new Date(start), new Date(start));
        List<Task> tasks = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());

        assert (tasks.contains(goodTask));
    }
}