public class Task {
    protected String discription;
    protected boolean isDone;

    public Task(String discription) {
        this.discription = discription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done: ");
        this.isDone = true;
        System.out.println("[" + this.getStatusIcon() + "]" + this.discription);

    }
}



