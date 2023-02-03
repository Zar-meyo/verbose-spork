package Jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener
{
    private static MouseListener instance;

    private double scrollX;
    private double scrollY;
    private double posX;
    private double posY;
    private double lastX;
    private double lastY;

    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener()
    {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.posX = 0.0;
        this.posY = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get()
    {
        if (MouseListener.instance == null)
            MouseListener.instance = new MouseListener();

        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xPos, double yPos)
    {
        MouseListener instance = MouseListener.get();
        instance.lastX = instance.posX;
        instance.lastY = instance.posY;
        instance.posX = xPos;
        instance.posY = yPos;
        instance.isDragging =
                instance.mouseButtonPressed[0] || instance.mouseButtonPressed[1] || instance.mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods)
    {
        MouseListener instance = MouseListener.get();
        if (action == GLFW_PRESS)
        {
            if (button < instance.mouseButtonPressed.length)
            {
                instance.mouseButtonPressed[button] = true;
            }
        }
        else if (action == GLFW_RELEASE)
        {
            if (button < instance.mouseButtonPressed.length)
            {
                instance.mouseButtonPressed[button] = false;
                instance.isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset)
    {
        MouseListener instance = MouseListener.get();
        instance.scrollX = xOffset;
        instance.scrollY = yOffset;
    }

    public static void endFrame()
    {
        MouseListener instance = MouseListener.get();
        instance.scrollX = 0;
        instance.scrollY = 0;
        instance.lastX = instance.posX;
        instance.lastY = instance.posY;
    }

    public static float getX()
    {
        return (float)get().posX;
    }

    public static float getY()
    {
        return (float)get().posY;
    }

    public static float getDx()
    {
        return (float)(get().lastX - get().posX);
    }

    public static float getDy()
    {
        return (float)(get().lastY - get().posY);
    }

    public static float getScrollX()
    {
        return (float)get().scrollX;
    }

    public static float getScrollY()
    {
        return (float) get().scrollY;
    }

    public static boolean isDragging()
    {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button)
    {
        if (button < get().mouseButtonPressed.length)
            return get().mouseButtonPressed[button];
        return false;
    }
}
