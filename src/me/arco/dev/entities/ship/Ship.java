package me.arco.dev.entities.ship;

import me.arco.dev.entities.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 1/10/13
 * Time: 18:50
 */
public class Ship extends Entity
{
    private long timePassed = 0;
    private Random random = new Random();
    private float health, shield = health = 1000;
    private List<ShipElement> shipElements = new ArrayList<ShipElement>();
    private boolean dead = false;
    private int elementPx = 45;

    //debug
    private String[] types = { "hangar", "void", "hospital", "" };

    private BufferedImage hangarImage, voidImage, hospitalImage, tempImage;

    public Ship(float x, float y, float motionX, float motionY, String type) throws IOException
    {
        super(x, y, motionX, motionY, type);
        this.motionX = 1;

        for(int i = 0; i < 3; i++)
        {
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.BOTTOM));
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.TOP));
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.LEFT));    // HOORAY FOR DEBUG!
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.RIGHT));
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.BOTTOM_LEFT));
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.BOTTOM_RIGHT));
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.TOP_LEFT));
            shipElements.add(new ShipElement(types[random.nextInt(types.length)], 1, ShipElement.Offset.TOP_RIGHT));
        }

        hangarImage = ImageIO.read(new File("./src/me/arco/dev/entities/ship/images/hangar.png"));
        voidImage = ImageIO.read(new File("./src/me/arco/dev/entities/ship/images/void.png"));
        hospitalImage = ImageIO.read(new File("./src/me/arco/dev/entities/ship/images/hospital.png"));
    }

    public float getHealth()
    {
        return (health / 1000) * 187;
    }

    public float getShield()
    {
        return (shield / 1000) * 187;
    }

    public void hit(double force)
    {
        if(shield <= 0)
        {
            health -= random.nextInt(15) * (force / 3);
            shield = 0;
        }
        else
        {
            shield -= random.nextInt(15) / (force / 2);
            if(random.nextBoolean())
            {
                health -= random.nextInt(15) / (force / 4);
            }
        }

        if(health <= 0)
        {
            dead = true;
            health = 0;
        }
    }

    public boolean checkIfDead()
    {
        return dead;
    }

    @Override
    public void draw(Graphics2D g)
    {
        //g.setColor(new Color(23, 60, 128));
        g.fillRect((int) x, (int) y, elementPx, elementPx);

        int topOffset = 0, bottomOffset = 0, leftOffset = 0, rightOffset = 0, topLeftOffset = 0, topRightOffset = 0, bottomLeftOffset = 0, bottomRightOffset = 0;

        for(ShipElement shipElement : shipElements)
        {
            if(shipElement.getType().equals("hangar"))
            {
                tempImage = hangarImage;
            }
            else if(shipElement.getType().equals("void"))
            {
                tempImage = voidImage;
            }
            else if(shipElement.getType().equals("hospital"))
            {
                tempImage = hospitalImage;
            }
            else
            {
                tempImage = voidImage;
            }


            switch(shipElement.getOffset())
            {
                case BOTTOM:
                    //g.drawRect((int) x, (int) y - (bottomOffset * elementPx), elementPx, elementPx);
                    g.drawImage(tempImage, (int) x, (int) y - (bottomOffset * elementPx), null);
                    bottomOffset++;
                    break;

                case TOP:
                    //g.drawRect((int) x, (int) y + (topOffset * elementPx), elementPx, elementPx);
                    g.drawImage(tempImage, (int) x, (int) y + (topOffset * elementPx), null);
                    topOffset++;
                    break;

                case LEFT:
                    //g.drawRect((int) x - (leftOffset * elementPx), (int) y, elementPx, elementPx);
                    g.drawImage(tempImage, (int) x - (leftOffset * elementPx), (int) y, null);
                    leftOffset++;
                    break;

                case RIGHT:
                    //g.drawRect((int) x + (rightOffset * elementPx), (int) y, elementPx, elementPx);
                    g.drawImage(tempImage, (int) x + (rightOffset * elementPx), (int) y, null);
                    rightOffset++;
                    break;

                case TOP_LEFT:
                    //g.drawRect((int) x - (topLeftOffset * elementPx), (int) y + (topLeftOffset * elementPx), elementPx, elementPx);
                    g.drawImage(tempImage, (int) x - (topLeftOffset * elementPx), (int) y + (topLeftOffset * elementPx), null);
                    topLeftOffset++;
                    break;

                case TOP_RIGHT:
                    //g.drawRect((int) x + (topRightOffset * elementPx), (int) y + (topRightOffset * elementPx), elementPx, elementPx);
                    g.drawImage(tempImage, (int) x + (topRightOffset * elementPx), (int) y + (topRightOffset * elementPx), null);
                    topRightOffset++;
                    break;

                case BOTTOM_LEFT:
                    //g.drawRect((int) x - (bottomLeftOffset * elementPx), (int) y - (bottomLeftOffset * elementPx), elementPx, elementPx);
                    g.drawImage(tempImage, (int) x - (bottomLeftOffset * elementPx), (int) y - (bottomLeftOffset * elementPx), null);
                    bottomLeftOffset++;
                    break;

                case BOTTOM_RIGHT:
                    //g.drawRect((int) x + (bottomRightOffset * elementPx), (int) y - (bottomRightOffset * elementPx), elementPx, elementPx);
                    g.drawImage(tempImage, (int) x + (bottomRightOffset * elementPx), (int) y - (bottomRightOffset * elementPx), null);
                    bottomRightOffset++;
                    break;
            }
        }
    }

    @Override
    public void update(long delta)
    {
        timePassed += delta;
        if (timePassed > 10000)
        {
            timePassed = 0;
            motionX *= -1;
        }
        super.update(delta);
    }
}

