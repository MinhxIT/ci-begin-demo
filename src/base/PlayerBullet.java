package base;

import base.renderer.AnimationRenderer;
import game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class PlayerBullet extends GameObject {

    public PlayerBullet() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/bullets/pink.png"
        );

        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0, 0);
    }
    @Override
    public void run() {
        this.move();
    }

    public void move() {
        this.position.addThis(0,-3 );
    }
}
