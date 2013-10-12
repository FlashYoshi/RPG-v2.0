package Engine;

import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author FlashYoshi
 */
public abstract class Game {

    private int delay = 1000 / 60;
    private boolean paused = false;
    private boolean over = false;
    private static final String TITLE = "GameEngine v2.0 \u00a9 Titouan Vervack";
    /*JFrame that will be hosting the entire program*/
    private JFrame window;

    public Game() {
        window = new JFrame(TITLE);
    }

    public Game(String title) {
        window = new JFrame(title);
    }

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public void resetGame() {
        delay = 1000 / 60;
        paused = false;
        window.setTitle(TITLE);
        System.gc();
    }

    public String getTitle() {
        return window.getTitle();
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public JFrame getFrame() {
        return window;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isPaused() {
        return paused;
    }

    public void pauseGame() {
        this.paused = true;
    }

    public void resumeGame() {
        this.paused = false;
    }

    public boolean isOver() {
        return over;
    }

    public void stopGame() {
        this.over = true;
    }
}