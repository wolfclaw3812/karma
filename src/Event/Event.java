package Event;

import java.util.ArrayList;

public class Event {
    ArrayList<EventAction> actions = new ArrayList<EventAction>();
    public Event(int eventID){

    }

    public void addEventAction(EventAction eventAction){
        actions.add(eventAction);
    }
}
