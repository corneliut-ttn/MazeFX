package maze.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MazeController implements Initializable {

    Stage previousStage;

    @FXML
    public void setPreviousStage(Stage stage){
        this.previousStage=stage;
    }


    public void startGame(Stage playStage)throws Exception{
        Parent playRoot = FXMLLoader.load(getClass().getResource("/maze/view/playmaze.fxml"));
        playStage.setTitle("Keep calm and let's play a game!");
        playStage.setScene(new Scene(playRoot));
        playStage.show();
    }

    public void startDraw(Stage drawStage) throws Exception{
        Parent drawRoot = FXMLLoader.load(getClass().getResource("/maze/view/drawmaze.fxml"));
        drawStage.setTitle("Drawing one of the most exciting and incredible maze ever seen in this world");
        drawStage.setScene(new Scene(drawRoot));
        drawStage.show();
    }

    @FXML
    public void drawMaze(ActionEvent e)throws IOException{

        Stage drawStage=new Stage();

        try {
            startDraw(drawStage);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    public void playMaze(ActionEvent e)throws IOException{

        Stage playStage=new Stage();

        try {
            startGame(playStage);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
