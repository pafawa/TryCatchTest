import org.junit.Test;
import tryCatch.*;
import tryCatch.figure.ChessFigure;
import tryCatch.figure.King;
import tryCatch.figure.Rook;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by pfarid on 28/10/14.
 */
public class TestPermutationGenerator {


    @Test
    public void test3x3() {
        PermutationGenerator.printoutFeasiblePermutations(new ChessBoard(new int[3][3]),
                new LinkedList<ChessFigure>(Arrays.asList(new King(), new King(), new Rook())));
    }
}
