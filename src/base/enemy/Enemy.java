package base.enemy;

import base.physics.BoxCollider;
import base.GameObject;
import base.physics.Physics;
import base.Vector2D;
import base.action.Action;
import base.action.ActionRepeat;
import base.action.ActionSequence;
import base.action.ActionWait;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics {
    Random random = new Random();
    int move = -2 + random.nextInt(2); // di chuyển

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
        ActionWait actionWait = new ActionWait(20); // chờ
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
        ActionSequence actionSequence = new ActionSequence(actionWait, actionFire);
        ActionRepeat actionRepeat = new ActionRepeat(actionSequence);
        this.action = actionRepeat;
    }

    public void fire() {
        EnemyBullet enemyBullet = GameObject.recycle(EnemyBullet.class);
        enemyBullet.position.set(this.position.x, this.position.y + 5);
        enemyBullet.velocity.addThis(0,3);
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
