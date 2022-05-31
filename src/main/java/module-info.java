module group2.monopoly.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires json;

    opens group2.monopoly.frontend to javafx.fxml;
    exports group2.monopoly.frontend;
}