import java.awt.*;

public class Paddle {

    public static int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private static int x;
    private static int y;
    private int speed ;

    public static int getWIDTH() {
        return Paddle_WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.Paddle_WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return Paddle_HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Paddle_HEIGHT = HEIGHT;
    }

    private static int Paddle_WIDTH; 
    private static int Paddle_HEIGHT;


    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
        this.Paddle_WIDTH = MainGame.getWIDTH()/100;
        this.Paddle_HEIGHT = MainGame.getHEIGHT()/100*20;
        this.speed = Paddle_HEIGHT/2;
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int outlineWidth = 8;
        g2d.setStroke(new BasicStroke(outlineWidth));

        // Set color to dark blue
        g.setColor(Color.DARK_GRAY);
        // Fill a round rectangle
        g.fillRoundRect(x, y, Paddle_WIDTH, Paddle_HEIGHT, 10, 10);

        // Set color to black for the outline
        g.setColor(Color.BLACK);
        // Draw the outline of the round rectangle
    }



    public void moveUp() {
        this.y -= speed;
    }

    public void moveDown() {
        this.y += speed;
    }

    // Add getters and setters for x and y if needed
}