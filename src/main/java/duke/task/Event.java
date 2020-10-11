package duke.task;

import duke.DateParser;
import java.time.LocalDateTime;


public class Event extends Task {

    protected String at;
    public String time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        time = DateParser.parseDate(at.trim());
    }

    /**
     * Writes to the file in the specific format.
     * @return the formatted string of Event task that will be stored in the file.
     */
    public String writeToFile() {
        String done="0";
        if (super.getDone()) done="1";
        return "E | "+done+" | "+super.getName()+" | "+ time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

}
