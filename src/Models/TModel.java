package Models;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * Just your old standard model
 *
 * @author Titouan Vervack
 */
public class TModel {

    private EventListenerList listeners = new EventListenerList();

    public void addListener(ChangeListener listener) {
        listeners.add(ChangeListener.class, listener);
    }

    public void removeListener(ChangeListener listener) {
        listeners.remove(ChangeListener.class, listener);
    }
    private final ChangeEvent changeEvent = new ChangeEvent(this);

    protected void fireStateChanged() {
        Object[] l = listeners.getListenerList();
        for (int i = l.length - 2; i >= 0; i -= 2) {
            if (l[i] == ChangeListener.class) {
                ((ChangeListener) l[i + 1]).stateChanged(changeEvent);
            }
        }
    }
}
