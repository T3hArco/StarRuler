package me.arco.dev.gamestate;

/**
 * Created with IntelliJ IDEA.
 * User: arco
 * Date: 12/3/13
 * Time: 9:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class GamestateHandler
{
    public enum GameState
    {
        MAINSCREEN, INGAME, CREDITS, GAME_OVER
    }

    private GameState gamestate;

    public void setGamestate(GameState gamestate)
    {
        this.gamestate = gamestate;
    }

    public GameState getGamestate()
    {
        return this.gamestate;
    }
}
