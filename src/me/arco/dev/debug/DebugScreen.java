package me.arco.dev.debug;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/11/13
 * Time: 12:06
 */
public class DebugScreen
{
    public void draw(Graphics2D g, String[] information)
    {
        float xPos = 275;
        float yPos = 580;

        g.setColor(Color.WHITE);
        g.drawRect((int) xPos, (int) yPos, 180, 128);
        g.setColor(new Color(0.56f, 1.0f, .5f, .5f));
        g.fillRect((int) xPos, (int) yPos, 181, 129);

        g.setColor(Color.WHITE);
        g.drawString("FPS: " + information[0], xPos + 10, yPos + 18);
        g.drawString("Population: " + information[1], xPos + 10, yPos + 33);
        g.drawString("Ship Elements: " + information[2], xPos + 10, yPos + 48);
        g.drawString("Last Mouse Pos (x): " + information[3], xPos + 10, yPos + 63);
        g.drawString("Last Mouse Pos (y): " + information[4], xPos + 10, yPos + 78);
        g.drawString("Last Mouse Action: " + information[5], xPos + 10, yPos + 93);
    }
}
