package tasks.validators;

import java.util.Date;

public class TaskValidator {
    public static boolean validateSimple(Date time) {
        return !(time.getTime() < 0);
    }

    public static boolean validateRepeated(Date start, Date end, int interval) {
        if (start.getTime() < 0 || end.getTime() < 0 || interval < 1)
            return false;
        return true;
    }
}
