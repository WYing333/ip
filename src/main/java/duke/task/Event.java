package duke.task;

import duke.DateParser;
import java.time.LocalDateTime;


public class Event extends Task {

    protected String at;
    public LocalDateTime time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        time = DateParser.parseDate(at.trim());
    }

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
