package base;

import base.renderer.AnimationRenderer;
import game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject {
    Random random = new Random();
    int positionEnemy = random.nextInt(384);
    int move = -2 + random.nextInt(5);
    public Enemy() {
        // tạo list chứa chuỗi ảnh làm ảnh động
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/level0/black/0.png",
                "assets/images/enemies/level0/black/1.png",
                "assets/images/enemies/level0/black/2.png",
                "assets/images/enemies/level0/black/4.png",
                "assets/images/enemies/level0/black/5.png",
                "assets/images/enemies/level0/black/6.png",
                "assets/images/enemies/level0/black/7.png",
                "assets/images/enemies/level0/black/8.png"
        );

        // render
        this.renderer = new AnimationRenderer(images);
        // vị trí của quái
        this.position = new Vector2D(positionEnemy,positionEnemy);
    }

    @Override
    public void run() {
        this.move();

    }
    public void move(){
        this.position.x +=move;
        this.position.y += move;
    }


}
