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
    private int speed = 20;

    public static int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    private static int WIDTH = MainGame.getWIDTH()/100;
    private static int HEIGHT = MainGame.getHEIGHT()/100*20;


    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        int outlineWidth = 8;
        g2d.setStroke(new BasicStroke(outlineWidth));

        g.setColor(Color.BLACK);
        g.drawRect(x, y, WIDTH, HEIGHT);

        g.setColor(Color.GREEN);

        g.fillRect(x, y, WIDTH, HEIGHT);



    }



    public void moveUp() {
        this.y -= speed;
    }

    public void moveDown() {
        this.y += speed;
    }

    // Add getters and setters for x and y if needed
}