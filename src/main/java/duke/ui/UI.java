package duke.ui;

import java.util.ArrayList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class UI {

    /**
     * Prints the line separator to make the output cleaner to users.
     */
    public static void printSeparator() {
        String separator="____________________________________________________________";
        System.out.println(separator);
    }

    /**
     * Shows the welcome screen of the Duke.
     */
    public static void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printSeparator();
        System.out.println("Hello! I'm Duke.Duke");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    /**
     * Prints the successfully added message to users.
     * @param tasks the ArrayList of Task class used in the whole program.
     * @param numOfTasks the total number of tasks in the ArrayList of Task--tasks.
     */
    public static void printSuccessfullyAddedMessage (ArrayList<Task> tasks, int numOfTasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(numOfTasks).toString());
        System.out.println("Now you have " + (numOfTasks+1) + " task(s) in the list.");
        printSeparator();
    }

    /**
     * Lists all the tasks to the users.
     * @param numOfTasks the total number of tasks in the ArrayList of Task--tasks.
     * @param tasks the ArrayList of Task class used in the whole program.
     */
    public static void list(int numOfTasks, ArrayList<Task> tasks) {
        if (numOfTasks == 0) {
            System.out.println("Your list is empty.");
        }
        else {
            System.out.println("Here are the task(s) in your list:");
            for (int j = 0; j < numOfTasks; j++) {
                System.out.println((j + 1) + "." + tasks.get(j).toString());
            }
        }
        printSeparator();
    }

    /**
     * Marks the specific task as Done.
     * @param in the input string from the users.
     * @param tasks the ArrayList of Task class used in the whole program.
     */
    public static void markDone(String in, ArrayList<Task> tasks) {
        int dividerPosition = in.indexOf(" ");
        String number = in.substring(dividerPosition+1);
        int i = Integer.parseInt(number);
        i = i - 1;
        tasks.get(i).markAsDone();
        System.out.println(tasks.get(i).toString());
        printSeparator();
    }

    /**
     * Adds task to the ArrayList of Task--tasks.
     * @param input the input string from the users and the file.
     * @param tasks the ArrayList of Task class used in the whole program.
     * @param numOfTasks the total number of tasks in the ArrayList of Task--tasks.
     */
    public static void addTask (String input, ArrayList<Task> tasks, int numOfTasks) {
        tasks.add(new Task(input));
        System.out.println("added: " + tasks.get(numOfTasks).getName());
        numOfTasks++;
    }

    /**
     * Adds ToDo task from the users and the file to the ArrayList of Task--tasks.
     * @param in the input string from the users and the file.
     * @param tasks the ArrayList of Task class used in the whole program.
     * @param numOfTasks the total number of tasks in the ArrayList of Task--tasks.
     */
    public static void addToDo(String in, ArrayList<Task> tasks, int numOfTasks) {
        boolean isFromFile = in.startsWith("T");
        boolean isDone = false;
        if (isFromFile) {
            String[] sentences=in.split(" \\| ");
            if (sentences[0].equals("T")){
                if (sentences[1].equals("1")) {
                    isDone = true;
                }
            }
            tasks.add(new ToDo(sentences[2]));
        }
        else {
            tasks.add(new ToDo(in.replace("todo", "")));
        }
        tasks.get(numOfTasks).setDone(isDone);
        printSuccessfullyAddedMessage(tasks, numOfTasks);
        numOfTasks++;
    }

    /**
     * Adds Deadline task from the users and the file to the ArrayList of Task--tasks.
     * @param in the input string from the users and the file.
     * @param tasks the ArrayList of Task class used in the whole program.
     * @param numOfTasks the total number of tasks in the ArrayList of Task--tasks.
     */
    public static void addDeadline(String in, ArrayList<Task> tasks, int numOfTasks) {
        boolean isFromFile = in.startsWith("D");
        boolean isDone = false;
        if (isFromFile) {
            String[] sentences=in.split(" \\| ");
            if (sentences[0].equals("D")){
                if (sentences[1].equals("1")) {
                    isDone = true;
                }
            }
            tasks.add(new Deadline(sentences[2],sentences[3]));
        }
        else{
            String descriptionOfDeadline;
            String by;
            descriptionOfDeadline = in.substring(9, (in.indexOf("/") - 1));
            by = in.substring((in.indexOf("/") + 4));
            tasks.add(new Deadline(descriptionOfDeadline,by));

        }

        tasks.get(numOfTasks).setDone(isDone);
        printSuccessfullyAddedMessage(tasks, numOfTasks);
        numOfTasks++;
    }

    /**
     * Adds Event task from the users and the file to the ArrayList of Task--tasks.
     * @param in the input string from the users and the file.
     * @param tasks the ArrayList of Task class used in the whole program.
     * @param numOfTasks the total number of tasks in the ArrayList of Task--tasks.
     */
    public static void addEvent(String in, ArrayList<Task> tasks, int numOfTasks) {
        boolean isFromFile = in.startsWith("E");
        boolean isDone = false;
        if (isFromFile) {
            String[] sentences=in.split(" \\| ");
            if (sentences[0].equals("E")){
                if (sentences[1].equals("1")) {
                    isDone = true;
                }
            }
            tasks.add(new Event(sentences[2],sentences[3]));
        }
        else {
            String descriptionOfEvent;
            String at;
            descriptionOfEvent = in.substring(6, (in.indexOf("/") - 1));
            at = in.substring((in.indexOf("/") + 4));
            tasks.add(new Event(descriptionOfEvent, at));

        }
        tasks.get(numOfTasks).setDone(isDone);
        printSuccessfullyAddedMessage(tasks, numOfTasks);
        numOfTasks++;
    }

    /**
     * Prints the corresponding exception message.
     */
    public static void messageStringIndexOutOfBoundsException(){
        printSeparator();
        System.out.println("  The task you input has missing fields!");
        printSeparator();
    }

    /**
     * Prints the writing file error message.
     */
    public static void messageSavingError(){
        printSeparator();
        System.out.println("    Failed to save task list.");
        printSeparator();
    }

    /**
     * Prints the invalid message command.
     */
    public static void messageInvalidCommand(){
        printSeparator();
        System.out.println("  OOPS!!! I'm sorry, but I don't know what that means :-(");
        printSeparator();
        System.out.println();
    }

    /**
     * Prints things that are related to exit the program.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        UI.printSeparator();
    }

}
