module com.brush_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.brush_app to javafx.fxml;
    exports com.brush_app;
}
