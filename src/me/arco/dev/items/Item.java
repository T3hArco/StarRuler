package me.arco.dev.items;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private BufferedImage image;
    private Type type;

    public Item(String name, String description, int amount, String imagePath, Type type)
    {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.imagePath = imagePath;
        this.type = type;

        try
        {
            image = ImageIO.read(new File(imagePath));
        }
        catch (IOException e)
        {
            System.err.println("ERROR: Someone tried to mount an image that doesn't exist!");
            try
            {
                image = ImageIO.read(new File("./src/me/arco/dev/items/images/none.jpg"));
            }
            catch (IOException e1)
            {
                System.err.println("ERROR: Game halted, images not loadable");
            }
        }
    }

    public void draw(Graphics2D g, float xPos, float yPos)
    {
        g.setColor(Color.WHITE);
        g.drawRect((int) xPos-2, (int) yPos-1, 50, 50);
        g.drawImage(image, (int) xPos, (int) yPos, null);
    }

}
