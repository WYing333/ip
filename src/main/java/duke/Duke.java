package duke;

import duke.exception.emptyException;
import duke.exception.nonMatchException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numOfTasks = 0;
    public static boolean isTerminated = false;


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        showWelcomeScreen();

        try {
            openFile();
        } catch (FileNotFoundException e) {
            try {
                File f = new File("data/duke.txt");
                if (f.createNewFile()) {
                    System.out.println("Create a new file.");
                }
            } catch (IOException ex) {
                System.out.println("Unable to create file.");
            }
        }
        while (!isTerminated) {
            String input = in.nextLine();
            parse(input);
        }
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
     * Prints the line separator to make the output cleaner to users.
     */
    public static void printSeparator() {
        String separator="____________________________________________________________";
        System.out.println(separator);
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
     * Opens the file--duke.txt.
     * @throws FileNotFoundException If the file is not found.
     */
    public static void openFile() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            parse(s.nextLine());
        }
    }

    /**
     * Writes to the file--duke.txt.
     * @param filePath path of the file--duke.txt.
     */
    private static void writeFile(String filePath) {
        clearFile();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).writeToFile()+"\n");
            }
            fw.close();
        }catch (IOException e){
            System.out.println("\n\tTrouble with IO stream.\n");
        }
    }

    /**
     * Clears the file.
     */
    public static void clearFile(){
        try {
            File f = new File("data/duke.txt");
            FileWriter fw = new FileWriter(f);
            fw.write("");
            fw.flush();
            fw.close();
        }
        catch (IOException e){
            System.out.println("\n\tTrouble with IO stream.\n");
        }
    }

    /**
     * Deals with the input from the uses and the file and reacts to the input.
     * @param input the input string from the users and the file.
     */
    public static void parse (String input) {
        try {
            if(input.equals("done") || input.equals("todo") || input.equals("event") || input.equals("deadline")){
                throw new emptyException();
            }
            if (input.equals("bye")) {
                bye();
            } else if (input.equals("list")) {
                list(numOfTasks, tasks);
            } else if (input.contains("done")) {
                markDone(input, tasks);
            } else if (input.contains("todo")||input.startsWith("T")) {
                addToDo(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.contains("deadline")||input.startsWith("D")) {
                addDeadline(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.contains("event")||input.startsWith("E")) {
                addEvent(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.contains("delete")) {
                deleteTask(input);
            } else if (input.contains("find ")) {
                find(input);
            } else {
                throw new nonMatchException();
            }
        } catch (nonMatchException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            printSeparator();
        }catch (emptyException ex) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
            printSeparator();
        }
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
     * Deletes task from the ArrayList of Task--tasks.
     * @param input the input string from the users.
     */
    public static void deleteTask (String input) {
        int dividerPosition = input.indexOf(" ");
        int index = Integer.parseInt(input.substring(dividerPosition+1)) - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(index).toString());
        tasks.remove(index);
        numOfTasks--;
        System.out.println("Now you have " + (numOfTasks) +" task(s) in the list.");
        printSeparator();
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
            String[] inputDeadline = in.split("/");
            tasks.add(new Deadline(inputDeadline[0].replace("deadline", ""),
                    inputDeadline[1].replace("by", "")));
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
            String[] inputEvent = in.split("/");
            tasks.add(new Event(inputEvent[0].replace("event", ""),
                    inputEvent[1].replace("at", "")));
        }
        tasks.get(numOfTasks).setDone(isDone);
        printSuccessfullyAddedMessage(tasks, numOfTasks);
        numOfTasks++;
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
     * Finds the specific string provided by the users from the ArrayList of Task--tasks.
     * @param in the input string from the users.
     */
    public static void find(String in) {
        String findName = in.replace("find", "");
        tasks.stream()
                .filter(t -> t.getName().contains(findName))
                .findAny()
                .ifPresent(System.out::println);
        printSeparator();
    }

    /**
     * Prints things that are related to exit the program.
     */
    public static void bye() {
        writeFile("data/duke.txt");
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
        isTerminated = true;
    }

}
