package me.arco.dev.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Arco
 * Date: 27/10/13
 * Time: 19:15
 */
public abstract class UI
{
    private final List<Button> buttons = new ArrayList<Button>();
    private final int[][] idList = new int[735][745];

    public UI()
    {
        for (int i = 0; i < idList.length; i++)
        {
            for (int j = 0; j < idList[0].length; j++)
            {
                idList[i][j] = -1;
            }
        }
    }

    public void addButton(String text, float xPos, float yPos, int height, int width, String assignedAction)
    {

        xFor:
        for (int i = (int) yPos; i < yPos + 20; i++)
        {
            for (int j = (int) xPos; j < xPos + (text.length() * 7) + 14; j++)
            {
                try
                {
                    idList[j][i] = buttons.size();
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    System.err.println("[ERROR] Newly generated button tried to go out of bounds! Generated a bit of it."); // Dirty, but functional
                    break xFor; // Stop our for, we're not going to get anything anyways.
                }
            }
        }

        buttons.add(new Button(text, xPos, yPos));
    }

    public void addButton(String text, float xPos, float yPos, int height, int width, String assignedAction, boolean toggleRender)
    {

        xFor:
        for (int i = (int) yPos; i < yPos + 20; i++)
        {
            for (int j = (int) xPos; j < xPos + (text.length() * 7) + 14; j++)
            {
                try
                {
                    idList[j][i] = buttons.size();
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    System.err.println("[ERROR] Newly generated button tried to go out of bounds! Generated a bit of it."); // Dirty, but functional
                    break xFor; // Stop our for, we're not going to get anything anyways.
                }
            }
        }

        buttons.add(new Button(text, xPos, yPos, toggleRender));
    }

    public int[][] getIdList()
    {
        return idList;
    }

    public boolean isButtonAtLocation(int xPos, int yPos)
    {
        return idList[xPos][yPos] != -1;

    }

    public Button getButtonById(int id)
    {
        try
        {
            return buttons.get(id);
        } catch (IndexOutOfBoundsException e)
        {
            System.err.println("[ERROR] Nonexistent button caught!");
            return new Button("null", 500, 500);
        }
    }

    public List<Button> getButtons()
    {
        return buttons;
    }

    public int getCurrentButtonListLength()
    {
        return buttons.size();
    }

    public void clearButtons()
    {
        for (int i = 0; i < idList.length; i++)
        {
            for (int j = 0; j < idList[0].length; j++)
            {
                idList[i][j] = -1;
            }
        }

        buttons.clear();

        System.out.println("[INFO] Cleared all buttons from screen. Have fun.");
    }

    public abstract void draw(Graphics2D g, double shipShield, double shipHealth);
}
