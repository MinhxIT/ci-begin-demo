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
    int damage;
    public PlayerBullet() {
        super();
        this.position = new Vector2D(0, 0); // vị trí của đạn
        this.velocity = new Vector2D(0, 0); // tốc độ di chuyển
    }

    @Override
    public void run() {
        Enemy enemy = GameObject.intersect(Enemy.class, this); // xét va chamh của enemy voi player
        if (enemy != null) { // nếu enemy có va chạm với đạn
            enemy.takeDamage(damage); // địch nát
            this.hitEnemy(); // đạn nát
            return;
        }
        if (this.position.y < 0) { // nếu chạy quá màn hình thì hủy
            this.destroy(); //
            return;
        }
        this.position.addThis(velocity); //
    }

    public void hitEnemy() {

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
