/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package Splash;


import javax.swing.*;
import java.awt.*;


public class SplashPanel extends JPanel {
    private ImageIcon splashImage = ImageHelper.loadImage("splash.png");

    private Dimension size = new Dimension(splashImage.getIconWidth(), splashImage.getIconHeight());

    public SplashPanel() {
        super();
        setForeground(new Color(233, 115, 103));
        setFont(new Font("Serif", Font.PLAIN, 28));
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        splashImage.paintIcon(this, g, 0, 0);
        int x = 316;
        int y = 172;
        Object rh = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(getFont());
        g.setColor(Color.black);
        g.setColor(getForeground());

        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, rh);
    }

}
