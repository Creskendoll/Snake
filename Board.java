package Snake;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;

public class Board extends JFrame{
    private JPanel contentPane, infoPane;
    protected static JButton[][] surface;

    protected static JLabel scoreLabel, highScore;

    protected static Color head = new Color(182,59,89), body = new Color(59, 89, 182),
           defaultFood = new Color(255,255,0), fastFood = new Color(50,182,89),
           bonusFood = new Color(0,255,255);

    protected static int headPositionX = 0;
    protected static int headPositionY = 0;

    //to store x and y
    protected static ArrayList<int[]> bodyPositions;

    public Board(int boardSize){
        //build frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        infoPane = new JPanel();

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       // getContentPane().add(contentPane);
        contentPane.setLayout(null); //was null
        contentPane.setFocusable(true);
        contentPane.requestFocus();
        setLayout(new GridLayout(1,1));
        contentPane.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
               contentPane.requestFocus();
            }
        });
        contentPane.addKeyListener(new KeyHandler());

        bodyPositions = new ArrayList<>();

        scoreLabel = new JLabel("Score: ");
        highScore = new JLabel("High Score: ");


        infoPane.add(highScore);
        infoPane.add(scoreLabel);

        this.setFocusable(false);

        //set window size
        setSize(64*(int)(Math.sqrt(boardSize)), 64*(int)(Math.sqrt(boardSize))+45);

        Rectangle rectangle = this.getBounds();

        //build grids
        surface = new JButton[(int)Math.sqrt(boardSize)][(int)Math.sqrt(boardSize)];
        for(int i = 0; i < Math.sqrt(boardSize); i++){
            for(int j = 0; j < Math.sqrt(boardSize); j++){
                JButton grid = new JButton();
                grid.setFocusable(false);
                grid.setEnabled(false);
                grid.setBackground(Color.white);
                int gridWidthSize = (int)(rectangle.getWidth()/surface.length)/2;
                int gridHeightSize = (int)(rectangle.getHeight()/surface.length)-4;
                grid.setBounds((gridWidthSize * j), (gridHeightSize * i), gridWidthSize, gridHeightSize);
                surface[i][j] = grid;
                contentPane.add(grid);
            }
        }


        add(contentPane);
        add(infoPane);


        // Adjust how the frame looks
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch ( Exception e) {
            e.printStackTrace();
        }

        surface[0][0].setBackground(head);
        setVisible(true);
    }



}
