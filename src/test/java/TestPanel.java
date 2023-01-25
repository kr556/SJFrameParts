import sframeparts.swing.SJPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestPanel extends SJPanel implements MouseListener {
    public TestPanel() {
        new Event().drag(true);
    }
}
