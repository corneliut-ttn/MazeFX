package maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import maze.generator.FileMazeGenerator;
import maze.model.Maze;
import maze.solver.ManualSolver;
import maze.solver.RecursiveSolver;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/maze.fxml"));
        primaryStage.setTitle("aMAZEing - A visual representation of a maze using JFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        /*
        RecursiveSolver rs=new RecursiveSolver(maze);
        rs.solve();*/
    }
}
