package easyevents.dougcodez;

public enum EventPriority {


    HIGHEST(0), HIGH(1), NORMAL(2), LOW(3), LOWEST(4);

    private int priority;

    private EventPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
