package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String writeToFile() {
        String done="0";
        if (super.getDone()) done="1";
        String sentence="E | "+done+" | "+super.getName()+" | "+at;
        return sentence;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.trim() + ")";
    }

}
