package maze.model;

import maze.generator.RecursiveMazeGenerator;

import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * Created by Cornelius on 19.04.2015.
 */
public class Maze {

    final Integer WALL = 1;
    final Integer ROOM = 0;
    final Integer START = 2;
    final Integer FINISH = 4;
    final Integer PATH = 3;

    private Integer[][] maze;
    private int height;
    private int width;
    private int[] startCell;
    private int[] finishCell;


    public Maze(){
        System.out.println("I've been created");

    }
    public Maze(Integer[][] labyrinth) {
        this.maze = labyrinth;
        this.height = labyrinth.length;
        this.width = labyrinth[0].length;

        for(int i=0;i<this.height;i++)
            for(int j=0;j<this.width;j++){
                if(this.maze[i][j].equals(START)) {
                    startCell = new int[2];
                    startCell[0]=i;
                    startCell[1]=j;
                }
                if(this.maze[i][j].equals(FINISH)) {
                    finishCell = new int[2];
                    finishCell[0]=i;
                    finishCell[1]=j;
                }
            }
    }
    public Maze(int height,int width){
        setHeight(height);
        setWidth(width);
        startCell=new int[2];
        finishCell=new int[2];
        System.out.println("I've been created");
        System.out.println(this.toString());
        generateLabyrinth();
    }

    public void generateLabyrinth() {

        RecursiveMazeGenerator RMG = new RecursiveMazeGenerator(height, width);
        this.maze = RMG.parseToInteger();
        this.height = maze.length;
        this.width = maze[0].length;
        randomStartFinishCell();
    }

    public void randomStartFinishCell() {
        int position = randomValue(0, 4);
        switch (position) {
            case 0: {//S-NV F-SE
                startCell[0] = 0;
                startCell[1] = 0;
                finishCell[0] = height - 1;
                finishCell[1] = width - 1;
            }
            break;
            case 1: {//S-NE F-SV
                startCell[0] = 0;
                startCell[1] = width - 1;
                finishCell[0] = height - 1;
                finishCell[1] = 0;
            }
            break;
            case 2: {//S-SE F-NV
                startCell[0] = height - 1;
                startCell[1] = width - 1;
                finishCell[0] = 0;
                finishCell[1] = 0;
            }
            break;
            case 3: {//S-SV F-NE
                startCell[0] = height - 1;
                startCell[1] = 0;
                finishCell[0] = 0;
                finishCell[1] = width - 1;
            }
            break;
            default: {
            }
        }
        maze[startCell[0]][startCell[1]] = START;
        maze[finishCell[0]][finishCell[1]] = FINISH;
    }

    public int randomValue(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    @Override
    public String toString() {
        return "Maze{" +
                "height=" + height +
                ", width=" + width +
                '}';

    }

    public void drawMaze(){

        System.out.println("Start drawing a maze...");

    }

    public boolean isFreeAt(int x, int y) {//first true => 2nd || first false =>third
        return maze[x][y] == null ? ROOM == null : maze[x][y].equals(ROOM);
    }


    public void unsetPathAt(int[] cell) {
        maze[cell[0]][cell[1]] = ROOM;
    }

    public boolean isStartAt(int x, int y) {
        return maze[x][y] == null ? START == null : maze[x][y].equals(START);
    }

    public boolean isWallAt(int x, int y) {
        try {
            return maze[x][y] == null ? WALL == null : maze[x][y].equals(WALL);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't go there, you might fall");
            return true;
        }
    }

    public boolean isPathAt(int x, int y) {
        try {
            return maze[x][y] == null ? PATH == null : maze[x][y].equals(PATH);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't go there, you might fall");
            return false;
        }
    }

    public void setPathAt(int[] cell) {
        try {
            maze[cell[0]][cell[1]] = PATH;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Imposible to reach path");
        }
    }

    public boolean isFinishAt(int x, int y) {
        return maze[x][y] == null ? FINISH == null : maze[x][y].equals(FINISH);
    }

    public int[] getStartCell() {
        return startCell;
    }

    public int[] getFinishCell() {
        return finishCell;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Integer[][] getMaze() {
        return maze;
    }

    public void setMaze(Integer[][] maze) {
        this.maze = maze;
    }

    public Integer getWALL() {
        return WALL;
    }

    public Integer getROOM() {
        return ROOM;
    }

    public Integer getSTART() {
        return START;
    }

    public Integer getFINISH() {
        return FINISH;
    }

    public Integer getPATH() {
        return PATH;
    }

}
