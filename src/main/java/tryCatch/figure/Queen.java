package tryCatch.figure;

import tryCatch.figure.ChessFigure;

/**
 * Created by pfarid on 28/10/14.
 */
public class Queen extends ChessFigure {


    public Queen() {
        super(true, "Q", MovementType.UP, MovementType.DOWN, MovementType.LEFT, MovementType.RIGHT,
                MovementType.UP_LEFT, MovementType.UP_RIGHT, MovementType.DOWN_LEFT, MovementType.DOWN_RIGHT);
    }
}
