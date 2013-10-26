package me.arco.dev.ui;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 20/10/13
 * Time: 9:45
 */
public class Button extends Hud
{
    private String text;

    public Button(String text)
    {
        this.text = text;
    }

    @Override
    public void draw(Graphics2D g, double xPos, double yPos, String arg3)
    {
        g.setColor(Color.WHITE);
        g.drawRect((int) xPos, (int) yPos, 50, 20);
        g.drawString(this.text, (int) xPos + 15, (int) yPos + 15);
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
