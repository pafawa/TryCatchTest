package tryCatch;

/**
 * Created by pfarid on 28/10/14.
 */
public class Position {

    private final int column;

    private final int row;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public int hashCode() {
        return 100 * column + row;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (!(o instanceof Position))
            return false;

        Position po = (Position) o;

        return po.column == this.column && po.row == this.row;
    }

    @Override
    public String toString() {
        return "(c:" + column + ",r:" + row + ")";
    }
}
