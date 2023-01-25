package sframeparts.swing.eventname;

import java.awt.*;
import java.awt.event.MouseEvent;

public final class  SMouseEvent extends MouseEvent{
    public SMouseEvent(Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger, int button) {
        super(source, id, when, modifiers, x, y, clickCount, popupTrigger, button);
    }

    public SMouseEvent(Component source, int id, long when, int modifiers, int x, int y, int clickCount, boolean popupTrigger) {
        super(source, id, when, modifiers, x, y, clickCount, popupTrigger);
    }

    public SMouseEvent(Component source, int id, long when, int modifiers, int x, int y, int xAbs, int yAbs, int clickCount, boolean popupTrigger, int button) {
        super(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger, button);
    }

    public static final int MOUSE_ALL = MOUSE_FIRST - 1,
            MOUSE_MAX = MOUSE_WHEEL,
            MOUSE_MAX_FOR_ARRAY = MOUSE_WHEEL - MOUSE_FIRST,
            CLICKED_FOR_ARRAY = MOUSE_CLICKED - MOUSE_FIRST,
            PRESSED_FOR_ARRAY = MOUSE_PRESSED - MOUSE_FIRST,
            RELEASED_FOR_ARRAY = MOUSE_RELEASED - MOUSE_FIRST,
            MOVED_FOR_ARRAY = MOUSE_MOVED - MOUSE_FIRST,
            ENTER_FOR_ARRAY = MOUSE_ENTERED - MOUSE_FIRST,
            EXIT_FOR_ARRAY = MOUSE_EXITED - MOUSE_FIRST,
            DRAGGED_FOR_ARRAY = MOUSE_DRAGGED - MOUSE_FIRST,
            WHEELE_FOR_ARRAY = MOUSE_WHEEL - MOUSE_FIRST,
            BUTTON_ALL = NOBUTTON - 1;
}
