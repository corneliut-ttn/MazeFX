package maze.generator;

/**
 * Created by Cornelius on 22.04.2015.
 */
public enum Directions {
    N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
    private final int bit;
    private final int x;
    private final int y;
    private Directions opposite;

    /**
     *  use the static initializer to resolve forward references
     */
    static {
        N.opposite = S;
        S.opposite = N;
        E.opposite = W;
        W.opposite = E;
    }

    private Directions(int bit, int x, int y) {
        this.bit = bit;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getBit() {
        return this.bit;
    }

    public Directions getOpposite() {
        return this.opposite;
    }

    public void setOpposite(Directions opposite) {
        this.opposite = opposite;
    }
};
