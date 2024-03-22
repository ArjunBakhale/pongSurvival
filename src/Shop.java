import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Shop implements MouseListener{
    private static final int SHOP_WIDTH = MainGame.getWIDTH() / 4; // adjust as needed
    private static final int SHOP_HEIGHT = MainGame.getHEIGHT();


    public Shop() {
        // Initialize the shop
        // Add this as a MouseListener to your game panel
        MainGame.getGamePanel().addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Check if the click was within the bounds of the shop items
        if (x >= shopX && x <= shopX + SHOP_WIDTH) {
            if (y >= textY && y <= textY + 20) {
                // Increase paddle size
                increasePaddleSize(Paddle.getHEIGHT());
            } else if (y >= textY + 20 && y <= textY + 40) {
                // Increase ball size
                increaseBallSize(MainGame.getBall());
            } else if (y >= textY + 40 && y <= textY + 60) {
                // Increase score multiplier
                increaseScoreMultiplier(Scoreboard.getPlayerScore());
            } else if (y >= textY + 60 && y <= textY + 80) {
                // Add a ball
                addBall();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void increasePaddleSize(int paddle) {
        // Increase the size of the paddle
        paddle.setHEIGHT(paddle.getHEIGHT() + 1);
    }

    public void increaseBallSize(Ball ball) {
        // Increase the size of the ball
        for (int i = 0; i < MainGame.balls.size(); i++) {
            Ball balls = MainGame.balls.get(i).setRadius(MainGame.balls.get(i).getRadius() + 1);
        }
    }

    public void increaseScoreMultiplier(Scoreboard scoreboard) {
        // Increase the score multiplier
        Scoreboard.setMultiplier(Scoreboard.getMultiplier() + 0.75);
    }

    public void addBall() {
        // Add a new ball to the game
        for (Ball balls : MainGame.balls) {
            MainGame.balls.add(new Ball(Math.random() * MainGame.getWIDTH(), Math.random() * MainGame.getHEIGHT(), MainGame.getHEIGHT() / 16, Color.red));
        }
    }

    public static void draw(Graphics g) {
        // Draw the shop on the right side of the screen
        int shopX = MainGame.getWIDTH() - SHOP_WIDTH;
        g.setColor(Color.WHITE);
        g.fillRect(shopX, 0, SHOP_WIDTH, SHOP_HEIGHT);

        // Draw a border around the shop
        g.setColor(Color.BLACK);
        g.drawRect(shopX, 0, SHOP_WIDTH, SHOP_HEIGHT);
        g.setColor(new Color(210, 180, 140));
        g.fillRect(shopX, 0, SHOP_WIDTH, SHOP_HEIGHT);

        // Make the upgrade text smaller
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        // Change the color of the text to black
        g.setColor(Color.BLACK);

        // Draw shop items
        int textX = shopX + SHOP_WIDTH / 2;
        int textY = SHOP_HEIGHT / 2; // Adjust this value as needed
        g.drawString("Increase Paddle Size", textX, textY);
        g.drawString("Increase Ball Size", textX, textY + 20);
        g.drawString("Increase Score Multiplier", textX, textY + 40);
        g.drawString("Add a Ball", textX, textY + 60);
    }
}