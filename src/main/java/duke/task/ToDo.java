package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Writes to the file in the specific format.
     * @return the formatted string of ToDo task that will be stored in the file.
     */
    public String writeToFile() {
        String done="0";
        if (super.getDone()) done="1";
        return "T | "+done+" | "+super.getName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
