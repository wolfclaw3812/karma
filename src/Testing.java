import Engine.*;
import GUI.*;

import java.util.ArrayList;

public class Testing {
    public static void main(String[] args){
        Engine e = new Engine();
        e.generateMap();
        for (ArrayList<Node> floor : e.getCurrentMap().getMap()){
            for (Node node : floor){
                System.out.println(node.toString());
            }
        }
    }
}
