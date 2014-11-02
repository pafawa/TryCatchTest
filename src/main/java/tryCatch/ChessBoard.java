package tryCatch;

import tryCatch.figure.ChessFigure;

import java.util.*;

/**
 * Created by pfarid on 28/10/14.
 */


public class ChessBoard {

    public static final String COLUMN_SEPARATOR = "|";
    public static final String EMPTY_FIELD = " ";
    public static final String END_LINE = "\n";

    private Set<Position> available = new LinkedHashSet<Position>();
    private  Map<Position, ChessFigure> taken = new HashMap<Position, ChessFigure>();

    int colNum, rowNum;
    private int[][] board;

    public ChessBoard(int[][] board) {
        this.board = board;

        colNum = board.length;
        rowNum = board[0].length;

        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                available.add(new Position(j, i));
            }
        }

    }

    public Iterator<Position> getAvailablePosIterator() {
        return available.iterator();
    }


    public void placeFigure(Position position, ChessFigure chessFigure) throws PositionsInCollisionException {
        markReachablePositions(chessFigure, position);
        markPosAsNotAvailable(position);
        taken.put(position, chessFigure);
    }

    public void markPosAsNotAvailable(Position position) throws PositionsInCollisionException {
        if(taken.get(position) != null) {
            throw new PositionsInCollisionException();
        }

        available.remove(position);

    }

    public boolean isFeasible(Position position) {
        return position.getColumn() < colNum && position.getColumn() >= 0 && position.getRow() < rowNum &&
                position.getRow() >= 0;
    }


    @Override
    public String toString() {

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < rowNum; i++) {

            strBuilder.append(COLUMN_SEPARATOR);

            for (int j = 0; j < colNum; j++) {
                ChessFigure chessFigure = taken.get(new Position(j, i));
                strBuilder.append((chessFigure != null ? chessFigure : EMPTY_FIELD));
                strBuilder.append(COLUMN_SEPARATOR);
            }

            strBuilder.append(END_LINE);

        }

        return strBuilder.toString();

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ChessBoard clonedChessBoard =  new ChessBoard(board);
        clonedChessBoard.available = new LinkedHashSet<Position>(available);
        clonedChessBoard.taken = new HashMap<Position, ChessFigure>(taken);

        return clonedChessBoard;
    }

    private void markReachablePositions(ChessFigure chessFigure, Position position) throws PositionsInCollisionException {
        chessFigure.markReachablePositions(this, position);

    }
}
