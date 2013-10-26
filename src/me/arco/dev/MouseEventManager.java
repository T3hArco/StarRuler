package me.arco.dev;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 13/10/13
 * Time: 10:31
 */
public class MouseEventManager implements MouseListener, MouseMotionListener
{
    private int mouseX, mouseY;
    private Stack<MouseEvent> mouseEvents = new Stack<MouseEvent>();


    @Override
    public void mouseClicked(java.awt.event.MouseEvent e)
    {
        mouseEvents.push(new MouseEvent(e.getX(), e.getY(), MouseEvent.Type.CLICKED));
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e)
    {
        mouseEvents.push(new MouseEvent(e.getX(), e.getY(), MouseEvent.Type.PRESSED));
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e)
    {
        mouseEvents.push(new MouseEvent(e.getX(), e.getY(), MouseEvent.Type.RELEASED));
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e)
    {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e)
    {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    public boolean hasMouseEvents()
    {
        return !mouseEvents.isEmpty();
    }


    public MouseEvent getNextMouseEvent()
    {
        return mouseEvents.pop();
    }

    public int getMouseX()
    {
        return mouseX;
    }

    public int getMouseY()
    {
        return mouseY;
    }

}
