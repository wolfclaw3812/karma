package Event;

abstract class EventAction {
    int actionID;
    int actionType;
    String actionName;
    String actionContent;

    public EventAction(){}
    public EventAction(int actionID){
        this.actionID = actionID;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    public String getActionContent() {
        return actionContent;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getActionID() {
        return actionID;
    }

    public void setActionID(int actionID) {
        this.actionID = actionID;
    }
}
