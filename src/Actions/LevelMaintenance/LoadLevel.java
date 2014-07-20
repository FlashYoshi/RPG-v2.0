package Actions.LevelMaintenance;

import Models.WorldModel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Flash
 */
public class LoadLevel extends AbstractAction {

    private final WorldModel world;

    public LoadLevel(WorldModel world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser(SaveLevel.HOME_DIR);
        fc.setDialogTitle("Load your level!");
        int knop = fc.showOpenDialog(null);
        if (knop == JFileChooser.APPROVE_OPTION) {
            parseXML(fc.getSelectedFile());
        }
    }

    private void parseXML(File file) {
        try {
            Document document = new SAXBuilder().build(file);
            Element root = document.getRootElement();
            List entities = root.getChildren();
            world.reset();
            for (Object entitie : entities) {
                Element el = (Element) entitie;
                String type = el.getAttributeValue("type");
                int x = Integer.parseInt(el.getAttributeValue("x"));
                int y = Integer.parseInt(el.getAttributeValue("y"));
                world.addEntity(type, x, y);
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(LoadLevel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
