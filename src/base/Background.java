package base;

import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Background extends GameObject {
    public Background() {
        super();
        BufferedImage image = SpriteUtils.loadImage("assets\\images\\background\\0.png");
        this.renderer = new SingleImageRenderer(image);
        this.position = new Vector2D(0,-(image.getHeight()-Settings.SCREEN_HEIGHT));
        this.anchor.set(0,0); // vì set anchor là 0,5 0,5 thì vị trí backgrounmd sẽ ở tâm của background
    }

    @Override
    public void run() { // nếu nền đã lớn hown0 thì return còn chưa lớn hơn thì cứ chạy lên
        if (this.position.y>=0){
            return;
        }else {
            this.position.y+=3; // tốc độ
        }
    }
}
