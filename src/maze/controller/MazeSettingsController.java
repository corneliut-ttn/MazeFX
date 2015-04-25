package maze.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import maze.settings.MazeSettings;

/**
 * Created by Cornelius on 24.04.2015.
 */
public class MazeSettingsController {

    private MazeSettings mazeSettings=MazeSettings.getInstance();

    public MazeSettings getMazeSettings(){
        return this.mazeSettings;
    }

    @FXML
    private ColorPicker wallFillColorPicker;
    @FXML
    private ColorPicker wallStrokeColorPicker;
    @FXML
    private ColorPicker roomFillColorPicker;
    @FXML
    private ColorPicker roomStrokeColorPicker;
    @FXML
    private ColorPicker startFillColorPicker;
    @FXML
    private ColorPicker startStrokeColorPicker;
    @FXML
    private ColorPicker finishFillColorPicker;
    @FXML
    private ColorPicker finishStrokeColorPicker;
    @FXML
    private ColorPicker pathFillColorPicker;
    @FXML
    private ColorPicker pathStrokeColorPicker;

    @FXML
    public void setColors(ActionEvent e){
        System.out.println("Colors changed");
        setWallFillColor(e);
        setWallStrokeColor(e);
        setRoomFillColor(e);
        setRoomStrokeColor(e);
        setStartFillColor(e);
        setStartStrokeColor(e);
        setFinishFillColor(e);
        setFinishStrokeColor(e);
        setPathFillColor(e);
        setPathStrokeColor(e);
        System.out.println(this.toString());
    }

    @FXML
    public void setWallFillColor(ActionEvent e) {
        this.mazeSettings.setWallFillColor(wallFillColorPicker.getValue());
    }

    @FXML
    public void setRoomFillColor(ActionEvent e) {
        this.mazeSettings.setRoomFillColor(roomFillColorPicker.getValue());
    }

    @FXML
    public void setStartFillColor(ActionEvent e) {
        this.mazeSettings.setStartFillColor(startFillColorPicker.getValue());
    }

    @FXML
    public void setFinishFillColor(ActionEvent e) {
        this.mazeSettings.setFinishFillColor(finishFillColorPicker.getValue());
    }

    @FXML
    public void setPathFillColor(ActionEvent e) {
        this.mazeSettings.setPathFillColor(pathFillColorPicker.getValue());
    }

    @FXML
    public void setWallStrokeColor(ActionEvent e) {
        this.mazeSettings.setWallStrokeColor(wallStrokeColorPicker.getValue());
    }

    @FXML
    public void setRoomStrokeColor(ActionEvent e) {
        this.mazeSettings.setRoomStrokeColor(roomStrokeColorPicker.getValue());
    }

    @FXML
    public void setStartStrokeColor(ActionEvent e) {
        this.mazeSettings.setStartStrokeColor(startStrokeColorPicker.getValue());
    }

    @FXML
    public void setFinishStrokeColor(ActionEvent e) {
        this.mazeSettings.setFinishStrokeColor(finishStrokeColorPicker.getValue());
    }

    @FXML
    public void setPathStrokeColor(ActionEvent e) {
        this.mazeSettings.setPathStrokeColor(pathStrokeColorPicker.getValue());
    }

}
