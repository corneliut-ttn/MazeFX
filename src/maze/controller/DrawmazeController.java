package maze.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import maze.model.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Cornelius on 20.04.2015.
 */
public class DrawmazeController implements Initializable {

    private MazeSettingsController mazeSettingsController = new MazeSettingsController();

    private Maze drawMaze;
    private GridPane drawingMazePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private BorderPane drawBorderPane;
    @FXML
    private TextField mazeWidthTextField;
    @FXML
    private TextField mazeHeightTextField;
    @FXML
    private RadioButton startRadioButton;
    @FXML
    private RadioButton finishRadioButton;
    @FXML
    private RadioButton roomRadioButton;
    @FXML
    private RadioButton wallRadioButton;

    @FXML
    public int getNewMazeWidth() {
        return Integer.parseInt(this.mazeWidthTextField.getText());
    }

    @FXML
    public int getNewMazeHeight() {
        return Integer.parseInt(this.mazeHeightTextField.getText());
    }

    @FXML
    public void createMaze(ActionEvent e) {

        try {
            this.drawMaze = new Maze((getNewMazeHeight() / 2) + 1, (getNewMazeWidth() / 2) + 1);

            setupMazePane();
            setListeners();
        } catch (Exception exception) {
            Stage errorStage = new Stage();
            exception.printStackTrace();
            try {
                errorStageStart(errorStage);

            } catch (Exception exc1) {
                exc1.printStackTrace();
            }
        }
    }

    private void updateMazePane() {
        drawingMazePane = new GridPane();
        drawingMazePane.setPadding(new Insets(25, 0, 0, 30));
        drawingMazePane.setGridLinesVisible(true);
        createDrawingElements();
        this.drawBorderPane.setCenter(drawingMazePane);
    }

    private void setupMazePane() {

        drawingMazePane = new GridPane();
        drawingMazePane.setPadding(new Insets(25, 0, 0, 30));
        drawingMazePane.setGridLinesVisible(true);
        createElements();
        this.drawBorderPane.setCenter(drawingMazePane);
    }

