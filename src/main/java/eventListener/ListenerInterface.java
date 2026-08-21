package eventListener;
import event.Event;

public interface ListenerInterface {
    /**
     * Activates the event linked to this listener. Does not require knowledge of the type of listener. 
     * If remainingActivations is 0 or negative, nothing will happen.
     * @param event The event that activated this listener.
     */
    public void onActivate(Event event);

    /**
     * Reduces the remaining activation count of this listener by 1. 
     * This function must be manually implemented into every Listener class. 
     */
    public void reduceActivationCount();
}
