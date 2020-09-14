package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done: ");
        this.isDone = true;
    }

    public void setDone(boolean value) {
        isDone=value;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getName() {
        return description;
    }

    public String writeToFile(){
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

}



