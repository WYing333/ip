import java.util.Scanner;

public class Duke {

    public static void printSeparator() {
        String separator="____________________________________________________________";
        System.out.println(separator);
    }

    public static void main(String[] args) {

        showWelcomeScreen();

        Scanner in = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int inputCount=0;
        int outputCount=1;

        System.out.print("Type something: ");
        String inputString = in.nextLine();

        while ( !inputString.equals("bye") ) {
            if ( !inputString.equals("list") && !inputString.contains("done ")) {
                if (inputString.contains("todo")) {
                    taskArray[inputCount]=new ToDo(inputString.replace("todo",""));
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskArray[inputCount]);
                    inputCount++;
                    System.out.println("Now you have"+inputCount+"tasks in the list.");
                    printSeparator();
                }

                else if (inputString.contains("deadline")) {
                    String[] inputDeadline=inputString.split("/");
                    taskArray[inputCount]=new Deadline(inputDeadline[0].replace("deadline",""),
                            inputDeadline[1].replace("by",""));
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskArray[inputCount]);
                    inputCount++;
                    System.out.println("Now you have"+inputCount+"tasks in the list.");
                    printSeparator();
                }

                else if (inputString.contains("event")) {
                    String[] inputEvent=inputString.split("/");
                    taskArray[inputCount]=new Event(inputEvent[0].replace("event",""),
                            inputEvent[1].replace("at",""));
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskArray[inputCount]);
                    inputCount++;
                    System.out.println("Now you have"+inputCount+"tasks in the list.");
                    printSeparator();
                }

                else {
                    taskArray[inputCount]= new Task(inputString);
                    inputCount++;
                    System.out.println("Added:" + inputString);
                }

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

            if (inputString.contains("done ") ) {
                String[] inputWords=inputString.split(" ");
                int doneNumber = Integer.parseInt(inputWords[1]);
                while (outputCount<=inputCount) {
                    if ( doneNumber == outputCount){
                        taskArray[outputCount-1].markAsDone();
                        System.out.println(taskArray[outputCount-1]);
                    }
                    outputCount++;
                }
                outputCount=1;
            }

            printSeparator();
            System.out.print("Type something: ");
            inputString = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();

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
