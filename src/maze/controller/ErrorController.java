package maze.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Cornelius on 20.04.2015.
 */
public class ErrorController {

    @FXML
    private Button errorButton;

    @FXML
    public void exit(ActionEvent actionEvent){
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
