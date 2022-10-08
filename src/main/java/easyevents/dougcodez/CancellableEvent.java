package easyevents.dougcodez;

public class CancellableEvent extends Event {
    private boolean isCancelled;

    public CancellableEvent() {
        this(false);
    }

    public CancellableEvent(boolean isAsync) {
        super(isAsync);
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}