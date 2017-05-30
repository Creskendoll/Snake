package Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static Snake.Main.currentDirection;

/**
 * Created by ken on 14.04.2017.
 */
public class KeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent keyEvent) {

        System.out.println(keyEvent.getKeyChar());

        switch(keyEvent.getKeyChar()){
            case 'w':
                if(currentDirection != Direction.DOWN){
                    currentDirection = Direction.UP;
                }
                break;
            case 's':
                if(currentDirection != Direction.UP){
                    currentDirection = Direction.DOWN;
                }
                break;
            case 'a':
                if(currentDirection != Direction.RIGHT){
                    currentDirection = Direction.LEFT;
                }
                break;
            case 'd':
                if(currentDirection != Direction.LEFT){
                    currentDirection = Direction.RIGHT;
                }
                break;
            case ' ':
                new Projectile(1,true);
                break;
            default:
                currentDirection = null;
                break;
        }
        try{
            Thread.sleep(60);
        }catch (Exception e){
            System.err.println("Memory FULL!");
            e.printStackTrace();
        }
    }
}
