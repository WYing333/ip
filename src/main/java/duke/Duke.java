package duke;

import duke.exception.emptyException;
import duke.exception.nonMatchException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.UI;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numOfTasks = 0;
    public static boolean isTerminated = false;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        UI.showWelcomeScreen();

        try {
            openFile();
        } catch (FileNotFoundException e) {
            try {
                File f = new File("duke.txt");
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
        writeFile("duke.txt");
    }

    /**
     * Opens the file--duke.txt.
     * @throws FileNotFoundException If the file is not found.
     */
    public static void openFile() throws FileNotFoundException {
        File f = new File("duke.txt");
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
            UI.messageSavingError();
        }
    }

    /**
     * Clears the file.
     */
    public static void clearFile(){
        try {
            File f = new File("duke.txt");
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
                isTerminated = true;
                UI.bye();
            } else if (input.equals("list")) {
                UI.list(numOfTasks, tasks);
            } else if (input.contains("done")) {
                UI.markDone(input, tasks);
            } else if (input.startsWith("todo")||input.startsWith("T")) {
                UI.addToDo(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.startsWith("deadline")||input.startsWith("D")) {
                UI.addDeadline(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.startsWith("event")||input.startsWith("E")) {
                UI.addEvent(input, tasks, numOfTasks);
                numOfTasks++;
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else if (input.startsWith("find ")) {
                find(input);
            } else {
                throw new nonMatchException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            UI.messageStringIndexOutOfBoundsException();
        } catch (nonMatchException e) {
            UI.messageInvalidCommand();
        }catch (emptyException ex) {
            UI.messageSavingError();
        }
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
        UI.printSeparator();
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
        UI.printSeparator();
    }

}
