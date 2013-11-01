package me.arco.dev.entities.ship;

import me.arco.dev.entities.living.Humanoid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 7/10/13
 * Time: 21:13
 */
public class ShipElement
{
    public static enum Offset
    {
        TOP, BOTTOM, LEFT, RIGHT, TOP_RIGHT, TOP_LEFT, BOTTOM_RIGHT, BOTTOM_LEFT
    }

    private String type;
    private int level;
    private Offset offset;
    private List<Humanoid> humanoids = new ArrayList<Humanoid>();

    public ShipElement(String type, int level, Offset offsets)
    {
        this.type = type;
        this.level = level;
        this.offset = offsets;
    }

    public ShipElement(String type, int level, Offset offsets, List<Humanoid> humanoids)
    {
        this.type = type;
        this.level = level;
        this.offset = offsets;
        this.humanoids = humanoids;
    }

    public void addHumanoid(Humanoid h)
    {
        humanoids.add(h);
    }

    public String getType()
    {
        return type;
    }

    public Offset getOffset()
    {
        return offset;
    }
}
