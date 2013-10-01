package Actions;

import Models.ButtonModel;
import Models.WorldModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * Controls the arrowbuttons on the editorpanel
 *
 * @author FlashYoshi
 */
public class ButtonAction extends AbstractAction {

    private ButtonModel model;
    private WorldModel world;
    
    public ButtonAction(ButtonModel model, WorldModel world) {
        this.model = model;
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonName = parseName(button.toString());
        switch(buttonName){
            case "BottomArrow":
                model.Yscroll(1);
                break;
            case "TopArrow":
                model.Yscroll(-1);
                break;
            case "LeftArrow":
                model.Xscroll(-1);
                break;
            case "RightArrow":
                model.Xscroll(1);
                break;
            case "-":
                world.zoomOut();
                break;
            case "+":
                world.zoomIn();
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
