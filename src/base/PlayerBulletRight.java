package base;

import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBulletRight extends GameObject{
    public PlayerBulletRight() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/sphere-bullets/0.png",
                "assets/images/sphere-bullets/1.png",
                "assets/images/sphere-bullets/2.png",
                "assets/images/sphere-bullets/3.png"
        );

        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0, 0);
    }
    @Override
    public void run() {
        this.move();
    }

    public void move() {
        this.position.addThis(3,-3);
    }
}
