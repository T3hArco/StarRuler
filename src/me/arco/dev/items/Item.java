package me.arco.dev.items;


import me.arco.dev.entities.ship.Ship;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 26/10/13
 * Time: 16:31
 */
public class Item
{
    public static enum Type
    {
        HEALING, DAMAGING, REPRODUCING, UPGRADE_WEAPON, UPGRADE_SHIELD, UPGRADE_HEALTH, UPGRADE_POPULATION
    }

    private String name;
    private String description;
    private int amount;
    private String imagePath;
    private Image image;
    private Type type;

    public Item(String name, String description, int amount, String imagePath, Type type)
    {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.imagePath = imagePath;
        this.type = type;

        // TODO: validate whether image exists

        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath));
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getAmount()
    {
        return amount;
    }

    public Type getType()
    {
        return type;
    }

    public void draw(Graphics2D g, float xPos, float yPos)
    {
        g.setColor(Color.WHITE);
        g.drawRect((int) xPos-2, (int) yPos-1, 50, 50);
        g.drawImage(image, (int) xPos, (int) yPos, null);
    }

    public void use(Ship ship)
    {
        switch(type)
        {
            case HEALING:
                ship.addToHealth(250);
                break;

            case DAMAGING:
                ship.removeFromHealth(250);
                ship.removeFromShield(100);
                break;

            case REPRODUCING:

                break;

            case UPGRADE_WEAPON:

                break;

            case UPGRADE_SHIELD:

                break;

            case UPGRADE_HEALTH:

                break;

            case UPGRADE_POPULATION:

                break;
        }
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", imagePath='" + imagePath + '\'' +
                ", image=" + image +
                ", type=" + type +
                '}';
    }
}
