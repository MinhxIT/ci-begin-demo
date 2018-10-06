package base;

import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

public class EnemyBullet extends GameObject implements Physics {
    public static int damage;
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
            this.destroy(); // đạn biến mất
            if (Player.HP > 0) { // nếu máu của player vẫn còn
                Player.HP -= EnemyBullet.damage; // mỗi lần bắn trúng thì máu sẽ bị trừ đi số damage
                return;
            } else {
                player.destroy(); // nếu máu hết thì die
            }
            return;
        }
        this.position.addThis(this.velocity.x, this.velocity.y);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
