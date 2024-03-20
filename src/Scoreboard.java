import java.awt.*;

public class Scoreboard {
    private static final Font consolas = new Font("Consolas", Font.PLAIN,60);
    private static int player1X = 0;
    private static int playerScore;

    public Scoreboard() {
        player1X = (int) (MainGame.getWIDTH()/2.0) - 80;
    }

    public static void playerScored(){
        playerScore++;
    }

    public static void draw(Graphics g) {
        g.setFont(consolas);
        g.setColor(Color.GREEN);
        g.drawString("Score: " + playerScore, player1X, 60);
    }


}