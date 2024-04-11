import java.awt.Color;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.awt.GradientPaint;

public class Ball {

    // 1. Declaration of Variables
    private double speedup = 1.25;

    private double x;            //x-coordinate of the center of the ball
    private double y;            //y-coordinate of the center of the ball
    private double diameter;    //diameter of the ball
    private double radius;        //radius of the ball
    private Color color;        //color of the ball
    private double xSpeed;        //x-speed = change in x-position
    private double ySpeed;        //y-speed = change in y-position
    private static boolean forgive = true;

    // 2. Create a default constructor

    /**
     * Default Constructor
     * Creates a red ball at (0, 0) with a diameter of 25.
     * The default speed is 0.
     */
    public Ball() {
        this.x = 0;
        this.y = 0;
        this.diameter = 25;
        this.radius = 12.5;
        this.color = Color.RED;
        this.xSpeed = 0;
        this.ySpeed = 0;

    }

    // 3. Write a constructor that allows the user to input the parameters (x, y, diameter, Color)
    // and sets the x and y-speed = 0.

    public Ball(double x, double y, double diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.radius = diameter / 2;
        this.color = color;

        if (Math.random() < 0.5) {
            this.xSpeed = -10;
        } else {
            this.xSpeed = 10;
        }

        if (Math.random() < 0.5) {
            this.ySpeed = -10;
        } else {
            this.ySpeed = 10;
        }
    }


    // 4. Create getters and setters for all private variables

    public static boolean getForgive() {
        return forgive;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getRadius() {
        return radius;
    }

    public Ball setRadius(double radius) {
        this.radius = radius;
        return null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }


    // 5. Finish the following methods


    // 6. Test using BouncingBallTester.java


public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // Set two constant colors
    Color color1 = Color.GRAY;
    Color color2 = Color.WHITE;

    // Create a gradient from the top (x, y - radius) to the bottom (x, y + radius) of the ball
    GradientPaint gradient = new GradientPaint((float)x, (float)(y - radius), color1, (float)x, (float)(y + radius), color2);

    g2d.setPaint(gradient);
    g.fillOval((int) (x - radius), (int) (y - radius), (int) radius*2, (int) radius*2);

    int outlineWidth = 2;
    g2d.setStroke(new BasicStroke(outlineWidth));
    g.setColor(Color.BLACK);
    g.drawOval((int) (x - radius), (int) (y - radius), (int) radius*2, (int) radius*2);
}


    // 7. Sets the center location of the ball
    //    This "teleports" the ball to the given x/y location

    public void setLocation(double xLoc, double yLoc) {
        this.x = xLoc;
        this.y = yLoc;

    }


    // 8. Sets the xSpeed and ySpeed to a value between
    // negative maxSpeed and positive maxSpeed
    public void setRandomSpeed(double maxSpeed) {
        this.xSpeed = Math.random() * maxSpeed + 4;
        this.ySpeed = Math.random() * maxSpeed + 4;

    }


    // 9. Write the move method to make the ball move around the screen
    // and bounce of the edges.
    public void move(int rightEdge, int bottomEdge) {
        this.x += (int)(xSpeed* (0.8));
        this.y += (int)(ySpeed* (0.8));

        if (x + radius >= rightEdge) {
            x = rightEdge - radius;
            xSpeed = -xSpeed;



        }

        Iterator<Ball> iterator = MainGame.balls.iterator();
        while (iterator.hasNext()) {
            Ball ball = iterator.next();
        
            if (ball.x - ball.radius <= 0) {
                if (forgive == false) {
                    iterator.remove();
                    continue;
                } else {
                    ball.xSpeed = -ball.xSpeed;
                    forgive = false;
                }
            }
        
        }

        if (x - radius <= (Paddle.getX()+ Paddle.getWIDTH()) && y >= Paddle.getY() && y <= Paddle.getY() + Paddle.getHEIGHT()) {
            
            xSpeed = -xSpeed;

            x = Paddle.getX() + radius*1.2;
             // Move the ball outside the paddle
            Scoreboard.playerScored();
        }

        

        if (y + radius >= bottomEdge) {
            y = bottomEdge - radius;
            ySpeed = -ySpeed;
        }

        if (y - radius <= 0) {
            y = radius;
            ySpeed = -ySpeed;
        }

        if (x + radius >= MainGame.getWIDTH()-MainGame.getWIDTH()/4 ) {
            xSpeed = -xSpeed;
            speedup = Math.pow(speedup, 1.005); // Increase speedup exponentially

            if(xSpeed < 0){
                xSpeed -= speedup;
            }
            else{
                xSpeed += speedup;
            }

            if(ySpeed < 0){
                ySpeed -= speedup;
            }
            else{
                ySpeed += speedup;
            }
        }
    }
}