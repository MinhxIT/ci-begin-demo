package base.enemy;

import base.*;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.player.Player;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyBullet extends GameObject implements Physics {
    public int damage;
    Vector2D velocity; // gia tốc
    BoxCollider collider;
    public EnemyBullet() {
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages("assets/images/enemies/bullets/blue.png");// ảnh đạn
        this.renderer = new AnimationRenderer(images); // vẽ ảnh động
        this.position = new Vector2D(0, 0); // vị trí
        this.velocity = new Vector2D(0, 0); // gia tốc
        this.collider = new BoxCollider(16, 16); // kích thước
        damage = Settings.DAMAGE_ENEMY_BULLET; // damage

    }

    @Override
    public void run() {
        Player player = GameObject.intersect(Player.class, this);
        if (player != null) { // nếu player trúng đạn
            player.takeDamage(damage);
            this.destroy();
        }
        this.position.addThis(this.velocity.x, this.velocity.y);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
