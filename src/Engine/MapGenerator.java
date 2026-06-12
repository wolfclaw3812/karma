package Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MapGenerator {
    private Random randomGen;
    private Map map = new Map();
    private int currNodeID = 100000;

    public MapGenerator(Random randomGen){
        this.randomGen = randomGen;
    }

    public void generateNodes(){
        int floors = 10; // TODO
        int minWidth = 3; // TODO
        int maxWidth = 5; // TODO

        // First Floor Generation
        skipNodeID(100);
        map.getMap().add(new ArrayList<Node>());
        int baseWidth = 3;
        for (int i = 0; i < baseWidth; i++){
            map.getMap().getFirst().add(Node.generateNode(randomGen, nextNodeID()));
        }

        // Post First Generation
        for (int i = 1; i < floors; i++) { // generates i floors
            skipNodeID(100);
            map.getMap().add(new ArrayList<Node>()); // create the ith floor
            int prevWidth = map.getMap().get(i-1).size();
            int currWidth = Math.max(minWidth, (int)(randomGen.nextDouble()*maxWidth+1)); // decide how wide this floor's going to be
            int lastParent = 0;

            for (int j = 0; j < currWidth; j++) { // add j nodes to floor i
                Node n = Node.generateNode(randomGen, nextNodeID());
/*
                int maxParents = (int) Math.ceil((double) currWidth / prevWidth);
                int numParents = randomGen.nextInt(maxParents)+1;
                for (int k = 0; k < maxParents; k++) {
                    n.addParent(map.get(i - 1).get(randomGen.nextInt(prevWidth))); // grab
                }
*/
                map.getMap().get(i).add(n);
            }
            connectNodes(randomGen, map.getMap().get(i-1), map.getMap().get(i));
        }

    }

    public void connectNodes(Random randomGen, ArrayList<Node> parentColumn, ArrayList<Node> childColumn) {
        if (parentColumn.isEmpty() || childColumn.isEmpty()) return;

        int numParents = parentColumn.size();
        int numChildren = childColumn.size();

        if (numChildren >= numParents) {
            // More children than parents: Every child gets 1 parent, some parents get multiple children
            for (int c = 0; c < numChildren; c++) {
                // Calculate an evenly distributed parent index using floating-point ratios
                int pIdx = (int) ((long) c * numParents / numChildren);
                childColumn.get(c).addParent(parentColumn.get(pIdx));
            }
        } else {
            // More parents than children: Every parent gets 1 child, some children get multiple parents
            for (int p = 0; p < numParents; p++) {
                // Calculate an evenly distributed child index
                int cIdx = (int) ((long) p * numChildren / numParents);
                childColumn.get(cIdx).addParent(parentColumn.get(p));
            }
        }

        int totalNodes = childColumn.size();
        double percent = 0.10 + (randomGen.nextDouble() * 0.30);
        int nodesToEnhance = Math.max((int) Math.ceil(totalNodes * percent), 1);
        nodesToEnhance = Math.min(nodesToEnhance, totalNodes);

        ArrayList<Integer> childIndices = new ArrayList<>();
        for (int i = 0; i < numChildren; i++) childIndices.add(i);
        Collections.shuffle(childIndices);

        int extrasAdded = 0;
        for (int childIdx : childIndices) {
            if (extrasAdded >= nodesToEnhance) break;

            Node child = childColumn.get(childIdx);

            int minParentIdx = Integer.MAX_VALUE;
            int maxParentIdx = Integer.MIN_VALUE;

            for (Node p : child.getParents()) {
                int pIdx = parentColumn.indexOf(p);
                if (pIdx < minParentIdx) minParentIdx = pIdx;
                if (pIdx > maxParentIdx) maxParentIdx = pIdx;
            }

            ArrayList<Node> safeExtraParents = new ArrayList<>();

            // Check immediately above the child's current parent block
            if (minParentIdx > 0) {
                Node potentialParentAbove = parentColumn.get(minParentIdx - 1);
                if (isConnectionOverlapFree(potentialParentAbove, child, parentColumn, childColumn, childIdx)) {
                    safeExtraParents.add(potentialParentAbove);
                }
            }
            // Check immediately below the child's current parent block
            if (maxParentIdx < numParents - 1) {
                Node potentialParentBelow = parentColumn.get(maxParentIdx + 1);
                if (isConnectionOverlapFree(potentialParentBelow, child, parentColumn, childColumn, childIdx)) {
                    safeExtraParents.add(potentialParentBelow);
                }
            }

            if (!safeExtraParents.isEmpty()) {
                Node extraParent = safeExtraParents.get(randomGen.nextInt(safeExtraParents.size()));
                child.addParent(extraParent);
                extrasAdded++;
            }
        }
    }

    /**
     * Cross-checks the proposed line against all existing lines to guarantee planar layout.
     */
    private boolean isConnectionOverlapFree(Node targetParent, Node targetChild,
                                            ArrayList<Node> parentColumn, ArrayList<Node> childColumn,
                                            int targetChildIdx) {
        int targetParentIdx = parentColumn.indexOf(targetParent);

        for (int c = 0; c < childColumn.size(); c++) {
            if (c == targetChildIdx) continue;

            Node otherChild = childColumn.get(c);
            for (Node otherParent : otherChild.getParents()) {
                int otherParentIdx = parentColumn.indexOf(otherParent);

                // Intersect check: crossing happens if vertical ordering is inverted
                if (targetChildIdx < c && targetParentIdx > otherParentIdx) return false;
                if (targetChildIdx > c && targetParentIdx < otherParentIdx) return false;
            }
        }
        return true;
    }

    private void runBaseBalancedConnection(ArrayList<Node> parentColumn, ArrayList<Node> childColumn) {
        int numParents = parentColumn.size();
        int numChildren = childColumn.size();
        int totalSlots = Math.max(numParents, numChildren);

        int baseParentsPerChild = totalSlots / numChildren;
        int extraSlots = totalSlots % numChildren;

        int[] parentCountsPerChild = new int[numChildren];
        for (int i = 0; i < numChildren; i++) {
            parentCountsPerChild[i] = baseParentsPerChild + (i < extraSlots ? 1 : 0);
            if (parentCountsPerChild[i] == 0) parentCountsPerChild[i] = 1;
        }

        int parentIdx = 0;
        for (int c = 0; c < numChildren; c++) {
            Node child = childColumn.get(c);
            int parentsNeeded = parentCountsPerChild[c];

            for (int p = 0; p < parentsNeeded; p++) {
                Node parent = parentColumn.get(parentIdx);
                child.addParent(parent);

                if (parentIdx < numParents - 1) {
                    int remainingChildrenSlots = numChildren - c - 1;
                    int remainingParents = numParents - parentIdx - 1;
                    if (p == parentsNeeded - 1 || remainingParents >= remainingChildrenSlots) {
                        parentIdx++;
                    }
                }
            }
        }
        while (parentIdx < numParents) {
            Node lastChild = childColumn.get(numChildren - 1);
            Node leftoverParent = parentColumn.get(parentIdx);
            if (!lastChild.getParents().contains(leftoverParent)) {
                lastChild.addParent(leftoverParent);
            }
            parentIdx++;
        }
    }

    public int nextNodeID(){
        return currNodeID++;
    }

    public void skipNodeID(int skip){
        currNodeID+=skip;
    }

    public void updateNodeID(int newNodeID){
        currNodeID = newNodeID;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getCurrNodeID() {
        return currNodeID;
    }

    public void setCurrNodeID(int currNodeID) {
        this.currNodeID = currNodeID;
    }
}
