package Jade;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene
{
    private boolean changingScene = false;
    private float tomeToChangeScene = 2.0f;

    public LevelEditorScene()
    {
        System.out.println("Inside level editor scene.");
    }

    @Override
    public void Update(float dt)
    {
        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE))
        {
            changingScene = true;
        }

        if (changingScene && tomeToChangeScene > 0)
        {
            tomeToChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;
        } else if (changingScene)
        {
            Window.changeScene(1);
        }
    }
}
