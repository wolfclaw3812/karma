package event;

import java.util.ArrayList;

public class MapEvent {
    ArrayList<EventAction> actions = new ArrayList<EventAction>();
    public MapEvent(int eventID){

    }

    public void addEventAction(EventAction eventAction){
        actions.add(eventAction);
    }
}
