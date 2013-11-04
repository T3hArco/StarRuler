package me.arco.dev.entities.ship;

import me.arco.dev.entities.Entity;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

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
    private Image hangarImage;
    private Image voidImage;
    private Image hospitalImage;
    private Image bridgeImage;
    private Image engineImage;
    private Image tempImage;

    public Ship(float x, float y, float motionX, float motionY, String type) throws IOException
    {
        super(x, y, motionX, motionY, type);
        this.motionX = 1;
        AtomicReference<List<ShipElement.Type>> types = new AtomicReference<List<ShipElement.Type>>(new ShipElement().getTypes());
        List<ShipElement.Offset> offsetList = new ShipElement().getOffsetList();

        for(int i = 0; i < 20; i++)
        {
            shipElements.add(new ShipElement(types.get().get(random.nextInt(types.get().size())), 1, offsetList.get(random.nextInt(offsetList.size()))));
        }

        hangarImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/hangar.png"));
        voidImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/void.png"));
        hospitalImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/hospital.png"));
        bridgeImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/bridge.png"));
        engineImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/ship/images/engines.png"));
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
            switch(shipElement.getType())
            {
                case VOID:
                    tempImage = voidImage;
                    break;

                case HANGAR:
                    tempImage = hangarImage;
                    break;

                case HOSPITAL:
                    tempImage = hospitalImage;
                    break;

                case BRIDGE:
                    tempImage = bridgeImage;
                    break;

                case ENGINE:
                    tempImage = engineImage;
                    break;

                default:
                    tempImage = voidImage;
                    break;
            }


            switch(shipElement.getOffset())
            {
                case BOTTOM:
                    g.drawImage(tempImage, (int) x, (int) y - (bottomOffset * elementPx), null);
                    bottomOffset++;
                    break;

                case TOP:
                    g.drawImage(tempImage, (int) x, (int) y + (topOffset * elementPx), null);
                    topOffset++;
                    break;

                case LEFT:
                    g.drawImage(tempImage, (int) x - (leftOffset * elementPx), (int) y, null);
                    leftOffset++;
                    break;

                case RIGHT:
                    g.drawImage(tempImage, (int) x + (rightOffset * elementPx), (int) y, null);
                    rightOffset++;
                    break;

                case TOP_LEFT:
                    g.drawImage(tempImage, (int) x - (topLeftOffset * elementPx), (int) y + (topLeftOffset * elementPx), null);
                    topLeftOffset++;
                    break;

                case TOP_RIGHT:
                    g.drawImage(tempImage, (int) x + (topRightOffset * elementPx), (int) y + (topRightOffset * elementPx), null);
                    topRightOffset++;
                    break;

                case BOTTOM_LEFT:
                    g.drawImage(tempImage, (int) x - (bottomLeftOffset * elementPx), (int) y - (bottomLeftOffset * elementPx), null);
                    bottomLeftOffset++;
                    break;

                case BOTTOM_RIGHT:
                    g.drawImage(tempImage, (int) x + (bottomRightOffset * elementPx), (int) y - (bottomRightOffset * elementPx), null);
                    bottomRightOffset++;
                    break;
            }
        }
    }

    public int getTotalPopulation()
    {
        int total = 0;

        for(ShipElement shipElement : shipElements)
        {
            total += shipElement.getHumanoidCount();
        }

        return total;
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

