package base;

import base.counter.FrameCounter;
import base.renderer.AnimationRenderer;
import base.renderer.SingleImageRenderer;
import game.GameCanvas;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {
    FrameCounter fireCounter;

    public Player() {
        // load ra chuỗi ảnh để tạo thành nhân vật động
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/players/straight/0.png",
                "assets/images/players/straight/1.png",
                "assets/images/players/straight/2.png",
                "assets/images/players/straight/3.png",
                "assets/images/players/straight/4.png",
                "assets/images/players/straight/5.png",
                "assets/images/players/straight/6.png"
        );
        // thực hiện render
        this.renderer = new AnimationRenderer(images);
        // vị trí hiển thị
        this.position = new Vector2D(Settings.START_PLAYER_POSITION_X, Settings.START_PLAYER_POSITION_Y);
        this.fireCounter = new FrameCounter(10); // 10 frame 1 lần bắn
    }

    @Override
    public void run() {
        if (KeyEventPress.isUpPress) {
            this.move(0, -2);
        }
        if (KeyEventPress.isDownPress) {
            this.move(0, 2);
        }
        if (KeyEventPress.isLeftPress) {
            this.move(-2, 0);
        }
        if (KeyEventPress.isRightPress) {
            this.move(2, 0);
        }
        boolean fireCounterRun = this.fireCounter.run();
        if (KeyEventPress.isSpacePress && fireCounterRun) {
            this.fire();
        }
    }

    public void fire() {
        PlayerBullet playerBullet = GameObject.create(PlayerBullet.class);
        playerBullet.position.set(this.position.x, this.position.y); // cho tọa độ của đạn trùng tọa độ với player
        PlayerBulletLeft playerBulletLeft = GameObject.create(PlayerBulletLeft.class);
        playerBulletLeft.position.set(this.position.x, this.position.y); // cho tọa độ của đạn trùng tọa độ với player
        PlayerBulletRight playerBulletRight = GameObject.create(PlayerBulletRight.class);
        playerBulletRight.position.set(this.position.x, this.position.y); // cho tọa độ của đạn trùng tọa độ với player
        this.fireCounter.reset(); // reste lại
    }

    public void move(int translateX, int translateY) {
        this.position.addThis(translateX, translateY);
    }
}
