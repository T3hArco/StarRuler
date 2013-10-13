package me.arco.dev;

import java.awt.event.MouseListener;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 13/10/13
 * Time: 10:31
 */
public class MouseEventManager implements MouseListener
{
    private int x, y;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e)
    {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e)
    {

    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
