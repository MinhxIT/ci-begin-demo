package base.player;

import base.physics.BoxCollider;
import base.GameObject;
import base.physics.Physics;
import base.Vector2D;
import base.enemy.Enemy;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject implements Physics {
    Vector2D velocity; // gia tốc bắn đạn
    BoxCollider collider; //

    public PlayerBullet() {
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/bullets/pink.png"
        ); // ảnh đạn
        this.renderer = new AnimationRenderer(images); // chạy ảnh động đạn
        this.position = new Vector2D(0, 0); // vị trí của đạn
        this.velocity = new Vector2D(0, 0); // tốc độ di chuyển
        this.collider = new BoxCollider(16, 16); // kích thước đạn
    }

    @Override
    public void run() {
        Enemy enemy = GameObject.intersect(Enemy.class, this); // xét va chamh của enemy voi player
        if (enemy != null) { // nếu enemy có va chạm với đạn
            enemy.destroy(); // địch nát
            this.destroy(); // đạn nát
            return;
        }
        if (this.position.y < 0) { // nếu chạy quá màn hình thì hủy
            this.destroy(); //
            return;
        }
        this.position.addThis(this.velocity.x, this.velocity.y); //
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}