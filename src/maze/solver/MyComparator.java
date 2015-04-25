package maze.solver;

/**
 * Created by Cornelius on 24.04.2015.
 */
public class MyComparator implements java.util.Comparator<char[]>{

    private int referenceLength;

    public MyComparator(String reference) {
        super();
        this.referenceLength = reference.length();
    }

    public int compare(char[] c1, char[] c2) {
        int dist1 = Math.abs(c1.length - referenceLength);
        int dist2 = Math.abs(c2.length - referenceLength);

        return dist1 - dist2;
    }
}
