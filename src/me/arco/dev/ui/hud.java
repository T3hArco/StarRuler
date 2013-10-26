package me.arco.dev.ui;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 15/10/13
 * Time: 19:15
 */
public class Hud extends UI
{

    @Override
    public void draw(Graphics2D g, double shipShield, double shipHealth, String arg3)
    {
        g.setColor(Color.RED);
        g.fillRect(50, 660, 80, 20);
        g.setColor(Color.GRAY);
        g.fillRect(50, 660, (int) shipShield, 20);
        g.setColor(Color.WHITE);
        g.drawString("Shield: ", 5, 675);
        g.drawString(shipShield + "", 80, 675);

        // Rendering our health and stuff
        g.setColor(Color.RED);
        g.fillRect(50, 690, 80, 20);
        g.setColor(new Color(0, 100, 0));
        g.fillRect(50, 690, (int) shipHealth, 20);
        g.setColor(Color.WHITE);
        g.drawString("Health: ", 5, 705);
        g.drawString(shipHealth + "", 80, 705);
    }
}
