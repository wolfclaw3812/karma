module karmic {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    opens GUI to javafx.graphics, javafx.fxml;

    exports GUI;
    exports Engine;
}
