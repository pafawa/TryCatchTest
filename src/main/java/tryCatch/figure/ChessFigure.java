package tryCatch.figure;


import tryCatch.ChessBoard;
import tryCatch.Position;
import tryCatch.PositionsInCollisionException;

/**
 * Created by pfarid on 28/10/14.
 */

public abstract class ChessFigure {

    /**
     * What kind of movements can the piece make
     */
    protected MovementType[] movementTypes;

    /**
     * Can it move more then one position at once
     */
    private boolean canGoMoreThanOne;


    /**
     * Char representation on a chess board
     */
    private Character charRep;

    protected ChessFigure(boolean canGoMoreThanOne, Character charRep, MovementType... movementTypes) {
        this.canGoMoreThanOne = canGoMoreThanOne;
        this.movementTypes = movementTypes;
        this.charRep = charRep;
    }

    @Override
    public String toString() {
        return String.valueOf(getCharRep());
    }

    /**
     * Marks all positions that are threaten by the chess piece  placed on the  position on the  given board. If at
     * least  one of the  threaten position is taken PositionsInCollisionException is thrown
     *
     * @param chessBoard
     * @param position
     * @throws PositionsInCollisionException
     */
    public void markThreatenPositions(ChessBoard chessBoard, Position position) throws PositionsInCollisionException {
        for (MovementType movementType : movementTypes) {
            boolean feasible;
            Position newPosition = position;

            do {

                newPosition = new Position(newPosition.getColumn() + movementType.getColumnCoordinates(),
                        newPosition.getRow() + movementType.getRowCoordinates());
                feasible = chessBoard.isFeasible(newPosition);
                chessBoard.markPosAsNotAvailable(newPosition);

            } while (canGoMoreThanOne && feasible);
        }
    }

    public Character getCharRep() {
        return charRep;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ChessFigure)) {
            return false;
        }

        return this.getCharRep() == ((ChessFigure) obj).getCharRep();
    }

    public enum MovementType {
        UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(+1, 0), UP_RIGHT(1, -1), UP_LEFT(-1, -1), DOWN_RIGHT(1, 1),
        DOWN_LEFT(-1, 1), UP_TWO_RIGHT(2, -1), UP_TWO_LEFT(-2, -1), DOWN_TWO_RIGHT(2, 1), DOWN_TWO_LEFT(-2, 1);

        public int columnCoordinates;
        public int rowCoordinates;


        MovementType(int columnCoordinates, int rowCoordinates) {

            this.columnCoordinates = columnCoordinates;
            this.rowCoordinates = rowCoordinates;
        }

        public int getColumnCoordinates() {
            return columnCoordinates;
        }


        public int getRowCoordinates() {
            return rowCoordinates;
        }


    }

}
