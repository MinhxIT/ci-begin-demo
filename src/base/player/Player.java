package base.player;

import base.*;
import base.counter.FrameCounter;
import base.event.KeyEventPress;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject implements Physics {
    public int HP;  // máu
    FrameCounter fireCounter; //
    BoxCollider collider;
    Vector2D velocity;

    public Player() {

        super();
        // load ra chuỗi ảnh để tạo thành nhân vật động
//        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
//                "assets/images/players/straight/0.png",
//                "assets/images/players/straight/1.png",
//                "assets/images/players/straight/2.png",
//                "assets/images/players/straight/3.png",
//                "assets/images/players/straight/4.png",
//                "assets/images/players/straight/5.png",
//                "assets/images/players/straight/6.png"
//        );
        // thực hiện render
        this.HP = Settings.HP_PLAYER; // máu của plaer
        this.renderer = new PlayerAnimator(); // render animation của plaer
        // vị trí hiển thị
        this.position = new Vector2D(Settings.START_PLAYER_POSITION_X, Settings.START_PLAYER_POSITION_Y); // vị trí player
        this.collider = new BoxCollider(32, 48);// kích thước player
        this.fireCounter = new FrameCounter(10); // 10 frame 1 lần bắn
        this.velocity = new Vector2D(0, 0); // gia tốc player
    }

    // hàm di chuyển
    public void move(int velocityX, int velocityY) {
        if (velocityX == 0 && velocityY == 0) {
            this.velocity.set(0, 0);
        } else {
            this.velocity.addThis(velocityX, velocityY);
            this.velocity.set(clamp(velocity.x, -3, 3), clamp(velocity.y, -3, 3));
        }
    }

    public int clamp(float number, float min, float max) {
        if (number < min) {
            return (int) min;
        } else if (number > max) {
            return (int) max;
        } else {
            return (int) number;
        }
        // return number<min?min:
    }

    public void fire() {
        PlayerBulletType1 playerBullet = GameObject.recycle(PlayerBulletType1.class);
        PlayerBulletType1 playerBulletLeft = GameObject.recycle(PlayerBulletType1.class);
        PlayerBulletType1 playerBulletRight = GameObject.recycle(PlayerBulletType1.class);

        playerBullet.velocity.set(0, -3);
        playerBulletLeft.velocity.set(-3, -3);
        playerBulletRight.velocity.set(3, -3);

        playerBullet.position.set(this.position.x, this.position.y); // cho tọa độ của đạn trùng tọa độ với player
        playerBulletRight.position.set(this.position.x, this.position.y);
        playerBulletLeft.position.set(this.position.x, this.position.y);
        this.fireCounter.reset(); // reset lại
    }

    public void takeDamage(int damage) { // hàm nhận damage
        this.HP -= damage; // máu của pkayer bị trừ đi 1 khoảng damage
        if (this.HP <= 0) { // nếu máu player nhỏ hơn 0
            this.destroy(); // bien mat
            HP = 0; // sét máu bằng 0
        }
    }

    @Override
    public void run() {
        int vx = 0;
        int vy = 0;
        if (KeyEventPress.isUpPress) {
            vy -= 1;
        }
        if (KeyEventPress.isDownPress) {
            vy += 1;
        }
        if (KeyEventPress.isLeftPress) {
            vx -= 1;
        }
        if (KeyEventPress.isRightPress) {
            vx += 1;
        }

        boolean fireCounterRun = this.fireCounter.run();
        if (KeyEventPress.isMousePress && fireCounterRun || KeyEventPress.isSpacePress && fireCounterRun) {
            this.fire();
        }
        this.move(vx, vy);
        this.position.addThis(this.velocity);//
    }


    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
