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
                new LinkedList<ChessFigure>(Arrays.asList(new King(), new Knight(), new Queen())));

    }


    @Test
    public void test7x7() {
        PermutationGenerator.printoutUniqueCorrectPermutations(new ChessBoard(new int[7][7]),
                new LinkedList<ChessFigure>(Arrays.asList(new King(), new King(), new Queen(), new Queen(),
                        new Bishop())));

    }
}
