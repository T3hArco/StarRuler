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
    private String type;
    private int xOffset, yOffset;
    private int level;
    private List<Humanoid> humanoidList = new ArrayList<Humanoid>();

    public ShipElement(String type, int xOffset, int yOffset, int level)
    {
        this.type = type;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.level = level;
    }
}
