import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class shopscreen {
    private static final Font consolas = new Font("Consolas", Font.PLAIN, 60);
    private static int player1X = 0;
    private static int playerScore;
    private static double multiplier;

    public shopscreen() {
        player1X = (int) (MainGame.getWIDTH() / 2.0) - 80;
        multiplier = 1; // Initialize multiplier to 1

    }

    public static void playerScored() {

        playerScore = (int) (playerScore + 1 + getMultiplier());
    }

    public static double getMultiplier() {
        return multiplier;
    }

    public static void setMultiplier(double multiplier) {
        Scoreboard.setMultiplier(Scoreboard.getMultiplier());
    }

    public static void addMultiplier(double multiplier) {
        Scoreboard.setMultiplier(Scoreboard.getMultiplier() + 1);
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void draw(Graphics g) {
        int modalWidth = MainGame.getWIDTH() / 2;
        int modalHeight = MainGame.getHEIGHT() / 2;
        int modalX = (MainGame.getWIDTH() - modalWidth) / 2;
        int modalY = (MainGame.getHEIGHT() - modalHeight) / 2;

        // Draw the modal
        g.setColor(Color.BLACK);

        int buttonHeight = (modalHeight - 100) / 4;
        int buttonX = modalX + 10; // Move buttons more towards the left

        // Draw the buttons and the text on the buttons
        String[] buttons = {"Increase Paddle Size", "Increase Ball Size", "Increase Score Multiplier", "Add a Ball"};
        for (int i = 0; i < buttons.length; i++) {
            int buttonY = modalY + 20 + i * (buttonHeight + 20);

            // Calculate the width of the text
            int textWidth = g.getFontMetrics().stringWidth(buttons[i]);

            // Draw the button with a black outline and translucent white fill
            g.setColor(Color.BLACK);
            g.fillRect(buttonX - 5, buttonY - 5, textWidth + 10, buttonHeight + 10);
            g.setColor(new Color(255, 255, 255, 200)); // Translucent white
            g.fillRect(buttonX, buttonY, textWidth, buttonHeight);

            // Draw the text
            g.setColor(Color.BLACK);
            g.drawString(buttons[i], buttonX, buttonY + buttonHeight / 2);
        }
    }
}
