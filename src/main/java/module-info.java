module com.gulukal.europaceagtaskgu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gulukal.europaceagtaskgu to javafx.fxml;
    exports com.gulukal.europaceagtaskgu;
}