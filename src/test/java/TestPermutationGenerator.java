import com.sun.tools.javac.util.Pair;
import org.junit.Test;
import tryCatch.ChessBoard;
import tryCatch.PermutationGenerator;
import tryCatch.Position;
import tryCatch.figure.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

import static tryCatch.PermutationGenerator.findUniqueSubsets;

/**
 * Created by pfarid on 28/10/14.
 */
public class TestPermutationGenerator {


    @Test
    public void test3x3() {
        PermutationGenerator.printoutUniqueCorrectPermutations(new ChessBoard(new int[3][3]),
                new LinkedList<Pair<ChessFigure, Integer>>(Arrays.asList(new Pair<ChessFigure, Integer>(new King(),
                        2), new Pair<ChessFigure, Integer>(new Rook(), 1))));

    }


    @Test
    public void test7x7() {
        PermutationGenerator.printoutUniqueCorrectPermutations(new ChessBoard(new int[7][7]),
                new LinkedList<Pair<ChessFigure, Integer>>(Arrays.asList(new Pair<ChessFigure, Integer>(new Queen(),
                        2), new Pair<ChessFigure, Integer>(new Bishop(), 2), new Pair<ChessFigure,
                        Integer>(new Knight(),
                        1), new Pair<ChessFigure, Integer>(new King(),
                        2))));


    }


    @Test
    public void testPerm() {

        for (Set<Position> positionList : findUniqueSubsets(new ArrayList<Position>
                (Arrays.asList(new
                                Position(1, 1), new Position(1, 2),
                        new Position(1, 3), new Position(1, 4))), 2)) {

            System.out.print("[");
            for (Position position : positionList) {
                System.out.print(position + ", ");


            }
            System.out.print("]");

        }
    }
}
