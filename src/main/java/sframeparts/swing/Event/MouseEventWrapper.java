package sframeparts.swing.Event;

public class MouseEventWrapper {
    private int o;
    private Runnable r;
    private int m;
    /**
     * @param when When do action.
     * @param action Action.
     * @param button Which button to trigger.
     */
    public MouseEventWrapper(int when, Runnable action, int button) {
        this.o = when;
        this.r = action;
        this.m = button;
    }

    public int getWhen() {
        return o;
    }
    public Runnable getAction() {
        return r;
    }
    public int getButton() {
        return m;
    }

    public void setWhen(int when) {
        this.o = when;
    }
    public void setAction(Runnable action) {
        this.r = action;
    }
    public void setButton(int button) {
        this.m = button;
    }
}
