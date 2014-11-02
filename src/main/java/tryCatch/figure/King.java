package tryCatch.figure;

/**
 * Created by pfarid on 28/10/14.
 */
public class King extends ChessFigure {


    public King() {
        super(false, 'K', MovementType.UP, MovementType.DOWN, MovementType.LEFT, MovementType.RIGHT,
                MovementType.UP_LEFT, MovementType.UP_RIGHT, MovementType.DOWN_LEFT, MovementType.DOWN_RIGHT);
    }
}
