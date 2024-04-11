import java.awt.*;

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
        Graphics2D g2d = (Graphics2D) g;

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

        // Calculate the width of the widest button
        int maxTextWidth = MainGame.getWIDTH()/2;


        // Increase the font size
        g.setFont(new Font("Consolas", Font.PLAIN, 25));

        for (int i = 0; i < buttons.length; i++) {
            int buttonY = modalY + 20 + i * (buttonHeight + 20);

            // Create a gradient from the left (buttonX) to the right (buttonX + maxTextWidth) of the button
            GradientPaint gradient = new GradientPaint(buttonX, buttonY, Color.GRAY, buttonX + maxTextWidth, buttonY, Color.WHITE);

            g2d.setPaint(gradient);
            g.fillRect(buttonX, buttonY, maxTextWidth, buttonHeight);
            g2d.setColor(Color.BLACK);
            g.drawRect(buttonX, buttonY, maxTextWidth, buttonHeight);

            // Draw the text with padding from the left of the button
            int textPadding = 10;
            g.setColor(Color.BLACK);
            g.drawString(buttons[i], buttonX + textPadding, buttonY + buttonHeight / 2);
        }
    }
}
