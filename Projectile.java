package Snake;

import java.awt.*;
import java.util.*;

import static Snake.Board.surface;
import static Snake.Main.board;
import static Snake.Main.currentDirection;
import static Snake.Main.projectiles;

/**
 * Created by ken on 07.05.2017.
 */
public class Projectile{
    public int[] position = new int[2];
    private int health;
    public Color color;
    public boolean isSeen;
    public Direction direction = null;


    public void update() {
        if (isSeen()) {
            color = new Color(0, 0, 0);

            switch (direction) {
                case UP:
                        position[0] -= 1;
                    break;
                case DOWN:
                        position[0] += 1;
                    break;
                case LEFT:
                        position[1] -= 1;
                    break;
                case RIGHT:
                        position[1] += 1;
                    break;
            }
        }
    }

    public Projectile(int health, boolean isSeen){
        setHealth(health);
        setSeen(isSeen);
        position[0] = board.headPositionX;
        position[1] = board.headPositionY;
        direction = currentDirection;
        projectiles.add(this);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
