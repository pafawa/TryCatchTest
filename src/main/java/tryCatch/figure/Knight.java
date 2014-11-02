package tryCatch.figure;

/**
 * Created by pfarid on 28/10/14.
 */
public class Knight extends ChessFigure {


    public Knight() {
        super(false, 'N', MovementType.UP_TWO_RIGHT, MovementType.UP_TWO_LEFT, MovementType.DOWN_TWO_RIGHT,
                MovementType.DOWN_TWO_LEFT, MovementType.TWO_UP_RIGHT, MovementType.TWO_UP_LEFT,
                MovementType.TWO_DOWN_RIGHT,
                MovementType.TWO_DOWN_LEFT);
    }
}
