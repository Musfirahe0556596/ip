package duke;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingTimeSeparator;
import duke.exception.DukeTaskOutOfRangeException;
import duke.task.Task;
import duke.task.TaskList;

public class Parser {
    public Parser() {

    }

    public static void performTodo(TaskList taskManager, String userInput) {
        try {
            Task newTask = taskManager.addTask(userInput);
            Storage.saveDataAddToListOperation(taskManager, newTask);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a todo cannot be empty!");
        }
    }

    public static void performTaskWithTimeConstraints(TaskList taskManager, String userInput, String stringSeparator, String taskType) {
        try {
            Task newTask = taskManager.addTaskWithTime(userInput, stringSeparator);
            Storage.saveDataAddToListOperation(taskManager, newTask);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a " + taskType + " cannot be empty!");
        } catch (DukeMissingTimeSeparator e) {
            System.out.println("OOPS! You did not include '" + stringSeparator + "' in your command!");
        }
    }

    public static void performMarking(TaskList taskManager, String userInput, boolean isMarked, String markType) {
        try {
            int taskNumberMarked = taskManager.markTask(isMarked, userInput);
            Storage.saveDataChangeMarkStatusOperation(taskManager, taskNumberMarked, isMarked);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! Please add the list number you want to " + markType +"!");
        } catch (NumberFormatException e) {
            System.out.println("OOPS! Specify a number for the list to " + markType +"!");
        } catch (DukeTaskOutOfRangeException e) {
            System.out.println("Task does not exist!");
        }
    }

    public static void performDeletion(TaskList taskManager, String userInput) {
        try {
            int taskNumberToDelete = taskManager.deleteTask(userInput);
            Storage.saveDataDeleteFromListOperation(taskManager, taskNumberToDelete);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! Please add the list number you want to delete!");
        } catch (NumberFormatException e) {
            System.out.println("OOPS! Specify a number for the list to delete!");
        } catch (DukeTaskOutOfRangeException e) {
            System.out.println("Task does not exist!");
        }
    }

    public static String validateAndExtractTaskDescription(String userInput) throws DukeEmptyDescriptionException {
        String[] arrayOfTaskStrings = userInput.split(" ");
        if (arrayOfTaskStrings.length <= 1) {
            throw new DukeEmptyDescriptionException();
        }

        String extractTaskDescription = "";
        for (int i = 1; i < arrayOfTaskStrings.length; i++) {
            extractTaskDescription += arrayOfTaskStrings[i] + " ";
        }

        return extractTaskDescription;
    }

    public static int fetchTaskNumber(String userInput) throws DukeTaskOutOfRangeException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        if (!isWithinTaskRange(taskNumber)) {
            throw new DukeTaskOutOfRangeException();
        }
        return taskNumber;
    }

    public static boolean isWithinTaskRange(int taskNumber) {
        if (taskNumber > Task.getNumberOfTasks() || taskNumber <= 0) {
            return false;
        }
        return true;
    }
}
