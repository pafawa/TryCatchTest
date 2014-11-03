package tryCatch;

import com.sun.tools.javac.util.Pair;
import tryCatch.figure.ChessFigure;

import java.util.*;

/**
 * Created by pfarid on 02/11/14.
 */
public final class PermutationGenerator {


    private PermutationGenerator() {

    }

    /**
     * Ands all correct permutations (the correct permutation is a permutation for which all of the pieces can be
     * placed on the board without threatening each other) to a collection.
     *
     * @param chessBoard      M N  dimension chess board
     * @param figureCountList a list of pieces  with their count to be placed on the board
     */

    public static void printoutUniqueCorrectPermutations(ChessBoard chessBoard, LinkedList<Pair<ChessFigure,
            Integer>> figureCountList) {

        if (figureCountList.isEmpty()) {
            System.out.print(chessBoard);
            System.out.print("\n");
            return;
        }

        Pair<ChessFigure, Integer> chessFigureCount = figureCountList.removeFirst();
        if (chessFigureCount.snd == null || chessFigureCount.snd < 1) {
            throw new IllegalArgumentException("Piece count can't be less than 0");
        }


        Iterator<Set<Position>> it = findUniqueSubsets(chessBoard.getAvailablePos(), chessFigureCount.snd).iterator();
        while (it.hasNext()) {
            Set<Position> positionSet = it.next();

            ChessBoard newChessBoard = new ChessBoard(chessBoard);

            try {
                for (Position position : positionSet) {
                    newChessBoard.placeFigureAndMarkThreaten(position, chessFigureCount.fst);
                }
            } catch (PositionsInCollisionException e) {
                continue;
            }

            printoutUniqueCorrectPermutations(newChessBoard, new LinkedList<Pair<ChessFigure,
                    Integer>>(figureCountList));
        }

    }

    public static <E> Set<Set<E>> findUniqueSubsets(List<E> list, int subSetSize) {

        Set<Set<E>> uniqueSubsetList = new HashSet<Set<E>>();

        findUniqueSubsets(list, subSetSize, new HashSet<E>(), uniqueSubsetList);

        return uniqueSubsetList;
    }

    private static <E> void findUniqueSubsets(List<E> list, int subSetSize, Set<E> prefixSet,
                                              Set<Set<E>> uniqueSubsetList) {
        if (prefixSet.size() == subSetSize) {
            uniqueSubsetList.add(new HashSet<E>(prefixSet));
        } else {
            for (int i = 0; i < list.size(); i++) {
                E removed = list.remove(i);
                prefixSet.add(removed);
                findUniqueSubsets(list, subSetSize, prefixSet, uniqueSubsetList);
                prefixSet.remove(removed);
                list.add(i, removed);
            }
        }
    }


}
