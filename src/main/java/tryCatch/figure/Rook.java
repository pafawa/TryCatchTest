package tryCatch.figure;

import tryCatch.figure.ChessFigure;

/**
 * Created by pfarid on 28/10/14.
 */
public class Rook extends ChessFigure {


    public Rook() {
        super(true, "R", MovementType.UP, MovementType.DOWN, MovementType.LEFT, MovementType.RIGHT);
    }
}
