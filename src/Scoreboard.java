import java.awt.*;

public class Scoreboard {
    private static final Font consolas = new Font("Consolas", Font.PLAIN,60);
    private static int player1X = 0;
    private static int playerScore;
    private static double multiplier;

    public Scoreboard() {
        player1X = (int) (MainGame.getWIDTH()/2.0) - 80;
        this.multiplier = 2; // Initialize multiplier to 1

    }

    public static void playerScored(){
        playerScore = (int)(playerScore + 1 * getMultiplier());
    }

    public static double getMultiplier() {
        return multiplier;
    }

    public static void setMultiplier(double multiplier) {
        Scoreboard.multiplier = multiplier;
    }

    public static void draw(Graphics g) {
        g.setFont(consolas);
        g.setColor(Color.GREEN);
        g.drawString("Score: " + playerScore, player1X, 60);
    }


}