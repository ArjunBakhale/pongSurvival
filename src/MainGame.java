import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class MainGame extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private BufferedImage image;
    private Graphics g;
    private Timer timer;
    private Paddle paddle;

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

        paddle = new Paddle(WIDTH/16, HEIGHT/2); // Initialize the paddle
        balls.add(new Ball(Math.random()*WIDTH, Math.random()*HEIGHT, HEIGHT/16, Color.red));

        addKeyListener(new Keyboard());
        addMouseListener(new Mouse());

        setFocusable(true);
        requestFocusInWindow();

        timer = new Timer(10, new TimerListener());
        timer.start();
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g.setColor(Color.GREEN);

            paddle.draw(g);


            for (int i = 0; i < balls.size(); i++) {
                balls.get(i).move(WIDTH, HEIGHT);
                balls.get(i).draw(g);


            }

            //display the score
            g.setColor(Color.BLACK);
            Scoreboard.draw(g);

            repaint();
        }
    }


    private class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                balls.add(new Ball(e.getX(), e.getY(), HEIGHT/16, Color.red));
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
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                paddle.moveUp();
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                paddle.moveDown();
            }
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MainGame());
        frame.setVisible(true);
    }
}