package Engine;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Node>> map = new ArrayList<ArrayList<Node>>();
    public Map(ArrayList<ArrayList<Node>> map){
        this.map = map;
    }
    public Map(){}

    public ArrayList<ArrayList<Node>> getMap() {
        return map;
    }

    public void setMap(ArrayList<ArrayList<Node>> map) {
        this.map = map;
    }
}
