package me.arco.dev.entities.enemy;

import me.arco.dev.entities.Entity;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 18/11/13
 * Time: 19:49
 */
public class Enemy extends Entity
{
    private int health = 1000;
    private int shield = 1000;
    private int xOffset = 1000;
    private boolean dead = false;
    private Random random = new Random();
    private int yOffset = random.nextInt(1000);

    public static enum Type
    {
        WIJNSINATOR, WIJNSINATOR_ANGRY, KAMIELINATOR, NICPISAURUS, GENERIC
    }

    private Image wijnsinator, wijnsinatorangry;
    private Type types;

    public Enemy(float x, float y, float width, float height, float motionX, float motionY, String type)
    {
        super(x, y, width, height, motionX, motionY, type);
        wijnsinator = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/enemy/images/Wijns.png"));
        wijnsinatorangry = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/enemy/images/WijnsULTIMATE.png"));
    }

    public void setType(Type type)
    {
        this.types = type;
    }

    public void hit()
    {
        if(shield <= 0)
        {
            health -= random.nextInt(15) * ((double) 2 / 3);
            shield = 0;
        }
        else
        {
            shield -= random.nextInt(15) / ((double) 2 / 2);
            if(random.nextBoolean())
            {
                health -= random.nextInt(15) / ((double) 2 / 4);
            }
        }

        if(health <= 0)
        {
            dead = true;
            health = 0;
        }
    }

    @Override
    public void draw(Graphics2D g)
    {
        boolean reached = false;

        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y - 20, 50 - xOffset, 10);

        g.setColor(new Color(23, 128, 3));
        g.fillRect((int) x, (int) y - 20, 50 - (health / 1000 * 50) - xOffset, 10);

        switch (types)
        {
            case WIJNSINATOR:
                g.drawImage(wijnsinator, (int) x - xOffset, (int) y - yOffset, null);

                if(x - xOffset <= x)
                {
                    xOffset--;
                    yOffset--;
                    Math.sin(yOffset);
                }
                else
                {
                    types = Type.WIJNSINATOR_ANGRY;
                }

                break;

            case WIJNSINATOR_ANGRY:
                g.drawImage(wijnsinatorangry, (int) x, (int) y, null);
                break;

            case KAMIELINATOR:
                break;

            case NICPISAURUS:
                break;

            default:

            case GENERIC:

                break;
        }
    }
}
