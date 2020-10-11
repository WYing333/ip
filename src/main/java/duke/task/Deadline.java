package duke.task;

import duke.DateParser;
import java.time.LocalDateTime;


public class Deadline extends Task {

    protected String by;
    public String time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        time = DateParser.parseDate(by.trim());
    }

    /**
     * Writes to the file in the specific format.
     * @return the formatted string of Deadline task that will be stored in the file.
     */
    public String writeToFile() {
        String done="0";
        if (super.getDone()) done="1";
        return "D | "+done+" | "+super.getName()+" | "+ time ;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

}
