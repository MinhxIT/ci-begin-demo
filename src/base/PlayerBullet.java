package base;

import base.renderer.AnimationRenderer;
import game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject {
    public PlayerBullet() {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/player-bullets/a/0.png",
                "assets/images/player-bullets/a/1.png",
                "assets/images/player-bullets/a/2.png",
                "assets/images/player-bullets/a/3.png"
        );

        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0,0);
    }

    @Override
    public void run() {
        this.position.addThis(0,-3);
    }
//    public void move() {
//        this.position.y -= 1;
//    } // tốc độ đạn di chuyển
}
