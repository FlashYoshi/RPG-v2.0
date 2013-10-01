package GUI;

import Actions.LevelMaintenance.LoadLevel;
import Actions.LevelMaintenance.SaveLevel;
import Engine.Game;
import Models.WorldModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Wrapper class for the EditorPanel and EditorList
 *
 * @author FlashYoshi
 */
public class Editor {

    public Editor(JPanel panel, Game game) {
        /*TODO: Display Dimension-selection screen first*/
        JFrame frame = (JFrame) panel.getParent().getParent().getParent();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        Dimension panelSize = panel.getSize();
        WorldModel world = new WorldModel(128, 128);
        
        Dimension d = new Dimension((int) (panelSize.width * 0.2), 52);
        JPanel listPanel = new JPanel();
        listPanel.setMinimumSize(new Dimension(d.width, panel.getHeight()));
        listPanel.setLayout(new BorderLayout());
        EditorList list = new EditorList(world);
        
        JButton save = new JButton("Save");
        save.addActionListener(new SaveLevel(new File("D:/uma.lvl"), world));
        JButton load = new JButton("Load");
        load.addActionListener(new LoadLevel(world));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(d);
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(save, BorderLayout.NORTH);
        buttonPanel.add(load, BorderLayout.CENTER);
        
        listPanel.add(buttonPanel, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);

        EditorPanel editPanel = new EditorPanel(new Dimension((int) (panelSize.width * 0.8), panelSize.height), world, list, game);
        panel.add(editPanel);
        panel.add(listPanel);
        
        frame.setResizable(false);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
