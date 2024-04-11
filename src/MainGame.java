import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class MainGame extends JPanel {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private BufferedImage image;
    private Graphics g;
    private Timer timer;
    private Paddle paddle;
    private boolean paused;

    public static ArrayList<Ball> balls = new ArrayList<Ball>();
    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public MainGame() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        paddle = new Paddle(5, HEIGHT/2); // Initialize the paddle
        balls.add(new Ball((WIDTH/2), HEIGHT/2, HEIGHT/20, Color.red));
        paused=false;
        addKeyListener(new Keyboard());
        addMouseListener(new Mouse());

        setFocusable(true);
        requestFocusInWindow();

        timer = new Timer(10, new TimerListener());
        timer.start();
    }

    public static Component getGamePanel() {
        return null;
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Paint gradient = new GradientPaint(0, 0, Color.BLUE, 0, HEIGHT, Color.CYAN);
            ((Graphics2D) g).setPaint(gradient);
            ((Graphics2D) g).setPaint(gradient);
            g.fillRect(0, 0, WIDTH, HEIGHT);



            g.setColor(Color.GREEN);

            paddle.draw(g);
            Shop.draw(g);

            List<Ball> ballsCopy = new ArrayList<>(balls);
            for (Ball ball : ballsCopy) {
                if(!paused){
                    ball.move(WIDTH, HEIGHT);
                }
                ball.draw(g);
            }

            if (paused) {
                shopscreen.draw(g);
            }

            //display the score
            g.setColor(Color.BLACK);
            Scoreboard.draw(g);


            g.setColor(Color.GRAY);
            g.fillRect(WIDTH- WIDTH/28, 0, WIDTH/64, HEIGHT/16);
            g.fillRect (WIDTH - (2*WIDTH/32), 0, WIDTH/64, HEIGHT/16);

            if (Ball.getForgive() == true ){
                g.setColor(Color.WHITE);
                g.fillRect(1,1, 2, HEIGHT);
            }

            if (Ball.getForgive() == false && balls.size() == 0) {
                g.setFont(new Font("Arial", Font.BOLD, 100)); // Set the font size to 100
                String gameOver = "Game Over";
                // Calculate the position of the text
                int stringWidth = g.getFontMetrics().stringWidth(gameOver);
                int stringHeight = g.getFontMetrics().getHeight();
                int x = (WIDTH - stringWidth) / 2;
                int y = (HEIGHT - stringHeight) / 2 + stringHeight;
            
                // Draw the outline
                g.setColor(Color.BLACK);
                for (int i = -3; i <= 3; i++) {
                    for (int j = -3; j <= 3; j++) {
                        g.drawString(gameOver, x + i, y + j);
                    }
                }
            
                // Draw the text
                g.setColor(Color.RED);
                g.drawString(gameOver, x, y);
            }

            if (Scoreboard.getPlayerScore()>10000) {
                g.setFont(new Font("Arial", Font.BOLD, 200)); // Set the font size to 100
                String gameOver = "YOU WIN!";
                // Calculate the position of the text
                int stringWidth = g.getFontMetrics().stringWidth(gameOver);
                int stringHeight = g.getFontMetrics().getHeight();
                int x = (WIDTH - stringWidth) / 2;
                int y = (HEIGHT - stringHeight) / 2 + stringHeight;
            
                // Draw the outline
                g.setColor(Color.BLACK);
                for (int i = -3; i <= 3; i++) {
                    for (int j = -3; j <= 3; j++) {
                        g.drawString(gameOver, x + i, y + j);
                    }
                }
            
                // Draw the text
                g.setColor(Color.WHITE);
                g.drawString(gameOver, x, y);
            }
            repaint();
        }
    }


    private class Mouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            

            if (SwingUtilities.isRightMouseButton(e)) {
//                if(e.getX() >WIDTH - WIDTH/16 && e.getY() < HEIGHT/16) {
//
//                    paused = !paused;
//                    System.out.println("working");
//
//                }

                if(e.getX() >900 && e.getY() < 100) {

                    paused = !paused;
                    



                }


            }



            if (SwingUtilities.isLeftMouseButton(e) && paused==true) {
                
                int SHOP_WIDTH = MainGame.getWIDTH() / 4; // adjust as needed
                int SHOP_HEIGHT = MainGame.getHEIGHT();
                int shopX = MainGame.getWIDTH() - SHOP_WIDTH;
                int buttonHeight = SHOP_HEIGHT / 19;
                int padding = SHOP_HEIGHT/20;
                int startY = SHOP_HEIGHT/4;
            
                int textY1 = buttonHeight * 1 + padding * 0 + startY;
                int textY2 = buttonHeight * 2 + padding * 1 + startY;
                int textY3 = buttonHeight * 3 + padding * 2 + startY;
                int textY4 = buttonHeight * 4 + padding * 3 + startY;
                
                if ((e.getX() >= 280 && e.getX() <= 840) && (e.getY() >= 180 && e.getY() <= 250) && (Scoreboard.getPlayerScore() >= 4)) {
                    Shop.increasePaddleSize();
                    Scoreboard.setPlayerScore(4);
                } else if ((e.getX() >= 280 && e.getX() <= 840) && (e.getY() >= 250 && e.getY() <= 305) && (Scoreboard.getPlayerScore() >= 2)) {
                    // Increase ball size
                    Shop.increaseBallSize();
                    Scoreboard.setPlayerScore(2);
//                    System.out.println("ball size working");
                } else if ((e.getX() >= 280 && e.getX() <= 840) && (e.getY() >= 325 && e.getY() <= 380) && (Scoreboard.getPlayerScore() >= 6)){
                    // Increase score multiplier
                    Shop.increaseScoreMultiplier();
                    Scoreboard.setPlayerScore(6);
//                    System.out.println("score multiplier working");
                } else if ((e.getX() >= 280 && e.getX() <= 840) && (e.getY() >= 400 && e.getY() <= 460) && (Scoreboard.getPlayerScore() >= 1)) {
                    // Add a ball
                    balls.add(new Ball(MainGame.getWIDTH() / 2, MainGame.getHEIGHT() / 2, HEIGHT / 16, Color.red) );
                    Scoreboard.setPlayerScore(1);
//                    System.out.println("add ball working");
                }

            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }


        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

    }

    private class Keyboard implements KeyListener {
        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                paddle.moveUp();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                paddle.moveDown();
            }

            if (e.getKeyCode() == KeyEvent.VK_1) {
                Shop.increasePaddleSize();
                Scoreboard.setPlayerScore(4);
            }
            if (e.getKeyCode() == KeyEvent.VK_2) {
                Shop.increaseBallSize();
                Scoreboard.setPlayerScore(2);
            }
            if (e.getKeyCode() == KeyEvent.VK_3) {
                Shop.increaseScoreMultiplier();
                Scoreboard.setPlayerScore(6);
            }
            if (e.getKeyCode() == KeyEvent.VK_4) {
                balls.add(new Ball(MainGame.getWIDTH() / 2, MainGame.getHEIGHT() / 2, HEIGHT / 16, Color.red));
                Scoreboard.setPlayerScore(1);
            }

            if(e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_SPACE){
                paused = !paused;
            }
            
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }


//    public void playMusic() {
//        File soundFile;
//        soundFile = new File("/Users/ankitrao/Downloads/01-Private-Landing-_Ft.-Justin-Bieber-_-Future_.wav");
//        try {
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioIn);
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        } catch (Exception e) {
//            System.out.println("Audio error" + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MainGame());
        frame.setVisible(true);
    }


}