package Engine;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Titouan Vervack
 */
public class GameStarter {

    public static void start(final Game game, final GamePanel panel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame window = game.getFrame();
                window.setExtendedState(JFrame.MAXIMIZED_BOTH);
                window.setContentPane(panel);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
                new Thread(new GameInternals(game)).start();
            }
        });
    }
}
