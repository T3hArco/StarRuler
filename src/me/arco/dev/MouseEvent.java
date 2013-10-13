package me.arco.dev;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 13/10/13
 * Time: 10:42
 */
public class MouseEvent
{
    private int x;
    private int y;

    public MouseEvent(int x, int y)
    {
        this.x = x;
        this.y = y;
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
