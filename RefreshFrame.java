package Snake;

import java.util.TimerTask;

import static Snake.Main.currentDirection;
import static Snake.Worker.*;

/**
 * Created by ken on 15.04.2017.
 */

public class RefreshFrame extends TimerTask {
    @Override
    public void run() {
        getFrameRectangle();
        if(currentDirection != null){
            moveSnake(currentDirection);
            updateFrame();
        }
    }
}
