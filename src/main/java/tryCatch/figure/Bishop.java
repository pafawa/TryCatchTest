package tryCatch.figure;

/**
 * Created by pfarid on 28/10/14.
 */
public class Bishop extends ChessFigure {


    public Bishop() {
        super(true, 'B', MovementType.UP_LEFT, MovementType.UP_RIGHT, MovementType.DOWN_LEFT, MovementType.DOWN_RIGHT);
    }
}
