import java.util.Scanner;
//import java.util.Arrays;



public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);

        String[] userInput = new String[100];
        int inputCount=0;
        int outputCount=1;
        int doneNumber=-1;
        String[] isDone = new String[100];

        String inputString;
        System.out.print("Type something: ");
        inputString = in.nextLine();

        while ( !inputString.equals("bye") ) {
            if ( !inputString.equals("list") && !inputString.contains("done")) {
                userInput[inputCount] = inputString;
                isDone[inputCount] = "✗";
                inputCount++;
                System.out.println("Added:" + inputString);
            }

            if (inputString.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                while (outputCount<=inputCount) {
                    System.out.println( outputCount + "." + "[" + isDone[outputCount-1] + "]" + userInput[outputCount-1] );
                    outputCount++;
                }
                outputCount=1;
            }

            if (inputString.contains("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                doneNumber = Integer.parseInt( inputString.substring(5) );
                while (outputCount<=inputCount) {
                    if ( doneNumber == outputCount){
                        isDone[outputCount-1] = "✓";
                        System.out.println("[" + isDone[outputCount-1] + "]" + userInput[outputCount-1]);
                    }
                    outputCount++;
                }
                outputCount=1;
            }

            System.out.println("____________________________________________________________");
            System.out.print("Type something: ");
            inputString = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");


    }
}
