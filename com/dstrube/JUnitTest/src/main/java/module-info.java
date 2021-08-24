module group.junittest {
    requires javafx.controls;
    requires javafx.fxml;


    opens group.junittest to javafx.fxml;
    exports group.junittest;
}