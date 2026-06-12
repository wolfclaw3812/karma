package Event;

public class EventActionDialogue extends EventAction{
    public EventActionDialogue(){

    }

    public EventActionDialogue(String name, String content){
        super.actionName = name;
        super.actionContent = content;
    }

}
