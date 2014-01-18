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
    private boolean toggled = false;
    private boolean render;
    private boolean canBeClosed;
    private boolean clickable = true;
    private boolean togglable = true;

    public Button(String text, float xPos, float yPos, boolean render)
    {
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
        this.render = render;
        this.canBeClosed = true;
    }

    public Button(String text, float xPos, float yPos)
    {
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
        this.render = true;
        this.canBeClosed = false;
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
        if(clickable)
        {
            toggled ^= true;
        }
    }

    public boolean isToggled()
    {
        return toggled;
    }

    public void untoggle()
    {
        toggled = false;
    }

    public boolean isRender()
    {
        return render;
    }

    public void toggleRender()
    {
        this.render ^= true;
    }

    public void makeUntogglable()
    {
        togglable = false;
    }

    public boolean isTogglable()
    {
        return togglable;
    }

    public void renderOff()
    {
        this.render = false;
    }

    public void renderOn()
    {
        this.render = true;
    }

    public void makeUnclickable()
    {
        this.clickable = false;
    }

    public void disable()
    {
        makeUntogglable();
        makeUnclickable();
        renderOff();
    }

    public boolean isCanBeClosed()
    {
        return canBeClosed;
    }

    public void draw(Graphics2D g)
    {
        if (togglable)
        {
            g.setColor(Color.WHITE);
            g.drawRect((int) xPos, (int) yPos, (this.text.length() * 7) + 20, 20);
            g.drawString(this.text, (int) xPos + 12, (int) yPos + 15);
        } else
        {
            renderOff();
        }
    }
}
