package me.arco.dev.ui;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 20/10/13
 * Time: 9:45
 */
public class Button
{
    private String text;
    private final float xPos;
    private final float yPos;


    public Button(String text, float xPos, float yPos)
    {
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public void click()
    {
        System.out.println("Clicked!");
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.WHITE);
        g.drawRect((int) xPos, (int) yPos, (this.text.length() * 7) + 14, 20);
        g.drawString(this.text, (int) xPos + 12, (int) yPos + 15);
    }
}
