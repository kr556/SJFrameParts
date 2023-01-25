package sframeparts.swing.eventname;

public class SKeyEvent {
    public final static Typed typed = new Typed();
    public final static Pressed pressed = new Pressed();
    public final static Released released = new Released();
    private final static class Typed {}
    private final static class Pressed {}
    private final static class Released {}
}
