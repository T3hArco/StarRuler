package me.arco.dev.entities.enemy;

import me.arco.dev.entities.Entity;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 18/11/13
 * Time: 19:49
 */
public class Enemy extends Entity
{
    private static enum Type
    {
        WIJNSINATOR, WIJNSINATOR_ANGRY, KAMIELINATOR, NICPISAURUS, GENERIC
    }

    private Image wijnsinator, wijnsinatorangry;
    private Type types;

    public Enemy(float x, float y, float motionX, float motionY, String type)
    {
        super(x, y, motionX, motionY, type);
        wijnsinator = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/enemy/images/Wijns.png"));
        wijnsinatorangry = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/entities/enemy/images/WijnsULTIMATE.png"));
    }

    @Override
    public void draw(Graphics2D g)
    {
        //
        switch (types)
        {
            case WIJNSINATOR:
                g.drawImage(wijnsinator, (int) x, (int) y, null);
                break;

            case WIJNSINATOR_ANGRY:
                g.drawImage(wijnsinatorangry, (int) x, (int) y, null);
                break;

            case KAMIELINATOR:
                break;

            case NICPISAURUS:
                break;

            case GENERIC:

                break;
        }
    }
}
