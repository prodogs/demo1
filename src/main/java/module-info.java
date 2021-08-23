module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mongo.java.driver;

    opens com.investrics.Capability to javafx.fxml;
    exports com.investrics.Capability;
    exports com.investrics.Capability.BusinessObjects;
    opens com.investrics.Capability.BusinessObjects to javafx.fxml;
}