module com.example.itticketsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.itticketsystem to javafx.fxml;
    opens com.example.itticketsystem.model to javafx.base;
    exports com.example.itticketsystem;
}