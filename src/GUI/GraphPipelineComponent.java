package GUI;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import Engine.Node;

public class GraphPipelineComponent extends ScrollPane {

    // --- Layout Customization Properties ---
    private double columnWidth = 180.0;
    private double rowHeight = 70.0;
    private double startX = 60.0;
    private double startY = 50.0;
    private double nodeRadius = 18.0;

    private final Pane canvas;

    public GraphPipelineComponent() {
        // Initialize the canvas layer
        this.canvas = new Pane();
        this.canvas.setStyle("-fx-background-color: #F4F6F9;");

        // Enforce horizontal scrolling rules (Rule #5)
        this.setContent(canvas);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setFitToHeight(true);
    }

    /**
     * Renders any arbitrary pre-structured pipeline data passed into it.
     * @param pipeline The ArrayList of ArrayLists containing nodes and parent data.
     */
    public void render(ArrayList<ArrayList<Node>> pipeline) {
        // Clear previous visual items to allow safe re-renders
        canvas.getChildren().clear();

        if (pipeline == null || pipeline.isEmpty()) return;

        Map<Node, GUINode> guiRegistry = new HashMap<>();

        // 2D structure to hold our visual representations sequentially
        ArrayList<ArrayList<GUINode>> visualPipeline = new ArrayList<>();

        // --- Pass 1: Wrap Logical Nodes and Compute Coordinates ---
        for (int col = 0; col < pipeline.size(); col++) {
            ArrayList<Node> logicalColumn = pipeline.get(col);
            ArrayList<GUINode> visualColumn = new ArrayList<>();

            for (int row = 0; row < logicalColumn.size(); row++) {
                Node logicalNode = logicalColumn.get(row);

                // Create visual wrapper
                GUINode guiNode = new GUINode(logicalNode);
                guiNode.setX(startX + (col * columnWidth)+stagger());
                guiNode.setY(startY + (row * rowHeight)+stagger());

                // Track internally for lines and rendering
                guiRegistry.put(logicalNode, guiNode);
                visualColumn.add(guiNode);

                // Build Visual UI Elements
                Circle nodeCircle = new Circle(guiNode.getX(), guiNode.getY(), nodeRadius);
                nodeCircle.setFill(Color.web("#34495E"));
                nodeCircle.setStroke(Color.web("#2C3E50"));
                nodeCircle.setStrokeWidth(2);

                Text text = new Text(guiNode.getX() - 8, guiNode.getY() + 5, guiNode.getName());
                text.setFill(Color.WHITE);
                text.setFont(Font.font("Arial", 12));

                canvas.getChildren().addAll(nodeCircle, text);
            }
            visualPipeline.add(visualColumn);
        }

        // --- Pass 2: Draw Connecting Lines Using the Wrapper Registry ---
        // Look up the logical child's parents, find their corresponding visual wrapper coordinates
        for (int col = 1; col < visualPipeline.size(); col++) {
            ArrayList<GUINode> visualColumn = visualPipeline.get(col);

            for (GUINode guiChild : visualColumn) {
                Node logicalChild = guiChild.getNode();

                for (Node logicalParent : logicalChild.getParents()) {
                    GUINode guiParent = guiRegistry.get(logicalParent);

                    if (guiParent != null) { // Safeguard to ensure parent exists visually
                        Line connectionLine = new Line(
                                guiParent.getX(), guiParent.getY(),
                                guiChild.getX(), guiChild.getY()
                        );
                        connectionLine.setStroke(Color.web("#95A5A6"));
                        connectionLine.setStrokeWidth(2.0);

                        // Render cleanly beneath node graphics
                        canvas.getChildren().add(0, connectionLine);
                    }
                }
            }
        }

        // --- Step 3: Set Canvas Bounds dynamically ---
        double requiredWidth = startX + (pipeline.size() * columnWidth) + startX;
        canvas.setMinWidth(requiredWidth);
        canvas.setPrefWidth(requiredWidth);

    }

    // --- Optional Layout Customization Setters ---
    public void setColumnWidth(double columnWidth) { this.columnWidth = columnWidth; }
    public void setRowHeight(double rowHeight) { this.rowHeight = rowHeight; }
    public void setNodeRadius(double nodeRadius) { this.nodeRadius = nodeRadius; }

    private int stagger(){
        return (int)(Math.random()*10)-5;
    }
}