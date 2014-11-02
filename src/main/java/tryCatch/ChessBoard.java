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
    int colNum, rowNum;
    /**
     * All available positions
     */
    private Set<Position> available = new LinkedHashSet<Position>();
    /**
     * Already taken positions
     */
    private Map<Position, ChessFigure> taken = new HashMap<Position, ChessFigure>();
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

    /**
     * Copy constructor
     *
     * @param chessBoard
     */

    public ChessBoard(ChessBoard chessBoard) {
        this.board = chessBoard.board;
        this.rowNum = chessBoard.rowNum;
        this.colNum = chessBoard.colNum;

        this.available = new LinkedHashSet<Position>(chessBoard.available);
        this.taken = new HashMap<Position, ChessFigure>(chessBoard.taken);
    }

    /**
     * @return Iterator for available positions
     */
    public Iterator<Position> getAvailablePosIterator() {
        return available.iterator();
    }

    /**
     * Method tries to place the  chess piece on  the position, and mark all threaten positions
     * In case of failure  - threaten position is already taken by another piece, PositionsInCollisionException is
     * thrown
     *
     * @param position    position on which figure should placed
     * @param chessFigure chess figure to placed
     * @throws PositionsInCollisionException at least one threaten position is taken by another piece
     */
    public void placeFigureAndMarkThreaten(Position position, ChessFigure chessFigure) throws PositionsInCollisionException {
        markThreatenPositions(chessFigure, position);
        placeFigure(position, chessFigure);
    }

    /**
     * Places the chess piece on the position , is the position is taken PositionsInCollisionException is thrown
     *
     * @param position
     * @param chessFigure
     * @throws PositionsInCollisionException
     */
    private void placeFigure(Position position, ChessFigure chessFigure) throws PositionsInCollisionException {
        markPosAsNotAvailable(position);
        taken.put(position, chessFigure);
    }

    /**
     * Marks the position as not available , if its already taken PositionsInCollisionException is thrown
     *
     * @param position
     * @throws PositionsInCollisionException
     */
    public void markPosAsNotAvailable(Position position) throws PositionsInCollisionException {
        if (taken.get(position) != null) {
            throw new PositionsInCollisionException();
        }

        available.remove(position);

    }

    /**
     * Checks if the position exists on  the board.
     *
     * @param position
     * @return true is exists, false otherwise
     */
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
    public boolean equals(Object obj) {
        if (!(obj instanceof ChessBoard))
            return false;

        ChessBoard chessBoard = (ChessBoard) obj;

        if (chessBoard.rowNum != this.rowNum || chessBoard.colNum != this.colNum) {
            return false;
        }

        if (chessBoard.taken.size() != this.taken.size()) {
            return false;
        }

        for (Position pos : chessBoard.taken.keySet()) {
            if (!chessBoard.taken.get(pos).equals(this.taken.get(pos))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;

        for (Position pos : taken.keySet()) {
            hashCode += pos.getRow() + pos.getColumn() * taken.get(pos).getCharRep();
        }

        return hashCode;
    }



    /**
     * Marks all positions that are threaten by the chess piece  placed on the given position. If at least one of the
     * threaten position is taken PositionsInCollisionException is thrown
     *
     * @param chessFigure chess piece
     * @param position
     * @throws PositionsInCollisionException
     */

    private void markThreatenPositions(ChessFigure chessFigure, Position position) throws PositionsInCollisionException {
        chessFigure.markThreatenPositions(this, position);

    }
}
