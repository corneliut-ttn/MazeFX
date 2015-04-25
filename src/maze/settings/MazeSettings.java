package maze.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 *
 * Created by Cornelius on 23.04.2015.
 */
public class MazeSettings {

    private static MazeSettings ourInstance = new MazeSettings();

    public static MazeSettings getInstance() {
        return ourInstance;
    }

    private MazeSettings() {
        basicSettings();
        System.out.println(this.toString());
    }

    private double wallSize;
    private double roomSize;
    private double startSize;
    private double finishSize;
    private double pathSize;

    private Color wallFillColor;
    private Color roomFillColor;
    private Color startFillColor;
    private Color finishFillColor;
    private Color pathFillColor;

    private Color wallStrokeColor;
    private Color roomStrokeColor;
    private Color startStrokeColor;
    private Color finishStrokeColor;
    private Color pathStrokeColor;

    public void basicSettings() {
        wallBasicSettings();
        roomBasicSettings();
        startBasicSettings();
        finishBasicSettings();
        pathBasicSettings();
    }

    public void wallBasicSettings() {
        wallSize = 15;
        wallStrokeColor = Color.BLACK;
        wallFillColor = Color.DARKGRAY;
    }

    public void roomBasicSettings() {
        roomSize = 15;
        roomStrokeColor = Color.DARKBLUE;
        roomFillColor = Color.LIGHTCYAN;
    }

    public void startBasicSettings() {
        startSize = 7;
        startStrokeColor = Color.ALICEBLUE;
        startFillColor = Color.GREEN;
    }

    public void finishBasicSettings() {
        finishSize = 7;
        finishStrokeColor = Color.DARKOLIVEGREEN;
        finishFillColor = Color.GOLDENROD;
    }

    public void pathBasicSettings() {
        pathSize = 7 ;
        pathStrokeColor = Color.ANTIQUEWHITE;
        pathFillColor = Color.CORNFLOWERBLUE;
    }

    public double getWallSize() {
        return wallSize;
    }

    public void setWallSize(double wallSize) {
        this.wallSize = wallSize;
    }

    public double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(double roomSize) {
        this.roomSize = roomSize;
    }

    public double getStartSize() {
        return startSize;
    }

    public void setStartSize(double startSize) {
        this.startSize = startSize;
    }

    public double getFinishSize() {
        return finishSize;
    }

    public void setFinishSize(double finnishSize) {
        this.finishSize = finnishSize;
    }

    public double getPathSize() {
        return pathSize;
    }

    public void setPathSize(double pathSize) {
        this.pathSize = pathSize;
    }

    public Color getWallFillColor() {
        return wallFillColor;
    }

    public void setWallFillColor(Color wallFillColor) {
        this.wallFillColor = wallFillColor;
    }

    public Color getRoomFillColor() {
        return roomFillColor;
    }

    public void setRoomFillColor(Color roomFillColor) {
        this.roomFillColor = roomFillColor;
    }

    public Color getStartFillColor() {
        return startFillColor;
    }

    public void setStartFillColor(Color startFillColor) {
        this.startFillColor = startFillColor;
    }

    public Color getFinishFillColor() {
        return finishFillColor;
    }

    public void setFinishFillColor(Color finishFillColor) {
        this.finishFillColor = finishFillColor;
    }

    public Color getPathFillColor() {
        return pathFillColor;
    }

    public void setPathFillColor(Color pathFillColor) {
        this.pathFillColor = pathFillColor;
    }

    public Color getWallStrokeColor() {
        return wallStrokeColor;
    }

    public void setWallStrokeColor(Color wallStrokeColor) {
        this.wallStrokeColor = wallStrokeColor;
    }

    public Color getRoomStrokeColor() {
        return roomStrokeColor;
    }

    public void setRoomStrokeColor(Color roomStrokeColor) {
        this.roomStrokeColor = roomStrokeColor;
    }

    public Color getStartStrokeColor() {
        return startStrokeColor;
    }

    public void setStartStrokeColor(Color startStrokeColor) {
        this.startStrokeColor = startStrokeColor;
    }

    public Color getFinishStrokeColor() {
        return finishStrokeColor;
    }

    public void setFinishStrokeColor(Color finishStrokeColor) {
        this.finishStrokeColor = finishStrokeColor;
    }

    public Color getPathStrokeColor() {
        return pathStrokeColor;
    }

    public void setPathStrokeColor(Color pathStrokeColor) {
        this.pathStrokeColor = pathStrokeColor;
    }

    @Override
    public String toString() {
        return "MazeSettings{" +
                ", wallSize=" + wallSize +
                ", roomSize=" + roomSize +
                ", startSize=" + startSize +
                ", finishSize=" + finishSize +
                ", pathSize=" + pathSize +
                ", wallFillColor=" + wallFillColor +
                ", roomFillColor=" + roomFillColor +
                ", startFillColor=" + startFillColor +
                ", finishFillColor=" + finishFillColor +
                ", pathFillColor=" + pathFillColor +
                ", wallStrokeColor=" + wallStrokeColor +
                ", roomStrokeColor=" + roomStrokeColor +
                ", startStrokeColor=" + startStrokeColor +
                ", finishStrokeColor=" + finishStrokeColor +
                ", pathStrokeColor=" + pathStrokeColor +
                '}';
    }
}
