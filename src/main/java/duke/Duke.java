package duke;

import duke.exception.DukeException;
import duke.exception.emptyException;
import duke.exception.nonMatchException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static final int MAX_TASK_NUM = 100;
    public static Task[] taskArray = new Task[MAX_TASK_NUM];

    public static void printSeparator() {
        String separator="____________________________________________________________";
        System.out.println(separator);
    }

    public static int readFile(Task[] tasks){
        try {
            int sum=0;
            File f = new File("/Users/wendyw/Desktop/study/cz2006(cs2113)/ip/data/duke.txt");
            Scanner s = new Scanner(f);
            while(s.hasNext()){
                String line=s.nextLine();
                boolean isDone=false;
                String[] sentences=line.split(" \\| ");
                if (sentences[0].equals("T")){
                    tasks[sum]=new ToDo(sentences[2]);
                    if (sentences[1].equals("1")){
                        isDone=true;
                    }
                    tasks[sum].setDone(isDone);
                    sum++;
                }
                else if (sentences[0].equals("D")){
                    tasks[sum]=new Deadline(sentences[2],sentences[3]);
                    if (sentences[1].equals("1")){
                        isDone=true;
                    }
                    tasks[sum].setDone(isDone);
                    sum++;
                }
                else if (sentences[0].equals("E")){
                    tasks[sum]=new Event(sentences[2],sentences[3]);
                    if (sentences[1].equals("1")){
                        isDone=true;
                    }
                    tasks[sum].setDone(isDone);
                    sum++;
                }
            }
            return sum;
        }
        catch (FileNotFoundException e){
            System.out.println("\n\tThe file does not exist.\n");
        }
        return 0;
    }

    public static void writeFile(Task[] tasks, int sum){
        try {
            File f = new File("/Users/wendyw/Desktop/study/cz2006(cs2113)/ip/data/duke.txt");
            clearFile();
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < sum; i++) {
                fw.write(tasks[i].writeToFile()+"\n");
            }
            fw.close();
        }
        catch (IOException e){
            System.out.println("\n\tTrouble with IO stream.\n");
        }
    }

    public static void clearFile(){
        try {
            File f = new File("/Users/wendyw/Desktop/study/cz2006(cs2113)/ip/data/duke.txt");
            FileWriter fw = new FileWriter(f);
            fw.write("");
            fw.flush();
            fw.close();
        }
        catch (IOException e){
            System.out.println("\n\tTrouble with IO stream.\n");
        }
    }


    public static void main(String[] args) {

        showWelcomeScreen();
        int inputCount=0;
        int outputCount=1;
        String inputString;
        String input;

        int sumList=readFile(taskArray);

        do {
            Scanner in = new Scanner(System.in);
            inputString = in.nextLine();
            input = inputString.trim();

            try {
                if(input.equals("done") || input.equals("todo") || input.equals("event") || input.equals("deadline")){
                    throw new emptyException();
                }
            } catch (DukeException e) {
                continue;
            }

                if (inputString.equals("list")) {
                    if (taskArray[0]==null) {
                        System.out.println("List is empty.");
                    }
                    else {
                        System.out.println("Here are the tasks in your list: ");
                        while (outputCount<=inputCount) {
                            System.out.println(taskArray[outputCount-1]);
                            outputCount++;
                        }
                    }
                    outputCount=1;
                }

                else if (inputString.contains("done ")) {
                    String[] inputWords = inputString.split(" ");
                    int doneNumber = Integer.parseInt(inputWords[1]);
                    while (outputCount <= inputCount) {
                        if (doneNumber == outputCount) {
                            taskArray[outputCount - 1].markAsDone();
                            System.out.println(taskArray[outputCount - 1]);
                        }
                        outputCount++;
                    }
                    outputCount = 1;
                }

                else if (inputString.contains("todo")) {
                    taskArray[inputCount] = new ToDo(inputString.replace("todo", ""));
                    printSuccessfullyAddedMessage(taskArray[inputCount]);
                    inputCount++;
                    System.out.println("Now you have " + inputCount + " tasks in the list.");
                }
                else if (inputString.contains("deadline")) {
                    String[] inputDeadline = inputString.split("/");
                    taskArray[inputCount] = new Deadline(inputDeadline[0].replace("deadline", ""),
                            inputDeadline[1].replace("by", ""));
                    printSuccessfullyAddedMessage(taskArray[inputCount]);
                    inputCount++;
                    System.out.println("Now you have " + inputCount + " tasks in the list.");
                }
                else if (inputString.contains("event")) {
                    String[] inputEvent = inputString.split("/");
                    taskArray[inputCount] = new Event(inputEvent[0].replace("event", ""),
                            inputEvent[1].replace("at", ""));
                    printSuccessfullyAddedMessage(taskArray[inputCount]);
                    inputCount++;
                    System.out.println("Now you have " + inputCount + " tasks in the list.");
                }
                else if(inputString.contains("delete ")) {
                    String[] deleteWords = inputString.split(" ");
                    int deleteNumber = Integer.parseInt(deleteWords[1]);
                    while (outputCount <= inputCount) {
                        if (deleteNumber == outputCount) {
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(taskArray[deleteNumber - 1]);
                            for (int i = deleteNumber - 1; i < inputCount - 1; i++) {
                                taskArray[i] = taskArray[i + 1];
                            }
                            //taskArray[inputCount - 1] = null;
                            inputCount--;
                            System.out.println("Now you have " + inputCount + " tasks in the list.");
                        }
                        outputCount++;
                    }
                    outputCount = 1;
                }
                else if(!input.equals("bye")) {
                    try {
                        throw new nonMatchException();
                    } catch(DukeException ignored) {
                    }
                }
            printSeparator();

        }while ( !inputString.equals("bye") );

        writeFile(taskArray,sumList);

        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();

    }

    public static void printSuccessfullyAddedMessage(Task t) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
    }

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

}
