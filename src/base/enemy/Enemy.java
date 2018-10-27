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

        this.position = new Vector2D(200, 100);
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
        //ActionParallel actionParallel = new ActionParallel(actionMoveLeft, actionFire); // song song bắn và di chuyển

        ActionSequence actionSequence = new ActionSequence(actionWait,actionFire);
        ActionRepeat actionRepeat = new ActionRepeat(actionSequence);

        this.action = actionRepeat;

    }


    public void move() {
        this.position.x -= 3;
        this.position.y += 3;
    }

    @Override
    public void run() {
        // this.move();
        this.action.run(this);
    }
    public void fire() {
        EnemyBullet enemyBullet = GameObject.recycle(EnemyBullet.class);
        enemyBullet.position.set(this.position.x, this.position.y + 5);
        enemyBullet.velocity.addThis(0, 3);
    }

    public void takeDamage(int damage) {
    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }

}
