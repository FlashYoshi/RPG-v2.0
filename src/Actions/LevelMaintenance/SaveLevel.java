package Actions.LevelMaintenance;

import Entities.Entity;
import Models.WorldModel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Flash
 */
public class SaveLevel extends AbstractAction {

    private WorldModel world;
    private static final File HOME_DIR = new File(System.getProperty("user.home"));

    public SaveLevel(WorldModel world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser(HOME_DIR);
        fc.setDialogTitle("Save your level!");
        int knop = fc.showOpenDialog(null);
        FileWriter writer;
        if (knop == JFileChooser.APPROVE_OPTION) {
            try {
                writer = new FileWriter(fc.getSelectedFile());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "The save failed to succeed.\nAn error was encountered: " + ex, "Error!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Element root = new Element("Level1");
            Element size = new Element("Size");
            size.setAttribute("width", String.valueOf(world.getSize().width));
            size.setAttribute("height", String.valueOf(world.getSize().height));

            Entity[][] sea = world.getSea();
            for (int x = 0; x < sea.length; x++) {
                for (int y = 0; y < sea[x].length; y++) {
                    Entity s = sea[x][y];
                    if (s != null) {
                        Element obstacle = new Element("Sea");
                        obstacle.setAttribute("type", s.toString());
                        obstacle.setAttribute("x", String.valueOf(x));
                        obstacle.setAttribute("y", String.valueOf(y));
                        root.addContent(obstacle);
                    }
                }
            }

            Entity[][] background = world.getBackground();
            for (int x = 0; x < background.length; x++) {
                for (int y = 0; y < background[x].length; y++) {
                    Entity bg = background[x][y];
                    if (bg != null) {
                        Element obstacle = new Element("Background");
                        obstacle.setAttribute("type", bg.toString());
                        obstacle.setAttribute("x", String.valueOf(x));
                        obstacle.setAttribute("y", String.valueOf(y));
                        root.addContent(obstacle);
                    }
                }
            }

            Entity[][] obstacles = world.getObstacles();
            for (int x = 0; x < obstacles.length; x++) {
                for (int y = 0; y < obstacles[x].length; y++) {
                    Entity obs = obstacles[x][y];
                    if (obs != null) {
                        Element obstacle = new Element("Obstacle");
                        obstacle.setAttribute("type", obs.toString());
                        obstacle.setAttribute("x", String.valueOf(x));
                        obstacle.setAttribute("y", String.valueOf(y));
                        root.addContent(obstacle);
                    }
                }
            }
            Document document = new Document(root);
            XMLOutputter serializer = new XMLOutputter();
            serializer.setFormat(Format.getPrettyFormat());
            try {
                serializer.output(document, writer);
                JOptionPane.showMessageDialog(null, "The file was saved succesfully at " + fc.getSelectedFile(), "File saved!", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "The save failed to succeed.\nAn error was encountered: " + ex, "Error!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
