package sframeparts.swing;

import sframeparts.swing.Event.MouseEventWrapper;
import sframeparts.swing.eventname.SMouseEvent;

import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class SJButton extends JButton implements MouseListener, MouseMotionListener, ActionListener {
    public Event event = new Event();

    private int mX = 0, mY = 0,
            screenMX = 0, screenMY = 0,
            firstX = 0,firstY = 0,firstMX = 0,firstMY = 0;
    private boolean
            cnButtonEventMode = false,
            drag = false,
            pressed = false,
            entered = false;
    private final List<Integer> keyName = new ArrayList<>();
    private final List<String> colorNameID = new ArrayList<>();
    private final Runnable dummy = () -> {};
    private Runnable action = () -> {};
    private Runnable[]
            mouseAction = new Runnable[SMouseEvent.MOUSE_MAX_FOR_ARRAY + 1];
    /**
     * 0 -> no button<br>
     * 1 -> button1<br>
     * 2 -> button2<br>
     * 3 -> button3<br>
     * <br>
     * 0 -> click<br>
     * 1 -> press<br>
     * 2 -> release<br>
     * 3 -> move<br>
     * 4 -> enter<br>
     * 5 -> exit<br>
     * 6 -> drag<br>
     * 7 -> wheel
     */
    private final MouseEventWrapper[][]
            eventWrappers = new MouseEventWrapper[SMouseEvent.MOUSE_MAX_FOR_ARRAY + 1][4];
    private final HashMap<String,Color> keepColor = new HashMap<>();

    public SJButton setSJSize(int w, int h) {
        setSize(w,h);
        return this;
    }
    public SJButton setSJSize(Dimension d) {
        setSize(d);
        return this;
    }
    public SJButton setSJBounds(int x, int y, int w, int h) {
        setBounds(x,y,w,h);
        return this;
    }
    public SJButton setSJBounds(Rectangle r) {
        setBounds(r);
        return this;
    }
    public SJButton setSJLayout(BorderLayout b) {
        setLayout(b);
        return this;
    }
    public SJButton setSJBackground(Color c) {
        setBackground(c);
        return this;
    }
    public SJButton setSJVisible(boolean b) {
        setVisible(b);
        return this;
    }
    public SJButton setSJBorder(Border b) {
        setBorder(b);
        return this;
    }
    public SJButton setSJOpaque(boolean b) {
        setOpaque(b);
        return this;
    }
    public SJButton setSJText(String text) {
        setText(text);
        return this;
    }
    public SJButton setSJFont(Font f) {
        setFont(f);
        return this;
    }
    public SJButton setSJHorizontalAlignment(int alignment) {
        setHorizontalAlignment(alignment);
        return this;
    }

    public SJButton addSJ(Component com) {
        add(com);
        return this;
    }
    /**
     * Can keep color. Even, if you input number, association by name.
     *
     * @param c Anything color.
     */
    public SJButton addKeepColor(Color c) {
        colorNameID.add(String.valueOf(keepColor.size()));
        keepColor.put(String.valueOf(keepColor.size()), c);
        return this;
    }
    /**
     * Can keep color, and association by name.
     *
     * @param colorName If when this color is null, automatically input size.
     * @param c         Anything color.
     */
    public SJButton addKeepColor(String colorName, Color c) {
        if (colorName.equals("")) {
            colorNameID.add(String.valueOf(keepColor.size()));
            keepColor.put(String.valueOf(keepColor.size()), c);
        } else {
            colorNameID.add(colorName);
            keepColor.put(colorName, c);
        }
        return this;
    }

    public Color getKeepColor(String colorName) {
        return keepColor.get(colorName);
    }
    public Color getKeepColor(int index) {
        return keepColor.get(colorNameID.get(index));
    }

    public class Event {
        public Event mouse(int when, Runnable action) {
            mouseAction[when - SMouseEvent.MOUSE_FIRST] = action;
            return this;
        }
        public void initMouse(int when) {
            if (when == SMouseEvent.MOUSE_ALL) {
                for (int i = SMouseEvent.MOUSE_FIRST; i <= SMouseEvent.MOUSE_MAX; i++) {
                    mouse(i,dummy);
                }
            } else {
                mouse(when,dummy);
            }
        }
        public Event mouse(MouseEventWrapper m) {
            cnButtonEventMode = true;

            eventWrappers[m.getWhen() - SMouseEvent.MOUSE_FIRST][m.getButton() - 1] = m;
            return this;
        }
        public void initEvent(int when, int button) {
            cnButtonEventMode = true;

            if (when == SMouseEvent.MOUSE_ALL) {
                for (int k = 0; k <= SMouseEvent.MOUSE_MAX_FOR_ARRAY; k++) {
                    for (int i = 0; i < SMouseEvent.BUTTON3; i++) {
                        eventWrappers[k][i].setAction(dummy);
                    }
                }
            } else {
                mouse(new MouseEventWrapper(when, dummy, button));
            }
        }
        public void setCnButtonEventMode(boolean b) {
            cnButtonEventMode = b;
        }

        public void drag(boolean dragMove) {
            drag = dragMove;
        }

        public void drag(boolean dragMove ,Runnable action) {
            drag = dragMove;
            mouseAction[SMouseEvent.DRAGGED_FOR_ARRAY] = action;
        }

        public void action(Runnable _action) {
            action = _action;
        }
    }

    public int getMX() {
        return this.mX;
    }
    public int getMY() {
        return this.mY;
    }
    public int getOnScreenMX() {
        return this.screenMX;
    }
    public int getOnScreenMY() {
        return this.screenMY;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        this.mX = e.getX();
        this.mY = e.getY();

        if (cnButtonEventMode) {
            for (MouseEventWrapper m : eventWrappers[SMouseEvent.CLICKED_FOR_ARRAY]) {
                if (m.getButton() == e.getButton()) {
                    m.getAction().run();
                }
            }
        }
        mouseAction[SMouseEvent.CLICKED_FOR_ARRAY].run();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        this.mX = e.getX();
        this.mY = e.getY();
        firstX = mX;
        firstY = mY;

        if (cnButtonEventMode) {
            for (MouseEventWrapper m : eventWrappers[SMouseEvent.PRESSED_FOR_ARRAY]) {
                if (m.getButton() == e.getButton()) {
                    m.getAction().run();
                }
            }
        }
        mouseAction[SMouseEvent.PRESSED_FOR_ARRAY].run();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        this.mX = e.getX();
        this.mY = e.getY();

        if (cnButtonEventMode) {
            for (MouseEventWrapper m : eventWrappers[SMouseEvent.RELEASED_FOR_ARRAY]) {
                if (m.getButton() == e.getButton()) {
                    m.getAction().run();
                }
            }
        }
        mouseAction[SMouseEvent.RELEASED_FOR_ARRAY].run();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        entered = true;
        this.mX = e.getX();
        this.mY = e.getY();

        if (cnButtonEventMode) {
            for (MouseEventWrapper m : eventWrappers[SMouseEvent.ENTER_FOR_ARRAY]) {
                if (m.getButton() == e.getButton()) {
                    m.getAction().run();
                }
            }
        }
        mouseAction[SMouseEvent.ENTER_FOR_ARRAY].run();
    }
    @Override
    public void mouseExited(MouseEvent e) {
        entered = false;
        this.mX = e.getX();
        this.mY = e.getY();

        if (cnButtonEventMode) {
            for (MouseEventWrapper m : eventWrappers[SMouseEvent.EXIT_FOR_ARRAY]) {
                if (m.getButton() == e.getButton()) {
                    m.getAction().run();
                }
            }
        }
        mouseAction[SMouseEvent.EXIT_FOR_ARRAY].run();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mX = e.getX();
        this.mY = e.getY();
        this.screenMX = e.getXOnScreen();
        this.screenMY = e.getYOnScreen();
        mouseAction[SMouseEvent.DRAGGED_FOR_ARRAY].run();
        if (drag) {
            setLocation(getOnScreenMX() - firstMX - firstX, getOnScreenMY() - firstMY - firstY);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        action.run();
    }

    public SJButton() {
        for (int i = 0; i <= SMouseEvent.MOUSE_MAX_FOR_ARRAY; i++) {
            mouseAction[i] = dummy;
            for (int k = 0; k <= SMouseEvent.BUTTON3; k++) {
                eventWrappers[i][k] = new MouseEventWrapper(i,dummy,k);
            }
        }
        addMouseListener(this);
        addMouseMotionListener(this);
        addActionListener(this);
    }
}
