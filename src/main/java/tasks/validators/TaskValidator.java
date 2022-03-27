package tasks.validators;

import tasks.model.Task;

import java.util.Date;

public class TaskValidator {
    public static boolean ValidateTask(Task task) {
        if (task.getStartTime().getTime() < 0 || task.getEndTime().getTime() < 0)
            return false;
        if(task.isRepeated() && task.getRepeatInterval() < 0)
            return false;
        return true;
    }
}
