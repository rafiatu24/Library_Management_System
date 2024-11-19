module com.rafiatu.library_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    exports com.rafiatu.library_management_system.views;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.desktop;


    opens com.rafiatu.library_management_system to javafx.fxml;
    exports com.rafiatu.library_management_system;
}

