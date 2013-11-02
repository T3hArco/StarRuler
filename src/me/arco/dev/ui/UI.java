package me.arco.dev.ui;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 27/10/13
 * Time: 19:15
 */
public abstract class UI
{
    private List<Button> buttons = new ArrayList<Button>();

    public void addButton(float xPos, float yPos, int height, int width)
    {
        // TODO: implement
    }

    public abstract void draw(Graphics2D g, double shipShield, double shipHealth);
}
