package Snake;

import javax.swing.*;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

import static Snake.Board.*;
import static Snake.Main.*;


/**
 * Created by ken on 14.04.2017.
 */
public class Worker implements Runnable{

    protected static java.util.Timer timer;
    protected static java.util.Timer secondTimer;

    @Override
    public void run() {

        if(secondTimer != null){
            secondTimer.cancel();
        }

        secondTimer = new java.util.Timer();

        timer = new java.util.Timer();
        timer.schedule(new RefreshFrame(), 0,150);

    }

    public static void updateFrame(){
        int index = -1;

        //draw and update projectiles
        for(Iterator<Projectile> projectileIterator = projectiles.iterator(); projectileIterator.hasNext();){
            Projectile projectile = projectileIterator.next();

            surface[projectile.position[0]][projectile.position[1]].setBackground(Color.white);

            projectile.update();

            try{
                surface[projectile.position[0]][projectile.position[1]].setBackground(projectile.color);
            }catch (ArrayIndexOutOfBoundsException e){
                projectileIterator.remove();
            }

            for(int[] bodyPart : bodyPositions){
                if(projectile.position[0] == bodyPart[0] && projectile.position[1] == bodyPart[1]){
                    index = bodyPositions.indexOf(bodyPart);
                }
            }
        }


        //update body position
            if(index != -1){
                //TODO:this is causing problem, fix it
                for(int i = 0; i < index; i++){
                    int[] temp = bodyPositions.remove(i);
                    surface[temp[0]][temp[1]].setBackground(Color.white);
                    snakeSize--;
                 }
            }

            if(bodyPositions.size() == snakeSize){
                int[] temp = bodyPositions.remove(0);
                surface[temp[0]][temp[1]].setBackground(Color.white);
            }

        //draw the body
        for(int i = 0; i < bodyPositions.size(); i++){
            surface[bodyPositions.get(i)[0]][bodyPositions.get(i)[1]].setBackground(body);
        }


        //paint grid depending on type
        switch (foodType){
            case FASTER:
                surface[foodPosition[0]][foodPosition[1]].setBackground(fastFood);
                break;

            case BONUS:
                surface[foodPosition[0]][foodPosition[1]].setBackground(bonusFood);
                break;

            case DISORIENT:
                surface[foodPosition[0]][foodPosition[1]].setBackground(defaultFood);
                break;

            case DEFAULT:
                surface[foodPosition[0]][foodPosition[1]].setBackground(defaultFood);
                break;

            default:
                surface[foodPosition[0]][foodPosition[1]].setBackground(defaultFood);
                break;
        }


        for(int[] body : bodyPositions){
            if(headPositionX == body[0] && headPositionY == body[1]){
                currentDirection = null;
                JOptionPane.showMessageDialog(null, "Game Over!");
            }
        }

        if(headPositionX == foodPosition[0] && headPositionY == foodPosition[1]){
            if(foodType == FoodType.BONUS){
                snakeSize += 4;
            }
            snakeSize++;
            placeFood();
        }


         int gridWidthSize = (int)(boardSize.getWidth()/surface.length)/2;
         int gridHeightSize = (int)(boardSize.getHeight()/surface.length)-4;

         for(int i = 0; i < surface.length; i++){
             for(int j = 0; j < surface.length; j++){
                 surface[i][j].setBounds(gridWidthSize*j,gridHeightSize*i,
                          gridWidthSize, gridHeightSize);
             }
         }

         scoreLabel.setText("Score: " + Integer.toString(snakeSize));
    }

    public static void placeFood(){
        Random random = new Random();

        //can be surface.length-1, can cause arrayOutOfBounds
        int x = random.nextInt(surface.length);
        int y = random.nextInt(surface.length);
        foodType = FoodType.values()[random.nextInt(FoodType.values().length)];

        if(snakeSize > 5){
            for(int[] a : bodyPositions){
                while((x == headPositionX && y == headPositionY) || (x == a[0] && y == a[1])){
                    System.err.println("Conflict in placing food\nFood x = " + x + "\nFood y = " + y);
                    x = random.nextInt(surface.length);
                    y = random.nextInt(surface.length);
                }
                //System.out.println("Changed to\n" +
                //        "Food x = " + x + "\nFood y = " + y);
                foodPosition[0] = x;   foodPosition[1] = y;
            }
        }else{
            foodType = FoodType.DEFAULT;
            for(int[] a : bodyPositions){
                while((x == headPositionX && y == headPositionY) || (x == a[0] && y == a[1])){
                    x = random.nextInt(surface.length);
                    y = random.nextInt(surface.length);
                }
            }
            foodPosition[0] = x;   foodPosition[1] = y;
        }
        System.out.println(foodType.toString());
    }

    //x is vertical y is horizontal
    public static void moveSnake(Direction direction){

        //fucking memory addresses.... TODO:ask this
        //update the body
        int[] bodyPosition = {headPositionX, headPositionY};
        bodyPositions.add(bodyPosition);

        //move and update position of the head
        switch (direction){
            case UP:
                if(headPositionX - 1 >= 0){
                    surface[headPositionX-1][headPositionY].setBackground(head);
                    headPositionX -= 1;
                }else{ //if out of bounds
                    surface[surface.length-1][headPositionY].setBackground(head);
                    headPositionX = surface.length-1;
                }
                break;
            case DOWN:
                if(headPositionX + 1 < surface.length){
                    surface[headPositionX+1][headPositionY].setBackground(head);
                    headPositionX += 1;
                }else{
                    surface[0][headPositionY].setBackground(head);
                    headPositionX = 0;
                }
                break;
            case LEFT:
                if(headPositionY - 1 >= 0){
                    surface[headPositionX][headPositionY-1].setBackground(head);
                    headPositionY -= 1;
                }else{
                    surface[headPositionX][surface.length-1].setBackground(head);
                    headPositionY = surface.length-1;
                }
                break;
            case RIGHT:
                if(headPositionY + 1 < surface.length){
                    surface[headPositionX][headPositionY+1].setBackground(head);
                    headPositionY += 1;
                }else{
                    surface[headPositionX][0].setBackground(head);
                    headPositionY = 0;
                }
                break;
        }
    }


    public static void getFrameRectangle(){
        boardSize =  board.getBounds();
    }
}
