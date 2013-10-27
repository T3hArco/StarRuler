package me.arco.dev.items;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 26/10/13
 * Time: 16:40
 */
public class Inventory
{
    List<Item> itemList = new ArrayList<Item>();

    public List<Item> getItemList()
    {
        return itemList;
    }

    public void addItem(int id)
    {
        switch(id)
        {
            case 0:
                itemList.add(new Item("A test!", "Well, game developers need to test too, no? :/", 64, "./src/me/arco/dev/items/images/trollvase.jpg", null));
                break;

            case 1:
                itemList.add(new Item("Shield upgrade", "Let's add some power to those shields", 1, "./src/me/arco/dev/items/images/shield_green.png", Item.Type.UPGRADE_SHIELD));
                break;

            case 2:
                itemList.add(new Item("Health upgrade", "Magically changes the physical properties of steel. Wow, amazing technology!", 1, "./src/me/arco/dev/items/images/heart.png", Item.Type.UPGRADE_HEALTH));
                break;

            case 3:
                itemList.add(new Item("Population upgrade", "This spawns people without any phyisical contact, weirdly enough", 1, "./src/me/arco/dev/items/images/person.png", Item.Type.UPGRADE_POPULATION));
                break;

            default:
                itemList.add(new Item("Nothing", "It'll probably kill you in your sleep ;)", 1, "./src/me/arco/dev/items/images/none.jpg", Item.Type.DAMAGING));
                break;
        }
    }

    public boolean checkIfInventoryFull()
    {
        if(itemList.size() == 24)
        {
            return true;
        }

        return false;
    }

    public Item getItem(int id)
    {
        try
        {
            return itemList.get(id);
        }
        catch(IndexOutOfBoundsException e)
        {
            return new Item("Nothing", "It'll probably kill you in your sleep ;)", 1, "./src/me/arco/dev/items/images/none.jpg", Item.Type.DAMAGING);
        }
    }

    public void draw(Graphics2D g)
    {
        int xOffset = 480;
        int yOffset = 20;
        int count = 0;

        g.setColor(Color.WHITE);
        g.drawRect(470, 10, 250, 150);
        g.setColor(new Color(1f, 0f, 0f, .5f));
        g.fillRect(470, 10, 251, 151);

        for(Item item : itemList)
        {
            if(count % 4 == 0 && count != 0)
            {
                yOffset += 60;
                xOffset = 480;
            }

            item.draw(g, xOffset, yOffset);

            xOffset += 60;
            count++;
        }
    }
}
