package GUI;
import Engine.Node;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class GUINode {
    private String name;
    private int type;
    private Node node;
    private LinkedHashSet<Node> parents;
    private LinkedHashSet<Node> children;

    // UI tracking properties
    private double x;
    private double y;

    public GUINode(Node node) {
        this.node = node;
        this.parents = node.getParents();
        this.children = node.getChildren();
        this.type = node.getType();
        switch(type){
            case 1: this.name = "bounty";break;
            case 2: this.name = "conflict";break;
            case 3: this.name = "domain";break;
            default:this.name = "error";
        }
    }
    public LinkedHashSet<Node> getParents() { return node.getParents(); }
    public String getName() { return name; }

    public Node getNode(){ return this.node; }
    // Getters and setters for layout coordinates
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
}
