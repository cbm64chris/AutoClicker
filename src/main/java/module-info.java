module fluffyluffs.autoclicker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.fluffyluffs to javafx.fxml;
    exports com.fluffyluffs;
}
