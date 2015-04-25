package maze.generator;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Cornelius on 22.04.2015.
 */
public class RecursiveMazeGenerator {

    private final int height;
    private final int width;
    private final int[][] maze;

    public RecursiveMazeGenerator(int width, int height) {
        System.out.println("Number of rooms for roseta code:" + height + " by " + width);
        this.height = height;
        this.width = width;
        maze = new int[this.height][this.width];
        generateMaze();
        //display();
    }

    private static boolean between(int value, int upper) {
        return (value >= 0) && (value < upper);
    }

    public String parseToString() {
        StringBuilder stringmaze = new StringBuilder();
        for (int i = 0; i < width; i++) {
            // draw the north edge
            if (i == 0) {
                for (int j = 0; j < height; j++) {
                    if (j == 0) {
                        stringmaze.append((maze[j][i] & 8) == 0 ? "0 " : "   ");
                    } else {
                        stringmaze.append((maze[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    }
                }
            } else {
                for (int j = 0; j < height; j++) {
                    if (j == 0) {
                        stringmaze.append((maze[j][i] & 1) == 0 ? "1 " : "0 ");
                    } else {
                        stringmaze.append((maze[j][i] & 1) == 0 ? "1 1 " : "1 0 ");
                    }
                }
                stringmaze.append("\n");
                // draw the west edge
                for (int j = 0; j < height; j++) {
                    if (j == 0) {
                        stringmaze.append((maze[j][i] & 8) == 0 ? "0 " : "0 0 ");
                    } else if (i == width - 1 && j == height - 1) {
                        stringmaze.append((maze[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    } else {
                        stringmaze.append((maze[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    }
                }
            }
            stringmaze.append("\n");
        }
        return stringmaze.toString();
    }

    public Integer[][] parseToInteger() {
        String aux = parseToString();
        System.out.println("How it is represented in the class :\n" + aux);
        Integer[][] integermaze = new Integer[2 * width - 1][2 * height - 1];
        int i = 0, j;
        for (String line : aux.split("\n")) {
            j = 0;
            for (String element : line.split(" ")) {
                integermaze[i][j++] = Integer.parseInt(element);
            }
            i++;
        }/*
        for( i=0;i<integermaze.length;i++){
            for( j=0;j<integermaze[i].length;j++)
                System.out.print(integermaze[i][j] + " ");
            System.out.println();
            }*/
        return integermaze;
    }

    private void generateMaze(int x, int y) {
        Directions[] dirs = Directions.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Directions dir : dirs) {
            int newX = x + dir.getX();
            int newY = y + dir.getY();
            if (between(newX, height) && between(newY, width)
                    && (maze[newX][newY] == 0)) {
                maze[x][y] |= dir.getBit();
                maze[newX][newY] |= dir.getOpposite().getBit();
                generateMaze(newX, newY);
            }
        }
    }

    public void generateMaze() {
        generateMaze(0,0);
    }

    public void display() {

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++)
                System.out.print(maze[i][i]);
            System.out.println();
        }

        System.out.println("The maze generatedby the recursive algorithm");
        for (int i = 0; i < width; i++) {
            // draw the north edge
            for (int j = 0; j < height; j++) {
                System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < height; j++) {
                System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < height; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }


}
