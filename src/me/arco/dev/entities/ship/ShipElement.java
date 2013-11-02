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

    public static enum Type
    {
        VOID, HANGAR, HOSPITAL, BRIDGE, ENGINE
    }

    private Type type;
    private int level;
    private Offset offset;
    private List<Humanoid> humanoids = new ArrayList<Humanoid>();
    private List<Offset> offsetList = new ArrayList<Offset>();  // dit kan blijkbaar geen 'offsets' heten?
    private List<Type> types = new ArrayList<Type>();

    public ShipElement(Type type, int level, Offset offsets)
    {
        this.type = type;
        this.level = level;
        this.offset = offsets;
    }

    public ShipElement()
    {
        types.add(Type.BRIDGE);
        types.add(Type.ENGINE);
        types.add(Type.HANGAR);
        types.add(Type.HOSPITAL);
        types.add(Type.VOID);

        offsetList.add(Offset.BOTTOM_RIGHT);
        offsetList.add(Offset.BOTTOM);
        offsetList.add(Offset.BOTTOM_LEFT);
        offsetList.add(Offset.LEFT);
        offsetList.add(Offset.RIGHT);
        offsetList.add(Offset.TOP);
        offsetList.add(Offset.TOP_LEFT);
        offsetList.add(Offset.TOP_RIGHT);
    }

    public ShipElement(Type type, int level, Offset offsets, List<Humanoid> humanoids)
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

    public int getHumanoidCount()
    {
        return humanoids.size();
    }

    public Type getType()
    {
        return type;
    }

    public List<Type> getTypes()
    {
        return types;
    }

    public List<Offset> getOffsetList()
    {
        return offsetList;
    }

    public Offset getOffset()
    {
        return offset;
    }
}
