package Snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Snake.Worker.placeFood;

/**
 * Created by ken on 13.04.2017.
 */
public class Main {
    public static int snakeSize = -1;
    public static Direction currentDirection = null;

    public static int[] foodPosition;
    public static FoodType foodType;

    public static Rectangle boardSize = new Rectangle();

    public static Board board;

    public static ArrayList<Projectile> projectiles = new ArrayList<>();

    public static void main(String[] args){
            snakeSize = Integer.parseInt(JOptionPane.showInputDialog("Snake Size?"));

            board = new Board(400);

            foodPosition = new int[2];

            Thread thread = new Thread(new Worker());

            thread.start();

            placeFood();
    }
}
