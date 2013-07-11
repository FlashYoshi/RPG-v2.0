package Actions;

import Models.EditorModel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 * Controls the buttons on the editorpanel
 *
 * @author FlashYoshi
 */
public class ButtonAction extends AbstractAction {

    private EditorModel model;

    public ButtonAction(EditorModel world) {
        this.model = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String buttonName = parseName(button.toString());
        switch(buttonName){
            case "Bottom":
                model.incrementOffset("y");
                break;
            case "Top":
                model.decrementOffset("y");
                break;
            case "Left":
                model.decrementOffset("x");
                break;
            case "Right":
                model.incrementOffset("x");
                break;
            default:
                System.err.println("Button action encountered a weird name!\nHere it is: " + buttonName);
                break;
        }
    }

    private String parseName(String s) {
        /*Split the path independent of the OS*/
        String[] splitted = s.split("[/\\\\]");
        return splitted[splitted.length - 1].split("Arrow")[0];
    }
}
