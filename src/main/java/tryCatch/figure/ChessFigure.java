package tryCatch.figure;


import tryCatch.ChessBoard;
import tryCatch.Position;
import tryCatch.PositionsInCollisionException;

/**
 * Created by pfarid on 28/10/14.
 */
public abstract class ChessFigure {

    protected MovementType[] movementTypes;
    private boolean canGoMoreThanOne;
    private String stringRep;

    protected ChessFigure(boolean canGoMoreThanOne, String  stringRep, MovementType... movementTypes) {
        this.canGoMoreThanOne = canGoMoreThanOne;
        this.movementTypes = movementTypes;
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }

    public void markReachablePositions(ChessBoard chessBoard, Position position) throws PositionsInCollisionException {
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
