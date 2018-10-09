package base.enemy;

import base.Background;
import base.action.*;
import base.physics.BoxCollider;
import base.GameObject;
import base.physics.Physics;
import base.Vector2D;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics {
    BoxCollider collider;
    Action action;

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
        this.defineAction();
        //  Actionrepeat: thực hiện lặp lại, trong ActionSequence sẽ là ActionWait 20s sau đó fire
    }

    private void defineAction() {
        ActionWait actionWait = new ActionWait(3); // chờ
        Action actionFire = new Action() {
            @Override
            public void run(GameObject master) {
                fire(); // fire của Action: action
                this.isDone = true;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        Action actionMoveLeft = new Action() {
            @Override
            public void run(GameObject master) {
                if (position.x > 9) {
                    position.addThis(-9, 6);
                }else {
                    this.isDone = false;
                    return;
                }
                this.isDone = true;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        Action actionMoveRight = new Action() {
            @Override
            public void run(GameObject master) {
                position.add(9, 0);
                this.isDone = true;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        // chờ 1 lúc -> vừa bắn vừa đi sang trái -> nếu kịch màn hình, thì dừng lại -> chờ 1 lúc -> phát hiện bị kịch
        // sang phải
        ActionParallel actionParallel = new ActionParallel(actionMoveLeft, actionFire); // song song bắn và di chuyển
        ActionParallel actionParallel1 = new ActionParallel(actionMoveRight,actionFire);

        ActionSequence actionSequence = new ActionSequence(actionWait,actionParallel);
        ActionRepeat actionRepeat = new ActionRepeat(actionSequence);

        this.action = actionRepeat;

    }

    public void fire() {
        EnemyBullet enemyBullet = GameObject.recycle(EnemyBullet.class);
        enemyBullet.position.set(this.position.x, this.position.y + 5);
        enemyBullet.velocity.addThis(0, 3);
    }

    Background background = new Background();

    public void move() {
        this.position.x -= 3;
        this.position.y += 3;
    }

    @Override
    public void run() {
        // this.move();
        this.action.run(this);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
