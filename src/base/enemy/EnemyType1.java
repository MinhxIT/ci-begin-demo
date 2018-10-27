package base.enemy;

import base.physics.BoxCollider;
import base.renderer.AnimationRenderer;
import base.renderer.SingleImageRenderer;

public class EnemyType1 extends Enemy {
    public EnemyType1(){
        super();
        this.renderer = new AnimationRenderer("assets/images/enemies/level0/black/0.png",
                "assets/images/enemies/level0/black/1.png",
                "assets/images/enemies/level0/black/2.png",
                "assets/images/enemies/level0/black/4.png",
                "assets/images/enemies/level0/black/5.png",
                "assets/images/enemies/level0/black/6.png",
                "assets/images/enemies/level0/black/7.png",
                "assets/images/enemies/level0/black/8.png");
        this.collider = new BoxCollider(28,28);
    }

    @Override
    public void takeDamage(int damage) {
        if(damage>0){
            this.destroy();
        }
    }
}
