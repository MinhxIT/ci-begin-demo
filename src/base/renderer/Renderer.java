package base.renderer;

import base.GameObject;

import java.awt.*;

public abstract class Renderer {
    // hành động render
    public abstract void render(
            Graphics g,
            GameObject master
    );
}
