package Actions.LevelMaintenance;

import Models.WorldModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Flash
 */
public class LoadLevel extends AbstractAction {

    private WorldModel world;

    public LoadLevel(WorldModel world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
