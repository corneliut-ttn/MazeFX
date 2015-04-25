package maze.generator;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Cornelius on 24.04.2015.
 */
public class FileMazeGenerator {

    private int[] startCell;
    private int[] finishCell;
    private File file;
    private int height;
    private int width;
    private Integer[][] maze;

    public FileMazeGenerator(String filename) {
        this.startCell = new int[2];
        this.finishCell = new int[2];
        this.file = new File(filename);
        generateLabyrinth();
    }

    public Integer[][] getMaze() {
        return maze;
    }

    public File readFile() {
        Scanner keyboard = new Scanner(System.in);
        return new File(keyboard.nextLine());
    }

    public void generateLabyrinth(File file) {
        String maze[][] = new String[(int) Math.sqrt(file.length())][];
        int rows = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    maze[rows] = new String[line.split(" ").length];
                    maze[rows++] = line.split(" ");
                }
                br.close();
                this.height = rows;
                this.width = maze[0].length;
                this.maze = new Integer[rows][width];
                for (int i = 0; i < rows; i++)
                    for (int j = 0; j < maze[i].length; j++)
                        this.maze[i][j] = (int)(maze[i][j].charAt(0)-'0');
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.Please insert a new filename path");
            generateLabyrinth(readFile());
        }
    }

    public void generateLabyrinth() {
        generateLabyrinth(this.file);
    }

    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(maze[i][j] + " ");
            System.out.println();
        }
    }
}
