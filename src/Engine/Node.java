package Engine;

import java.util.LinkedHashSet;
import java.util.Random;

public class Node {
    int type;
    int eventID;
    int nodeID;
    LinkedHashSet<Node> children = new LinkedHashSet<Node>();
    LinkedHashSet<Node> parents = new LinkedHashSet<Node>();

    public Node(){

    }

    public Node(int eventID, int nodeID){
        this.eventID = eventID;
        this.nodeID = nodeID;
    }

    private void readEvent(){
        // TODO
        /*
        Read the file that contains the event
        Read the event ID, read the event name
        Read the involved Actions in the event
         */
    }
    public LinkedHashSet<Node> getParents() {
        return parents;
    }

    public void addParent(Node parent) {
        this.parents.add(parent);
        parent.addChild(this);
    }

    public LinkedHashSet<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNodeID() {
        return nodeID;
    }

    public static Node generateNode(Random randomGen, int nodeID){
        return new Node(randomGen.nextInt(4), nodeID); // TODO pick from event list
    }

    @Override
    public String toString(){
        String str = "";
        str+="Engine.Node "+nodeID;
        str+="|EventID: "+eventID;
        str+="|Parents: ";
        for (Node node: parents){
            str += node.getNodeID() + " ";
        }
        return str;
    }

}
