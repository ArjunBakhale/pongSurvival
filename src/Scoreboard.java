import java.awt.*;

public class Scoreboard {
    private static final Font consolas = new Font("Consolas", Font.PLAIN,60);
    private static int player1X = 0;
    private static int playerScore;
    private static double multiplier;

    public Scoreboard() {
        player1X = (int) (MainGame.getWIDTH()/2.0) - 80;
        multiplier = 1; // Initialize multiplier to 1

    }

    public static void playerScored(){
        playerScore = (int)(playerScore + 1 + getMultiplier());
    }

    



    public static double getMultiplier() {
        return multiplier;
    }

    public static void setMultiplier(double multiplier) {
        Scoreboard.multiplier = multiplier;
    }

    public static void addMultiplier(double multiplier) {
        Scoreboard.multiplier += multiplier;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void setPlayerScore(int increment) {
        Scoreboard.playerScore -= increment ;
    }
    public static void draw(Graphics g) {
        g.setColor(Color.WHITE);

        // Use a different font
        Font newFont = new Font("Serif", Font.BOLD, 60);
        g.setFont(newFont);

        // Add padding from the top and left of the screen
        int paddingX = 40; // adjust as needed
        int paddingY = 40; // adjust as needed

        g.drawString("Score: " + playerScore, player1X + paddingX, 60 + paddingY);
    }

}