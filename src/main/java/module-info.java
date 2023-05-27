module com.example.workappjx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
//    requires mysql.connector.java;

    opens com.example.workappjx to javafx.fxml;
    exports com.example.workappjx;
}