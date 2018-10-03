package base;

import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class PlayerBulletLeft extends GameObject{
    public PlayerBulletLeft() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/player-bullets/a/0.png",
                "assets/images/player-bullets/a/1.png",
                "assets/images/player-bullets/a/2.png",
                "assets/images/player-bullets/a/3.png"
        );

        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0, 0);
    }
    @Override
    public void run() {
        this.move();
    }

    public void move() {
        this.position.addThis(-3,-3);
    }
}

