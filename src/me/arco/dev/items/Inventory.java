package me.arco.dev.items;

import me.arco.dev.entities.ship.Ship;

import java.awt.*;
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
    private final List<Item> itemList = new ArrayList<Item>();
    private int listOffsetX = 480;
    private int listOffsetY = 590;
    private final int[][] idList = new int[735][745];
    private int count = 0;


    public Inventory()
    {
        for(int i = 0; i < idList.length; i++) for(int j = 0; j < idList[0].length; j++) idList[i][j] = -1;
    }

    public int[][] getIdList()
    {
        return idList;
    }

    public boolean isItemAtLocation(int xPos, int yPos)
    {
        return idList[xPos][yPos - 25] != -1;

    }

    public void addItem(int id)
    {
        // TODO refactor this in another function that returns items

        Item item;

        switch(id)
        {
            case 0:
                item = new Item("A test!", "Well, game developers need to test too, no? :/", 64, "/me/arco/dev/items/images/trollvase.jpg", null);
                break;

            case 1:
                item = new Item("Shield upgrade", "Let's add some power to those shields", 1, "/me/arco/dev/items/images/shield_green.png", Item.Type.UPGRADE_SHIELD);
                break;

            case 2:
                item = new Item("Health upgrade", "Magically changes the physical properties of steel. Wow, amazing technology!", 1, "/me/arco/dev/items/images/heart.png", Item.Type.UPGRADE_HEALTH);
                break;

            case 3:
                item = new Item("Population upgrade", "This spawns people without any phyisical contact, weirdly enough", 1, "/me/arco/dev/items/images/person.png", Item.Type.UPGRADE_POPULATION);
                break;

            default:
                item = new Item("Nothing", "It'll probably kill you in your sleep ;)", 1, "/me/arco/dev/items/images/none.jpg", Item.Type.DAMAGING);
                break;

        }

        itemList.add(item);

        xFor:
        for(int i = listOffsetY; i < listOffsetY + 50; i++)
        {
            for(int j = listOffsetX; j < listOffsetX + 50; j++)
            {
                try
                {
                    idList[j][i] = itemList.size();
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    System.err.println("[ERROR] Went out of bounds");
                    break xFor;
                }
            }
        }

        if(count % 4 == 0 && count != 0)
        {
            listOffsetY += 60;
            listOffsetX = 480;
        }

        listOffsetX += 60;
        count++;
    }

    public boolean checkIfInventoryFull()
    {
        if(itemList.size() == 8)
        {
            return true;
        }

        return false;
    }

    public void useItem(Ship ship, int id)
    {
        Item item = itemList.get(id);
        item.use(ship);
        itemList.remove(id);
    }

    public Item getItemById(int id)
    {
        try
        {
            return itemList.get(id);
        }
        catch(IndexOutOfBoundsException e)
        {
            return new Item("Nothing", "It'll probably kill you in your sleep ;)", 1, "/me/arco/dev/items/images/none.jpg", Item.Type.DAMAGING);
        }
    }

    public void draw(Graphics2D g)
    {
        int xOffset = 480;
        int yOffset = 590;
        int count = 0;

        g.setColor(Color.WHITE);
        g.drawRect(470, 580, 250, 128);
        g.setColor(new Color(1f, 0f, 0f, .5f));
        g.fillRect(470, 580, 251, 129);

        if(itemList.isEmpty())
        {
            g.setColor(Color.WHITE);
            g.drawString("Inventory is empty!", xOffset + 68, yOffset + 55);
        }
        else
        {
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
}
