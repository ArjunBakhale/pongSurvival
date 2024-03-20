import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;


public class Shop {
    private static final int SHOP_WIDTH = MainGame.getWIDTH() / 4; // adjust as needed
    private static final int SHOP_HEIGHT = MainGame.getHEIGHT();



    public Shop() {
        // Initialize the shop
    }

    public void increasePaddleSize(Paddle paddle) {
        // Increase the size of the paddle
        paddle.setHEIGHT(paddle.getHEIGHT() + 1);
    }

    public void increaseBallSize(Ball ball) {
        // Increase the size of the ball
        for (int i = 0; i<MainGame.balls.size(); i++) {
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
        g.setFont(new Font("Arial", Font.PLAIN, 8));
    
        // Draw shop items
        int textX = shopX + SHOP_WIDTH / 2;
        int textY = SHOP_HEIGHT / 2 - 50; // Adjust this value as needed
        g.drawString("Increase Paddle Size", textX, textY);
        g.drawString("Increase Ball Size", textX, textY + 20);
        g.drawString("Increase Score Multiplier", textX, textY + 40);
        g.drawString("Add a Ball", textX, textY + 60);
    }
}