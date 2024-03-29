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
    private String consoleMessage = "";

    public void setConsoleMessage(String consoleMessage)
    {
        this.consoleMessage = consoleMessage;
    }

    public String getConsoleMessage()
    {
        return consoleMessage;
    }

    public void draw(Graphics2D g, double shipShield, double shipHealth)
    {
        g.setColor(Color.WHITE);
        g.drawRect(10, 580, 250, 128);
        g.setColor(new Color(0.7529412f, 0.7529412f, 0.7529412f, 0.5f));
        g.fillRect(10, 580, 251, 129);

        g.setColor(new Color(1f, 0f, 0f, .5f));
        g.fillRect(65, 621, 187, 20);
        g.setColor(Color.GRAY);
        g.fillRect(65, 621, (int) shipShield, 20);
        g.setColor(Color.WHITE);
        g.drawString("Shield: ", 20, 635);

        // Rendering our health and stuff
        g.setColor(new Color(1f, 0f, 0f, .5f));
        g.fillRect(65, 661, 187, 20);
        g.setColor(new Color(40, 89, 31));
        g.fillRect(65, 661, (int) shipHealth, 20);
        g.setColor(Color.WHITE);
        g.drawString("Health: ", 20, 675);
    }
}
