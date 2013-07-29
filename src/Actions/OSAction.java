package Actions;

import Engine.Game;
import GUI.Editor;
import GUI.TList;
import MapsAndFactories.DrawMap;
import Util.Layer;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 * The required action for TList. It will create the EditorPanel, show that
 * the actual game isn't implemented yet or just exit.
 *
 * @author FlashYoshi
 */
public class OSAction extends AbstractAction {

    private JPanel panel;
    private Game game;

    public OSAction(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getID()) {
            case (0):
                preperation(e);
                //Start game
                game.setTitle("RPG v2.0 \u00a9 Titouan Vervack");
                TList tlist = new TList((JPanel) game.getFrame().getContentPane(), new String[]{"Not yet implemented!"}, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.err.println("Game mode hasn't been implemented yet!");
                        System.exit(1);
                    }
                });
                DrawMap.getInstance().addToDraw(tlist, Layer.GUI);
                break;
            case (1):
                preperation(e);
                /*TODO: Create editor on panel*/
                game.setTitle("Level Editor v2.0 \u00a9 Titouan Vervack");
                Editor edit = new Editor(panel, game);
                break;
            case (2):
                System.err.println("Exit has been called!");
                System.exit(0);
                break;
        }
    }

    private void preperation(ActionEvent e) {
        TList tlist = (TList) e.getSource();
        panel = tlist.getPanel();
        /*Told you you shouldn't forget about this :)*/
        tlist.detach();
        DrawMap.getInstance().removeToDraw(tlist, Layer.GUI);
    }
}
