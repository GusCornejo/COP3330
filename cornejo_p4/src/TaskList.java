import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TaskList {

    List<TaskItem> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public void add(TaskItem taskitem){
        taskList.add(taskitem);
    }

    public void edit(int index, String newTitle, String newDescription, String newDate){

        taskList.get(index).setTitle(newTitle);
        taskList.get(index).setDescription(newDescription);
        taskList.get(index).setDueDate(newDate);

    }

    public void delete(int index){

        taskList.remove(index);
    }

    public void markCompleted(int index){

        taskList.get(index).placeCheckMark();
    }

    public void removeMark(int index){

        taskList.get(index).removeCheckMark();
    }

    public int size(){
        return taskList.size();
    }

    public void printList(){
        for (int i = 0; i < taskList.size(); i++){
            System.out.printf("%d) %s: %s - %s\n", i + 1, taskList.get(i).getTaskDate(), taskList.get(i).getTaskTitle(), taskList.get(i).getTaskDescription());
        }
    }

    public void printCompletedTaskList(){
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).getCompletionStatus()) {
                System.out.printf("%d) %s: %s - %s\n", i + 1, taskList.get(i).getTaskDate(), taskList.get(i).getTaskTitle(), taskList.get(i).getTaskDescription());
            }
        }
    }

    public void printUncompletedTaskList(){
        for (int i = 0; i < taskList.size(); i++){
            if (!taskList.get(i).getCompletionStatus()) {
                System.out.printf("%d) %s: %s - %s\n", i + 1, taskList.get(i).getTaskDate(), taskList.get(i).getTaskTitle(), taskList.get(i).getTaskDescription());
            }
        }
    }

    public void write(String fileName){
        try (Formatter output = new Formatter(fileName)){
            for (TaskItem taskItem : taskList) {
                output.format("%s|%s|%s\n", taskItem.getTaskDate(), taskItem.getTaskTitle(), taskItem.getTaskDescription());
            }
        } catch (FileNotFoundException e){
            System.err.println("Unable to find the file");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean isIndexValid(int index) {
        try {
            taskList.get(index).getCompletionStatus();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("The task selected does not exists");
        }
        return false;
    }


}
