package duke;

public class emptyException extends DukeException{
        public emptyException() {
            System.out.println("The description of an action(exclude list) cannot be empty.");
            String separator="____________________________________________________________";
            System.out.println(separator);
        }
}
