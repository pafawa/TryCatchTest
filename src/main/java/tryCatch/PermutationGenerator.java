package tryCatch;

import tryCatch.figure.ChessFigure;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by pfarid on 02/11/14.
 */
public class PermutationGenerator {


    public static  void printoutFeasiblePermutations(ChessBoard chessBoard, LinkedList<ChessFigure> figureList) {
        if (figureList.isEmpty()) {
            System.out.println(chessBoard);
            return;
        }

        ChessFigure chessFigure = figureList.removeFirst();

        Iterator<Position> it = chessBoard.getAvailablePosIterator();
        while (it.hasNext()) {
            Position position = it.next();

            ChessBoard newChessBoard;
            try {
                newChessBoard = (ChessBoard) chessBoard.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

            try {
                newChessBoard.placeFigure(position, chessFigure);
            } catch (PositionsInCollisionException e) {
                continue;
            }

            printoutFeasiblePermutations(newChessBoard, new LinkedList<ChessFigure>(figureList));
        }

    }


}
