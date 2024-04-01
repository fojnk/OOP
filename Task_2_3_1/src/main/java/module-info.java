module com.example.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task_2_3_1 to javafx.fxml;
    exports com.example.task_2_3_1;

    opens com.example.task_2_3_1.controllers to javafx.fxml;
    exports com.example.task_2_3_1.controllers;

    exports com.example.task_2_3_1.game;
    opens com.example.task_2_3_1.game to javafx.fxml;
}