import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK_NUM = 100;
    public static Task[] taskArray = new Task[MAX_TASK_NUM];
    public static int taskCount=0;

    public static void printSeparator() {
        String separator="____________________________________________________________";
        System.out.println(separator);
    }

    public static void main(String[] args) {

        showWelcomeScreen();
        int inputCount=0;
        int outputCount=1;
        String inputString;
        String input;


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
                else if(!input.equals("bye")) {
                    try {
                        throw new nonMatchException();
                    } catch(DukeException ignored) {
                    }
                }
            printSeparator();
            //inputString = in.nextLine();

        }while ( !inputString.equals("bye") );

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
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printSeparator();
    }

}
