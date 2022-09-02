package knights;

public class MirroredTorusTopology extends Topology {

    public MirroredTorusTopology(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Field map(Field f) {
        final int row = map(f.getRow(), getRows()); final int column = map(f.getColumn(), getColumns());
        return new Field(row, column);
    }

    private static int map(int x, int num) {
        if (num == 1) return 0; if (x < 0) x = -x; final int max = num - 1;
        return (x / max) % 2 == 0 ? x % max : max - x % max;
    }
}
