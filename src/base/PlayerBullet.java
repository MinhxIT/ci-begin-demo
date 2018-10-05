package base;

import base.renderer.AnimationRenderer;
import game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class PlayerBullet extends GameObject implements Physics {
    Vector2D velocity;
    BoxCollider collider;

    public PlayerBullet() {
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/bullets/pink.png"
        );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(0, 0);
        this.velocity = new Vector2D(0, 0);
        this.collider = new BoxCollider(24, 24);
    }

    @Override
    public void run() {
        Enemy enemy = GameObject.intersect(Enemy.class, this);
        if (enemy != null) { // nếu trúng địch
            enemy.destroy(); // địch nát
            this.destroy(); // đạn nát
            return;
        }
        if (this.position.y < 0) { // nếu chạy quá màn hình thì hủy
            this.destroy(); //
            return;
        }
        this.position.addThis(this.velocity.x, this.velocity.y);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
