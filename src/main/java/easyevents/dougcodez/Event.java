package easyevents.dougcodez;

public abstract class Event {

    private boolean isAsync;
    private String name;

    public Event() {
        this(false);
    }

    public Event(boolean isAsync) {
        this.isAsync = isAsync;
    }

    public String getName() {
        if (name == null) {
            name = this.getClass().getSimpleName();
        }
        return name;
    }

    public boolean isAsync() {
        return isAsync;
    }
}