package me.arco.dev;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:44
 */
public class Entity
{
    private String type;
    private int listID;
    private int xpos;
    private int ypos;
    private int xrender;
    private int yrender;

    public Entity(String type, int listID, int xpos, int ypos, int xrender, int yrender)
    {
        this.type = type;
        this.listID = listID;
        this.xpos = xpos;
        this.ypos = ypos;
        this.xrender = xrender;
        this.yrender = yrender;
    }

    public String getType()
    {
        return type;
    }

    public int getXrender()
    {
        return xrender;
    }

    public int getYrender()
    {
        return yrender;
    }

    public void setXrender(int xrender)
    {
        this.xrender = xrender;
    }

    public void setYrender(int yrender)
    {
        this.yrender = yrender;
    }
}
