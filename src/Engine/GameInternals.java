package Engine;

/**
 *
 * @author Titouan Vervack
 */
public class GameInternals implements Runnable {

    private Game game;
    private GamePanel panel;

    public GameInternals(Game game) {
        this.game = game;
        panel = (GamePanel) game.getFrame().getContentPane();
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while (!game.isOver()) {
            if (!game.isPaused()) {
                panel.repaint();
                try {
                    Thread.sleep(game.getDelay());
                } catch (InterruptedException ex) {
                    System.err.println("Exception at getDelay(): " + ex.toString());
                }
            }
        }
    }
}
