package Actions;

import Models.WorldModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * Controls the zoombuttons on the editorpanel
 *
 * @author FlashYoshi
 */
public class ZoomButtonAction extends AbstractAction {

    private WorldModel world;

    public ZoomButtonAction(WorldModel world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonName = parseName(button.toString());
        switch(buttonName){
            case "-":
                world.decZoom();
                break;
            case "+":
                world.incZoom();
                break;
            default:
                System.err.println("Button action encountered a weird name!\nHere it is: " + buttonName);
                break;
        }
    }

    private String parseName(String s) {
        /*Split the path independent of the OS*/
        String[] splitted = s.split("[/\\\\]");
        return splitted[splitted.length - 1].split(".png")[0];
    }
}
