package fr.univlyon1.stratificationdatalog.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutController {
    @FXML
    private Button btnClose;

    @FXML
    void handleClose(ActionEvent event) {
        Stage oldStage = (Stage) btnClose.getScene().getWindow();
        oldStage.close();
    }

}