    public void setListeners() {

//        for (int i = 0; i < drawMaze.getHeight(); i++) {
//            for (int j = 0; j < drawMaze.getWidth(); j++) {
//                final Node node = getNodeFromGridPane(drawingMazePane, i, j);
        for (final Node node : this.drawingMazePane.getChildren()) {
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (wallRadioButton.isSelected()) {
                        drawMaze.getMaze()[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = drawMaze.getWALL();
                        System.out.println(GridPane.getRowIndex(node) + " " + GridPane.getColumnIndex(node));
                    }
                    if (startRadioButton.isSelected()) {
                        drawMaze.getMaze()[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = drawMaze.getSTART();
                        System.out.println(GridPane.getRowIndex(node)+" "+GridPane.getColumnIndex(node));
                    }
                    if (finishRadioButton.isSelected()) {
                        drawMaze.getMaze()[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = drawMaze.getFINISH();
                        System.out.println(GridPane.getRowIndex(node)+" "+GridPane.getColumnIndex(node));
                    }
                }
            });
            updateMazePane();
        }
    }


    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private void createDrawingElements() {
        for (int i = 0; i < drawMaze.getHeight(); i++) {
            for (int j = 0; j < drawMaze.getWidth(); j++) {
                try {
                    drawingMazePane.add(createElement(i, j), i, j);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createElements() {

        for (int i = 0; i < drawMaze.getHeight(); i++) {
            for (int j = 0; j < drawMaze.getWidth(); j++) {
                drawingMazePane.add(createElement(), i, j);
                drawMaze.getMaze()[i][j] = drawMaze.getROOM();
            }
        }
    }

    private javafx.scene.shape.Shape createElement(int x, int y) throws InstantiationException, IllegalAccessException {
        javafx.scene.shape.Shape shape;
        if (drawMaze.isWallAt(x, y)) {
            shape = new Rectangle(mazeSettingsController.getMazeSettings().getWallSize(), mazeSettingsController.getMazeSettings().getWallSize());
            shape.setFill(mazeSettingsController.getMazeSettings().getWallFillColor());
            shape.setStroke(mazeSettingsController.getMazeSettings().getWallStrokeColor());
        } else if (drawMaze.isFreeAt(x, y)) {
            shape = new Rectangle(mazeSettingsController.getMazeSettings().getRoomSize(), mazeSettingsController.getMazeSettings().getRoomSize());
            ;
            shape.setFill(mazeSettingsController.getMazeSettings().getRoomFillColor());
            shape.setStroke(mazeSettingsController.getMazeSettings().getRoomStrokeColor());
        } else if (drawMaze.isPathAt(x, y)) {
            shape = new Circle(mazeSettingsController.getMazeSettings().getPathSize());
            shape.setFill(mazeSettingsController.getMazeSettings().getPathFillColor());
            shape.setStroke(mazeSettingsController.getMazeSettings().getPathStrokeColor());
        } else if (drawMaze.isStartAt(x, y)) {
            shape = new Circle(mazeSettingsController.getMazeSettings().getStartSize());
            shape.setFill(mazeSettingsController.getMazeSettings().getStartFillColor());
            shape.setStroke(mazeSettingsController.getMazeSettings().getStartStrokeColor());
        } else if (drawMaze.isFinishAt(x, y)) {
            shape = new Circle(mazeSettingsController.getMazeSettings().getFinishSize());
            shape.setFill(mazeSettingsController.getMazeSettings().getFinishFillColor());
            shape.setStroke(mazeSettingsController.getMazeSettings().getFinishStrokeColor());
        } else {
            shape = new Circle(5);
            shape.setFill(Color.RED);
            shape.setStroke(Color.BLACK);
        }

        return shape;

    }


    public void errorStageStart(Stage errorStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/maze/view/error.fxml"));
        errorStage.setTitle("User input error");
        errorStage.setScene(new Scene(root));
        errorStage.show();
    }

    private Rectangle createElement() {
        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.setStroke(Color.ORANGE);
        rectangle.setFill(Color.GREY);

        return rectangle;
    }

    @FXML
    public void playMaze(ActionEvent e) throws IOException {

        Stage playStage = new Stage();

        try {
            startGame(playStage);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void startGame(Stage playStage) throws Exception {
        Parent playRoot = FXMLLoader.load(getClass().getResource("/maze/view/playmaze.fxml"));
        playStage.setTitle("Keep calm and let's play a game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maze/view/playmaze.fxml"));
        PlaymazeController controller = new PlaymazeController();
        controller.setPlayMaze(drawMaze);
        loader.setController(controller);
        loader.setRoot(playRoot);

        playStage.setScene(new Scene(playRoot));
        playStage.show();
    }


    @FXML
    public void saveFile(ActionEvent e) {
        WritableImage snapshot = drawingMazePane.snapshot(new SnapshotParameters(), null);
        drawingMazePane.getChildren().add(new ImageView(snapshot));
        saveImage(snapshot);
        System.out.println(drawingMazePane.getChildren().size());
    }

    private void saveImage(WritableImage snapshot) {
        BufferedImage image;
        image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, new BufferedImage((int) drawingMazePane.getHeight(), (int) drawingMazePane.getWidth(), BufferedImage.TYPE_INT_ARGB));
        try {
            Graphics2D gd = (Graphics2D) image.getGraphics();
            gd.translate(drawingMazePane.getWidth(), drawingMazePane.getHeight());
            ImageIO.write(image, "jpg", new File("D:\\UAIC-ComputerScience\\UAIC\\AnII-Sem2\\PA-Java\\MazeFX\\utils\\image.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void mazeSettingsON(ActionEvent e)throws IOException {

        Stage settingsStage=new Stage();

        try {
            startSettings(settingsStage);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void startSettings(Stage settingsStage)throws Exception{
        Parent settingsStageRoot = FXMLLoader.load(getClass().getResource("/maze/view/mazesettings.fxml"));
        settingsStage.setTitle("Modify me!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/maze/view/mazesettings.fxml"));
        MazeSettingsController controller = this.mazeSettingsController;

        loader.setController(controller);
        loader.setRoot(settingsStageRoot);

        settingsStage.setScene(new Scene(settingsStageRoot));
        settingsStage.show();
    }
}
