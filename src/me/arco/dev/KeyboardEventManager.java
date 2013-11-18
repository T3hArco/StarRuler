package me.arco.dev;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class KeyboardEventManager implements KeyListener
{

    private final Stack<KeyboardEvent> keyboardEvents;
    private final Set<Integer> keyDownStates;

    public KeyboardEventManager()
    {
        keyboardEvents = new Stack<KeyboardEvent>();
        keyDownStates = new HashSet<Integer>();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (!keyDownStates.contains(e.getKeyCode()))
        {
            keyboardEvents.push(new KeyboardEvent(e.getKeyCode(), KeyboardEvent.DOWN));
            keyDownStates.add(e.getKeyCode());
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keyboardEvents.push(new KeyboardEvent(e.getKeyCode(), KeyboardEvent.UP));
        keyDownStates.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        //NOPE
    }

    public boolean hasKeyboardEvents()
    {
        return !keyboardEvents.isEmpty();
    }

    public KeyboardEvent getNextKeyboardEvent()
    {
        return keyboardEvents.pop();
    }

}
