package maze.solver;

import maze.model.Maze;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cornelius on 24.04.2015.
 */
public class RecursiveSolver {

    private Maze model;

    private boolean solved;
    private List<char[]> solutions = new ArrayList<char[]>();
    private char[] path;
    private int position = 0;

    public RecursiveSolver(String filename) {
        //this.model = new Maze(filename);

        this.solved = false;
    }

    public RecursiveSolver(Maze model) {
        this.model = model;
        this.solved = false;
    }

    public boolean isSolved() {
        return solved;
    }

    public void save() {
        System.out.println("Starting serializing...");
        try {
            writeIntLabyrinth();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RecursiveSolver load() {
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
                    new FileOutputStream(".maze/utils/maze.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.model);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in .utils/maze.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public RecursiveSolver readIntLabirynth() throws IOException {
        try {
            FileInputStream fileIn =
                    new FileInputStream("./src/utils/maze.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            return (RecursiveSolver) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    public boolean validCoordinates(int[] cell) {
        return (((cell[0] >= 0) && (cell[0] < this.model.getHeight())) && ((cell[1] >= 0) && (cell[1] < this.model.getWidth())));
    }

    public void savePath(char[] path, int startPos, int endPos) {
        char[] aux = new char[endPos - startPos + 1];
        int i = 0;
        System.out.println("Found path to the exit: ");
        for (int pos = startPos; pos <= endPos; pos++) {
            aux[i++] = path[pos];
        }
        solutions.add(aux);
    }

    public void solveRecursively(int[] cell, char direction) {
        if (!validCoordinates(cell)) {
            return;
        }
        path[position++] = direction;
        if (this.model.isFinishAt(cell[0], cell[1])) {

            System.out.println("Solved");
            savePath(path, 0, position - 1);
        }
        if ((this.model.isFreeAt(cell[0], cell[1])) || (this.model.isStartAt(cell[0], cell[1]))) {
            this.model.getMaze()[cell[0]][cell[1]] = 5;  // cell has been processed

            solveRecursively(down(cell), 'd');
            solveRecursively(right(cell), 'r');
            solveRecursively(up(cell), 'u');
            solveRecursively(left(cell), 'l');
            this.model.getMaze()[cell[0]][cell[1]] = this.model.getROOM();  // cell has been processed
        }
        position--;
    }

    public void solve() {
        path = new char[this.model.getHeight() * this.model.getWidth()];
        solveRecursively(model.getStartCell(),'S');
        Collections.sort(solutions,new MyComparator(""));
        for (char[] sol : solutions) {
            System.out.println(sol);
        }
    }

    public int[] down(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0] + 1;
        newCell[1] = cell[1];
        return newCell;
    }

    public int[] up(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0] - 1;
        newCell[1] = cell[1];
        return newCell;
    }

    public int[] right(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0];
        newCell[1] = cell[1] + 1;
        return newCell;
    }

    public int[] left(int[] cell) {
        int[] newCell = new int[2];
        newCell[0] = cell[0];
        newCell[1] = cell[1] - 1;
        return newCell;
    }

    public List<char[]> getSolutions(){
        return this.solutions;
    }
}
