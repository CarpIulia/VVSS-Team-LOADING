package tasks.model;

import javafx.collections.ObservableList;

import java.util.*;

public class TasksOperations {

    public ArrayList<Task> tasks;

    public TasksOperations(ObservableList<Task> tasksList){
        tasks=new ArrayList<>();
        tasks.addAll(tasksList);
    }

    public Iterable<Task> incoming(Date start, Date end){
        //System.out.println(start);
        //System.out.println(end);
        ArrayList<Task> incomingTasks = new ArrayList<>();
        for(int i=0;i<tasks.size();i++) {
            Date nextTime = tasks.get(i).nextTimeAfter(start);
            if (nextTime != null) {
                if (nextTime.before(end)) {
                    incomingTasks.add(tasks.get(i));
                    System.out.println(tasks.get(i).getTitle());
                }
                if (nextTime.equals(end)) {
                    incomingTasks.add(tasks.get(i));
                    System.out.println(tasks.get(i).getTitle());
                }
            }
        }
        return incomingTasks;
    }
    public SortedMap<Date, Set<Task>> calendar( Date start, Date end){
        Iterable<Task> incomingTasks = incoming(start, end);
        TreeMap<Date, Set<Task>> calendar = new TreeMap<>();

        for (Task t : incomingTasks){
            Date nextTimeAfter = t.nextTimeAfter(start);
            while (nextTimeAfter!= null && (nextTimeAfter.before(end) || nextTimeAfter.equals(end))){
                if (calendar.containsKey(nextTimeAfter)){
                    calendar.get(nextTimeAfter).add(t);
                }
                else {
                    HashSet<Task> oneDateTasks = new HashSet<>();
                    oneDateTasks.add(t);
                    calendar.put(nextTimeAfter,oneDateTasks);
                }
                nextTimeAfter = t.nextTimeAfter(nextTimeAfter);
            }
        }
        return calendar;
    }
}

