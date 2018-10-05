package base;

import base.counter.FrameCounter;
import base.renderer.AnimationRenderer;
import game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics {
    Random random = new Random();
    int move = -2 + random.nextInt(2); // di chuyển

    FrameCounter fireCounter;
    BoxCollider collider;

    public Enemy() {
        super();
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
        this.position = new Vector2D(200, 100);
        this.collider = new BoxCollider(28, 28);
        this.fireCounter = new FrameCounter(20); // 10 frame 1 lần bắn

    }

    public void fire() {
        EnemyBullet enemyBullet = GameObject.recycle(EnemyBullet.class);
        enemyBullet.velocity.set(0, 3);
        enemyBullet.position.set(this.position.x, this.position.y);
        this.fireCounter.reset();
    }

    @Override
    public void run() {
        this.move();
        boolean fireCounterRun = this.fireCounter.run();
        if (Player.HP > 0) {
            if (fireCounterRun) {
                this.fire();
            }
        }
    }

    public void move() {
        if (this.position.x > 0 || this.position.y < 384) {
            this.position.x += move;
            this.position.y += move;
        } else {
            this.position.x -= move;
            this.position.y -= move;
        }
    }


    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
