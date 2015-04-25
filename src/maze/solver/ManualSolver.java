package maze.solver;

import maze.model.Maze;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Cornelius on 24.04.2015.
 */
public class ManualSolver {

    private Maze model;
    private boolean solved;
    private Map<Integer, Integer> solvingPath;
    private int[] startCell;

    /**
     * Constructor with no parameters
     */
    public ManualSolver() {
        this.model = new Maze();
        this.solved = false;
        this.startCell = new int[2];
    }

    /**
     * Constructor with one parameter
     *
     * @param model=the labyrinth model
     */
    public ManualSolver(Maze model) {
        this.model = model;
        this.solved = false;
        this.startCell = new int[2];
    }

    public boolean isSolved() {
        return solved;
    }

    public int[] nextCellToExplore(char move, int[] previousCell) {
        switch (move) {
            case 'u': {
                return up(previousCell);
            }
            case 'r': {
                return right(previousCell);
            }
            case 'd': {
                return down(previousCell);
            }
            case 'l': {
                return left(previousCell);
            }
            default: {
                System.out.println("Wrong move;try again");
                return null;
            }
        }
    }

    public char readNextMove() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Insert new move:");
        return keyboard.next().charAt(0);
    }

    public void addPath(int[] cell) {
        solvingPath.put(cell[0], cell[1]);
        this.model.setPathAt(cell);
    }

    public void removePath(int[] cell) {
        if (cell[0] == this.model.getStartCell()[0] && cell[1] == this.model.getStartCell()[1]) return;
        solvingPath.remove(cell[0], cell[1]);
        this.model.unsetPathAt(cell);
    }

    public void solve() {
        this.startCell = this.model.getStartCell();
        this.solvingPath = new HashMap<Integer, Integer>();
        solvingPath.put(startCell[0], startCell[1]);
        int[] previousCell = new int[2];
        previousCell[0] = startCell[0];
        previousCell[1] = startCell[1];
        while (!isSolved()) {
            int[] currentCell = nextCellToExplore(readNextMove(), previousCell);
            if (currentCell != null) {
                if (currentCell[0] == this.model.getFinishCell()[0] && currentCell[1] == this.model.getFinishCell()[1]) {
                    solved = true;
                    continue;
                }
                if (solvingPath.containsKey(currentCell[0]) && (solvingPath.get(currentCell[0]) == currentCell[1])) {
                    removePath(currentCell);
                } else {
                    addPath(currentCell);
                }
                show();
                previousCell[0] = currentCell[0];
                previousCell[1] = currentCell[1];

            } else System.out.println("Wrong command ,try again");
        }
        System.out.println("Congratulations:You have finished the labyrinth. Zeus would be proud!");
        save();

    }

    public int[] down(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0] + 1;
        newCell[1] = previousCell[1];
        if (newCell[0] > this.model.getHeight()) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    public int[] up(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0] - 1;
        newCell[1] = previousCell[1];
        if (newCell[0] < 0 || newCell[1] < 0) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    public int[] right(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0];
        newCell[1] = previousCell[1] + 1;
        if (newCell[1] > this.model.getHeight()) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    public int[] left(int[] previousCell) {
        int[] newCell = new int[2];
        newCell[0] = previousCell[0];
        newCell[1] = previousCell[1] - 1;
        if (newCell[1] < 0) {
            System.out.println("Maze row out of bound");
            return null;
        }
        if (this.model.isWallAt(newCell[0], newCell[1])) {
            System.out.println("You can't go there, there is a wall ahead !");
            return null;
        } else return newCell;
    }

    public void save() {
        System.out.println("Starting serializing...");
        try {
            writeIntLabyrinth();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ManualSolver load() {
        System.out.println("Starting deserializing...");
        try {
            return readIntLabirynth();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeIntLabyrinth() throws IOException {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("./src/utils/manualSolver.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.model);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in ./src/utils/manualSolver.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public ManualSolver readIntLabirynth() throws IOException {
        try {
            FileInputStream fileIn =
                    new FileInputStream("./src/com/labyrinth/resources/integerlabyrinthsolver.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            return (ManualSolver) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    public void show(){
        for(int i=0;i<this.model.getHeight();i++){
            for(int j=0;j<this.model.getWidth();j++){
                System.out.print(this.model.getMaze()[i][j]+" ");
            }
            System.out.println();
        }
    }
}
