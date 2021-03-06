package tryCatch;

import com.sun.tools.javac.util.Pair;
import tryCatch.figure.ChessFigure;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pfarid on 02/11/14.
 */
public final class PermutationGenerator {

    public static int POOL_SIZE = 8;
    public static int MAX_QUEUE_SIZE = 10;


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
            Integer>> figureCountList) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        POOL_SIZE,
                        POOL_SIZE,
                        Long.MAX_VALUE,
                        TimeUnit.DAYS,
                        new LinkedBlockingQueue<Runnable>()
                );

        printoutUniqueCorrectPermutations(chessBoard, figureCountList, threadPoolExecutor);

        while (true) {
            if (threadPoolExecutor.getQueue().size() == 0 && threadPoolExecutor.getActiveCount() == 0) {
                break;
            }
            Thread.sleep(100);
        }

        threadPoolExecutor.shutdown();

    }


    private static void printoutUniqueCorrectPermutations(ChessBoard chessBoard, LinkedList<Pair<ChessFigure,
            Integer>> figureCountList, final ThreadPoolExecutor executor) {

        if (figureCountList.isEmpty()) {
            printOutBoard(chessBoard);
            return;
        }

        Pair<ChessFigure, Integer> chessFigureCount = figureCountList.removeFirst();
        if (chessFigureCount.snd == null || chessFigureCount.snd < 1) {
            throw new IllegalArgumentException("Piece count must be grater than 0");
        }

        Iterator<Set<Position>> it = findUniqueSubsets(chessBoard.getAvailablePos(), chessFigureCount.snd).iterator();
        while (it.hasNext()) {
            Set<Position> positionSet = it.next();

            final ChessBoard newChessBoard = new ChessBoard(chessBoard);

            try {
                for (Position position : positionSet) {
                    newChessBoard.placeFigureAndMarkThreaten(position, chessFigureCount.fst);
                }
            } catch (PositionsInCollisionException e) {
                continue;
            }

            final LinkedList<Pair<ChessFigure,
                    Integer>> newFigureCountList = new LinkedList<Pair<ChessFigure,
                    Integer>>(figureCountList);

            if (executor.getQueue().size() < MAX_QUEUE_SIZE) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        printoutUniqueCorrectPermutations(newChessBoard, newFigureCountList, executor);
                    }
                });
            } else {
                printoutUniqueCorrectPermutations(newChessBoard, newFigureCountList, executor);
            }
        }

    }

    private static void printOutBoard(ChessBoard chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Thread.currentThread().getId());
        stringBuilder.append("\n");
        stringBuilder.append(chessBoard);

        System.out.println(stringBuilder.toString());
    }

    public static <E> Set<Set<E>> findUniqueSubsets(List<E> list, int subSetSize) {

        Set<Set<E>> uniqueSubsetList = new HashSet<Set<E>>();

        findUniqueSubsets(list, subSetSize, new LinkedList<E>(), uniqueSubsetList);

        return uniqueSubsetList;
    }

    private static <E> void findUniqueSubsets(List<E> list, int subSetSize, LinkedList<E> prefixList,
                                              Set<Set<E>> uniqueSubsetList) {
        if (prefixList.size() == subSetSize) {
            uniqueSubsetList.add(new HashSet<E>(prefixList));
        } else {
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                E removed = list.remove(0);
                prefixList.add(removed);
                findUniqueSubsets(new LinkedList<E>(list), subSetSize, prefixList, uniqueSubsetList);
                prefixList.removeLast();
            }
        }
    }


}
