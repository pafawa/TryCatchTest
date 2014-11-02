package tryCatch;

import tryCatch.figure.ChessFigure;

import java.util.*;

/**
 * Created by pfarid on 02/11/14.
 */
public class PermutationGenerator {

    /**
     * Printouts all correct permutations (the correct permutation is a permutation for which all of the pieces can be
     * placed on the board without threatening each other).
     *
     * @param chessBoard  - M N  dimension chess board
     * @param figureList - a list of pieces to be placed on the board
     */

    public static void printoutUniqueCorrectPermutations(ChessBoard chessBoard, LinkedList<ChessFigure> figureList) {

        Set<ChessBoard> chessBoardSet = new HashSet<ChessBoard>();

        generateCorrectPermutations(chessBoard, figureList, chessBoardSet);

        for (ChessBoard chB : chessBoardSet) {
            System.out.print(chB);
            System.out.print("\n");
        }
    }


    /**
     * Ands all correct permutations (the correct permutation is a permutation for which all of the pieces can be
     * placed on the board without threatening each other) to a collection.
     *
     * @param chessBoard           M N  dimension chess board
     * @param figureList           a list of pieces to be placed on the board
     * @param chessBoardCollection collection to which correct permutations are added;
     */

    public static void generateCorrectPermutations(ChessBoard chessBoard, LinkedList<ChessFigure> figureList,
                                                   Collection<ChessBoard> chessBoardCollection) {
        if (figureList.isEmpty()) {
            chessBoardCollection.add(chessBoard);
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
                newChessBoard.placeFigureAndThreaten(position, chessFigure);
            } catch (PositionsInCollisionException e) {
                continue;
            }

            generateCorrectPermutations(newChessBoard, new LinkedList<ChessFigure>(figureList), chessBoardCollection);
        }

    }


}
