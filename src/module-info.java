module karmic {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    opens GUI to javafx.graphics, javafx.fxml;

    exports GUI;
    exports Engine;
    exports Combat;
    exports EventListener;
}
