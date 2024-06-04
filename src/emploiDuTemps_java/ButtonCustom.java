package emploiDuTemps_java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonCustom extends JButton {

    private int round = 5;
    private Color c;

    public ButtonCustom(String text, Color c) {
    	super(text);
        this.c = c;
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 8, 8, 8));
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int x = 0;
        int y = 0;
        int width = getWidth();
        int height = getHeight();
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        
        g2.setColor(c);
        g2.fill(area);
        g2.dispose();
        
        super.paintComponent(grphcs);
    }
}
