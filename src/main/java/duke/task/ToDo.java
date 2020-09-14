package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

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
