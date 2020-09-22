package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the specific task.
     * @return tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the specific task as Done and prints the message to notify the users.
     */
    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done: ");
        this.isDone = true;
    }

    /**
     * Set the specific task as Done.
     * @param value the status of the specific task.
     */
    public void setDone(boolean value) {
        isDone=value;
    }

    /**
     * Gets the status of the specific task.
     * @return true or false.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Gets the description of the specific task.
     * @return String which is the description of the specific task.
     */
    public String getName() {
        return description;
    }

    /**
     * Writes to the file in the specific format.
     * @return the formatted string of task that will be stored in the file.
     */
    public String writeToFile(){
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

}



