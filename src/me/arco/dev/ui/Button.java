package me.arco.dev.ui;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 20/10/13
 * Time: 9:45
 */
public class Button
{
    private String text;
    private float xPos, yPos;


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
