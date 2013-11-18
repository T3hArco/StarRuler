package me.arco.dev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

abstract class GameWindow
{

    private final JFrame frame;
    private final JPanel renderingPanel;
    private final BufferedImage frameBuffer;
    private final KeyboardEventManager keyboardEventManager;
    private final MouseEventManager mouseEventManager;
    private final ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/me/arco/dev/icon.png")));

    private final int width;
    private final int height;
    private boolean running;

    GameWindow(String title, int width, int height)
    {
        this.width = width;
        this.height = height;

        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(imageIcon.getImage());

        renderingPanel = new JPanel();
        frame.add(renderingPanel);

        keyboardEventManager = new KeyboardEventManager();
        frame.addKeyListener(keyboardEventManager);

        mouseEventManager = new MouseEventManager();
        frame.addMouseListener(mouseEventManager);
        frame.addMouseMotionListener(mouseEventManager);

        frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void run()
    {
        initialize();
        running = true;

        Timer frameTimer = new Timer();

        while (running)
        {
            while (keyboardEventManager.hasKeyboardEvents())
            {
                KeyboardEvent keyboardEvent = keyboardEventManager.getNextKeyboardEvent();
                handleKeyboardEvent(keyboardEvent);
            }

            while (mouseEventManager.hasMouseEvents())
            {
                MouseEvent mouseEvent = mouseEventManager.getNextMouseEvent();
                handleMouseEvent(mouseEvent);
            }



            update(frameTimer.stop() % 1000);
            frameTimer.set();

			/* clear the framebuffer... (just draw a black rect over the entire window) */
            Graphics bufferRenderHandle = frameBuffer.getGraphics();
            bufferRenderHandle.setColor(Color.BLACK);
            bufferRenderHandle.fillRect(0, 0, 1280, 720);

            render((Graphics2D) bufferRenderHandle);

            swapBuffers();


            //Throttle for CPU usage... (optional)
            long timePassed = frameTimer.check();
            if (timePassed < 20)
            {
                try
                {
                    Thread.sleep(20 - timePassed);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        WindowEvent closeWindowEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindowEvent);
    }

    void swapBuffers()
    {
        Graphics g = renderingPanel.getGraphics();
        g.drawImage(frameBuffer, 0, 0, null);
    }

    void shutdown()
    {
        running = false;
    }

    protected abstract void initialize();

    protected abstract void update(long delta);

    protected abstract void render(Graphics2D renderHandle);

    protected abstract void handleKeyboardEvent(KeyboardEvent event);

    protected abstract void handleMouseEvent(MouseEvent event);
}
