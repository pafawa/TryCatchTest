import com.sun.tools.javac.util.Pair;
import org.junit.Test;
import tryCatch.ChessBoard;
import tryCatch.PermutationGenerator;
import tryCatch.figure.*;

import java.util.Arrays;
import java.util.LinkedList;

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
}
