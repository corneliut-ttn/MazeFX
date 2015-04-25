package maze.controller;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import maze.generator.FileMazeGenerator;
import maze.model.Maze;
import maze.solver.ManualSolver;
import maze.solver.RecursiveSolver;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Cornelius on 22.04.2015.
 */
public class PlaymazeController {

    private MazeSettingsController mazeSettingsController=new MazeSettingsController();

    private Maze playMaze;
    private GridPane playMazePane;
    private ManualSolver manualSolver;
    private RecursiveSolver recursiveSolver;

    @FXML
    private BorderPane playBorderPane;
    @FXML
    private Sphere sphere;
    @FXML
    private TextField mazeHeightTextField;
    @FXML
    private TextField mazeWidthTextField;

    //public PlaymazeController(Maze maze){
  //      playMaze=maze;
 //   }


    public Maze getPlayMaze() {
        return playMaze;
    }

    public void setPlayMaze(Maze playMaze) {
        this.playMaze = playMaze;
    }

    @FXML
    public int getNewMAzeWidth() {
        return Integer.parseInt(this.mazeWidthTextField.getText());
    }

    @FXML
    public int getNewMazeHeight() {
        return Integer.parseInt(this.mazeHeightTextField.getText());
    }

    @FXML
    public void createMaze(ActionEvent e) {

        try {
            this.playMaze = new Maze((getNewMazeHeight()/2) +1, (getNewMAzeWidth()/2)+1);
            this.manualSolver=new ManualSolver(playMaze);
            setupMazePane();

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

    @FXML
    public void solveRecursive(ActionEvent e){
        this.recursiveSolver=new RecursiveSolver(this.playMaze);
        this.recursiveSolver.solve();
        drawSolution();
        try {
            setupMazePane();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    public void drawSolution(){
        int[] current_cell=new int[2];
        for(char[] sol:this.recursiveSolver.getSolutions()){
            current_cell[0]=this.playMaze.getStartCell()[0];
            current_cell[1]=this.playMaze.getStartCell()[1];
            for(char move:sol){
                switch(move){
                    case 'l':{
                        current_cell[1]--;
                        this.playMaze.setPathAt(current_cell);
                    }break;
                    case 'r':{
                        current_cell[1]++;
                        this.playMaze.setPathAt(current_cell);
                    }break;
                    case 'd':{
                        current_cell[0]++;
                        this.playMaze.setPathAt(current_cell);
                    }break;
                    case 'u':{
                        current_cell[0]--;
                        this.playMaze.setPathAt(current_cell);
                    }break;
                    default: break;
                }
            }
            this.playMaze.getMaze()[this.playMaze.getStartCell()[0]][this.playMaze.getStartCell()[1]]=playMaze.getSTART();
            this.playMaze.getMaze()[this.playMaze.getFinishCell()[0]][this.playMaze.getFinishCell()[1]]=playMaze.getFINISH();
        }
        System.out.println(this.playMaze.toString());
        for(int i=0;i<this.playMaze.getHeight();i++){
            for (int j=0;j<this.playMaze.getWidth();j++){
                System.out.print(this.playMaze.getMaze()[i][j]);
            }
            System.out.println();
        }
    }

    public void errorStageStart(Stage errorStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/maze/view/error.fxml"));
        errorStage.setTitle("User input error");
        errorStage.setScene(new Scene(root));
        errorStage.show();
    }

    @FXML
    public void generateFromFile(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Maze from File");

        File file =fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            FileMazeGenerator fmg = new FileMazeGenerator(file.getPath());
            this.playMaze = new Maze(fmg.getMaze());

            try {
                setupMazePane();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
    }

    @FXML
    public void animate(ActionEvent e){
        Path path = new Path();
        path.getElements().add(new MoveTo(20, 20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);        pathTransition.setNode(sphere);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }

    private Shape createElement(int x,int y) throws InstantiationException,IllegalAccessException{
        Shape shape;
        if(playMaze.isWallAt(x, y)){
            shape= new Rectangle(mazeSettingsController.getMazeSettings().getWallSize(),mazeSettingsController.getMazeSettings().getWallSize()) ;
            shape.setFill(mazeSettingsController.getMazeSettings().getWallFillColor());
            shape.setStroke(mazeSettingsController.getMazeSettings().getWallStrokeColor());
        }
        else
            if(playMaze.isFreeAt(x, y)){
                shape= new Rectangle(mazeSettingsController.getMazeSettings().getRoomSize(),mazeSettingsController.getMazeSettings().getRoomSize()) ; ;
                shape.setFill(mazeSettingsController.getMazeSettings().getRoomFillColor());
                shape.setStroke(mazeSettingsController.getMazeSettings().getRoomStrokeColor());
            }
            else
                if(playMaze.isPathAt(x, y)){
                    shape= new Circle(mazeSettingsController.getMazeSettings().getPathSize()) ;
                    shape.setFill(mazeSettingsController.getMazeSettings().getPathFillColor());
                    shape.setStroke(mazeSettingsController.getMazeSettings().getPathStrokeColor());
                }
                else
                    if(playMaze.isStartAt(x, y)){
                        shape= new Circle(mazeSettingsController.getMazeSettings().getStartSize()) ;
                        shape.setFill(mazeSettingsController.getMazeSettings().getStartFillColor());
                        shape.setStroke(mazeSettingsController.getMazeSettings().getStartStrokeColor());
                    }
                    else
                        if(playMaze.isFinishAt(x,y)){
                            shape= new Circle(mazeSettingsController.getMazeSettings().getFinishSize());
                            shape.setFill(mazeSettingsController.getMazeSettings().getFinishFillColor());
                            shape.setStroke(mazeSettingsController.getMazeSettings().getFinishStrokeColor());
                        }
        else {
                            shape = new Circle(5);
                            shape.setFill(Color.RED);
                            shape.setStroke(Color.BLACK);
                        }

        return shape;

    }

    private void setupMazePane()throws InstantiationException,IllegalAccessException {

        playMazePane = new GridPane();
        playMazePane.setPadding(new Insets(25, 0, 0, 30));

        playMazePane.setGridLinesVisible(true);

        createElements();

        this.playBorderPane.setCenter(playMazePane);

    }

    private void createElements()throws InstantiationException,IllegalAccessException{

        for (int i = 0; i < playMaze.getHeight(); i++) {
            for (int j = 0; j < playMaze.getWidth(); j++) {
                playMazePane.add(createElement(i,j),i,j);
            }
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

    @FXML
    public void saveFile(ActionEvent e){
        WritableImage snapshot = playBorderPane.snapshot(new SnapshotParameters(), null);
        playBorderPane.getChildren().add(new ImageView(snapshot));
        saveImage(snapshot);
        System.out.println(playBorderPane.getChildren().size());
    }

    private void saveImage(WritableImage snapshot) {
        BufferedImage image;
        image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot,new BufferedImage((int)playBorderPane.getHeight(),(int)playBorderPane.getWidth(), BufferedImage.TYPE_INT_ARGB));
        try {
            Graphics2D gd = (Graphics2D) image.getGraphics();
            gd.translate(playBorderPane.getWidth(), playBorderPane.getHeight());
            ImageIO.write(image, "JPG",new File("D:\\UAIC-ComputerScience\\UAIC\\AnII-Sem2\\PA-Java\\MazeFX\\utils\\image.jpg"));
        } catch (IOException ex) {
           ex.printStackTrace();
        };
    }

}
