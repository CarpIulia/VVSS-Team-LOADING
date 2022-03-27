package tasks.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.Date;

@DisplayName("Task service tests")
@TestMethodOrder(MethodOrderer.Random.class)
class TasksServiceTest {

    TasksService tasksService;

    @BeforeEach
    void setUp() {
        this.tasksService = new TasksService(new ArrayTaskList());
    }

    @Test
    @Timeout(10)
    @Tag("ECP")
    void addObservableListGoodTask_ECP() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        Task task = new Task("Task1", new Date(start), new Date(end), 86400);

        this.tasksService.addObservableList(task);

        assert(this.tasksService.getObservableList().contains(task));
    }

    @Test
    @Tag("ECP")
    void addObservableListBadIntervalTask_ECP() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        Task task = new Task("Task1", new Date(start), new Date(end), -100);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> this.tasksService.addObservableList(task));
        assert(ex.getMessage().equals("Time or interval can't be negative"));
    }

    @Test
    @Tag("ECP")
    void addObservableListBadStartDateTask_ECP() {
        long start = -1000;
        long end = System.currentTimeMillis();
        Task task = new Task("Task1", new Date(start), new Date(end), 86400);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> this.tasksService.addObservableList(task));
        assert(ex.getMessage().equals("Time or interval can't be negative"));
    }

    @Test
    @Tag("ECP")
    void addObservableListBadEndDateTask_ECP() {
        long start = System.currentTimeMillis();;
        long end = -1000;
        Task task = new Task("Task1", new Date(start), new Date(end), 86400);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> this.tasksService.addObservableList(task));
        assert(ex.getMessage().equals("Time or interval can't be negative"));
    }

    @Test
    @Tag("BVA")
    void addObservableTaskIntervalValid_BVA() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        int intervalMin = 0;
        int intervalMax = Integer.MAX_VALUE;
        Task taskIntervalMin = new Task("Task1", new Date(start), new Date(end), intervalMin);
        Task taskIntervalMax = new Task("Task1", new Date(start), new Date(end), intervalMax);

        this.tasksService.addObservableList(taskIntervalMin);
        this.tasksService.addObservableList(taskIntervalMax);

        assert (this.tasksService.getObservableList().contains(taskIntervalMin));
        assert (this.tasksService.getObservableList().contains(taskIntervalMax));
    }

    @Test
    @Tag("BVA")
    void addObservableTaskIntervalInvalid_BVA() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        int intervalMinInv = -1;
        int intervalMaxInv = Integer.MAX_VALUE + 1;
        Task taskIntervalMinInv = new Task("Task1", new Date(start), new Date(end), intervalMinInv);
        Task taskIntervalMaxInv = new Task("Task1", new Date(start), new Date(end), intervalMaxInv);

        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> this.tasksService.addObservableList(taskIntervalMinInv));
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> this.tasksService.addObservableList(taskIntervalMaxInv));

        assert(ex1.getMessage().equals("Time or interval can't be negative"));
        assert(ex2.getMessage().equals("Time or interval can't be negative"));
    }

    @Test
    @Tag("BVA")
    void addObservableTaskStartDateValid_BVA() {
        long startMin = 0;
        long startMax = Long.MAX_VALUE;
        long end = System.currentTimeMillis();
        Task taskStartMin= new Task("Task1", new Date(startMin), new Date(end), 1);
        Task taskStartMax = new Task("Task1", new Date(startMax), new Date(end), 1);

        this.tasksService.addObservableList(taskStartMin);
        this.tasksService.addObservableList(taskStartMax);

        assert (this.tasksService.getObservableList().contains(taskStartMax));
        assert (this.tasksService.getObservableList().contains(taskStartMax));
    }

    @Test
    @Tag("BVA")
    void addObservableTaskStartDateInvalid_BVA() {
        long startMinInv = -1;
        long startMaxInv = Long.MAX_VALUE + 1;
        long end = System.currentTimeMillis();
        Task taskStartMinInv= new Task("Task1", new Date(startMinInv), new Date(end), 1);
        Task taskStartMaxInv = new Task("Task1", new Date(startMaxInv), new Date(end), 1);


        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> this.tasksService.addObservableList(taskStartMinInv));
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> this.tasksService.addObservableList(taskStartMaxInv));

        assert(ex1.getMessage().equals("Time or interval can't be negative"));
        assert(ex2.getMessage().equals("Time or interval can't be negative"));
    }
}