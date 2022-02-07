public class Task {
    protected String taskDescription;
    protected boolean isDone;

    private static int numberOfTasks = 0;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        numberOfTasks += 1;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public static int getNumberOfTasks()
    {
        return numberOfTasks;
    }

    public void markAsDone()
    {
        isDone = true;
    }

    public void markAsUndone()
    {
        isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskDescription;
        } else {
            return "[ ] " + taskDescription;
        }
    }
}