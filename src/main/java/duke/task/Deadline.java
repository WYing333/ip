package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String writeToFile() {
        String done="0";
        if (super.getDone()) done="1";
        return "D | "+done+" | "+super.getName()+" | "+by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.trim() + ")";
    }

}
