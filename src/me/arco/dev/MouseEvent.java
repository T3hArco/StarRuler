package me.arco.dev;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 13/10/13
 * Time: 10:42
 */
public class MouseEvent
{
    public static enum Type
    {
        CLICKED, PRESSED, RELEASED
    }

    private int x, y;
    private Type type;

    public MouseEvent(int x, int y, Type type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Type getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return "MouseEvent{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
