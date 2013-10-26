package me.arco.dev.items;

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
                itemList.add(new Item("A test!", "Well, game developers need to test too, no? :/", 64, "/me/arco/dev/items/images/rollvase.jpg", null));
                break;

            case 1:
                itemList.add(new Item("Shield upgrade", "Let's add some power to those shields", 1, "/me/arco/dev/items/images/shield_green.png", Item.Type.UPGRADE_SHIELD));
                break;

            default:
                itemList.add(new Item("Nothing", "It'll probably kill you in your sleep ;)", 1, "/me/arco/dev/items/images/none.jpg", Item.Type.DAMAGING));
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
            return new Item("Nothing", "It'll probably kill you in your sleep ;)", 1, "images/none.jpg", Item.Type.DAMAGING);
        }
    }
}
