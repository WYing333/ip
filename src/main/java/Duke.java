import java.util.Scanner;

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

        String line;
        System.out.print("Type something: ");
        line = in.nextLine();

        while ( !line.equals("bye") ) {
            if (!line.equals("list")) {
                userInput[inputCount] = line;
                inputCount++;
                System.out.println("Added:" + line);
            }

            if (line.equals("list")) {
                while (outputCount<=inputCount) {
                    System.out.println( outputCount + "." + userInput[outputCount-1] );
                    outputCount++;
                }
                outputCount=1;
            }

            System.out.println("____________________________________________________________");
            System.out.print("Type something: ");
            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");


    }
}
