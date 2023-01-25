import sframeparts.swing.SJFrame;
import sframeparts.swing.SJLabel;
import sframeparts.swing.SJPanel;
import sframeparts.swing.eventname.SMouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Test extends SJPanel {

    public static void main(String[] args) {
        SJFrame j = new SJFrame()
                .setSJBounds(10,10,1440,810)
                .setSJTitle("Test")
                .setSJDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.add(new Test(j.getWidth(),j.getHeight()));

        j.setVisible(true);
    }

    private final SJLabel sl = new SJLabel()
            .setSJBounds(0,0,0,0)
            .setSJBackground(Color.DARK_GRAY)
            .setSJOpaque(true)
            .setSJVisible(true);
    private final SJLabel[] sb;
    private int i;

    public Test(int w,int h) {
        setSJBounds(0, 0, w, h)
                .setSJBackground(Color.DARK_GRAY)
                .setSJVisible(true)
                .setSJLayout(null);
        sl.setSize((int) (w * 0.3), h);
        add(sl);
        sb = new SJLabel[1];
        for (i = 0; i < 1; i++) {
            sb[i] = new SJLabel()
                    .setSJBounds((int) (sl.getWidth() * 0.3), (int) (sl.getHeight() * 0.03 + sl.getHeight() * 0.03 * i), 200, 20)
                    .setSJOpaque(true)
                    .setSJBackground(Color.WHITE)
                    .setSJBorder(new LineBorder(Color.BLACK, 1))
                    .setSJText("ButtonAssets.homePanel[i]");
            sb[i].setHorizontalAlignment(JLabel.CENTER);
            sb[i].addKeepColor("first",sb[i].getBackground())
                    .addKeepColor("press",Color.RED)
                    .addKeepColor("enter",Color.CYAN);
            sb[i].event.mouse(SMouseEvent.MOUSE_ENTERED, () -> sb[i].setBackground(sb[i].getKeepColor("enter")))
                    .mouse(SMouseEvent.MOUSE_EXITED, () -> sb[i].setBackground(sb[i].getKeepColor("first")))
                    .mouse(SMouseEvent.MOUSE_PRESSED, () -> sb[i].setBackground(sb[i].getKeepColor("press")))
                    .mouse(SMouseEvent.MOUSE_RELEASED, () -> sb[i].setBackground(sb[i].getKeepColor("first")));
            sl.add(sb[i]);
        }
    }
}