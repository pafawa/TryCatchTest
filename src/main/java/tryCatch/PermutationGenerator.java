package tryCatch;

import tryCatch.figure.ChessFigure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by pfarid on 02/11/14.
 */
public class PermutationGenerator {

    /**
     * Printouts all correct permutations (the correct permutation is a permutation for which all of the pieces can be
     * placed on the board without threatening each other).
     *
     * @param chessBoard - M N  dimension chess board
     * @param figureList - a list of pieces to be placed on the board
     */

    public static void printoutUniqueCorrectPermutations(ChessBoard chessBoard, LinkedList<ChessFigure> figureList) {

        Set<ChessBoard> rootOfaTestedTree = new HashSet<ChessBoard>();

        printoutUniqueCorrectPermutations(chessBoard, figureList, rootOfaTestedTree);

    }


    /**
     * Ands all correct permutations (the correct permutation is a permutation for which all of the pieces can be
     * placed on the board without threatening each other) to a collection.
     *
     * @param chessBoard        M N  dimension chess board
     * @param figureList        a list of pieces to be placed on the board
     * @param rootOfaTestedTree a set that stores roots of the already tested tree of chessboard permutations
     */

    public static void printoutUniqueCorrectPermutations(ChessBoard chessBoard, LinkedList<ChessFigure> figureList,
                                                         Set<ChessBoard> rootOfaTestedTree) {
        if (figureList.isEmpty()) {
            System.out.print(chessBoard);
            System.out.print("\n");
            return;
        }

        ChessFigure chessFigure = figureList.removeFirst();

        Iterator<Position> it = chessBoard.getAvailablePosIterator();
        while (it.hasNext()) {
            Position position = it.next();

            ChessBoard newChessBoard = new ChessBoard(chessBoard);

            try {
                newChessBoard.placeFigureAndMarkThreaten(position, chessFigure);
            } catch (PositionsInCollisionException e) {
                continue;
            }

            if (rootOfaTestedTree.contains(newChessBoard)) {
                continue;
            } else {
                rootOfaTestedTree.add(newChessBoard);
            }

            printoutUniqueCorrectPermutations(newChessBoard, new LinkedList<ChessFigure>(figureList),
                    rootOfaTestedTree);
        }

    }


}
