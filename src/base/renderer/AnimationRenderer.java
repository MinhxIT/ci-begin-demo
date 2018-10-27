package base.renderer;

import base.GameObject;
import base.counter.FrameCounter;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationRenderer extends Renderer { // tạo hiệu ứng ảnh động animation
    // 1 chuỗi những single image
    ArrayList<BufferedImage> images;
    FrameCounter frameCount; // số khung hình
    int currentImage = 0; // ảnh hiện tại là 0
    public AnimationRenderer(String ...urls){
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(urls);
        this.images = images;
        this.frameCount = new FrameCounter(5);
    }
    public AnimationRenderer(ArrayList<BufferedImage> images) {
        this.images = images;
        this.frameCount = new FrameCounter(5); // init trong hàm tạo
    }

    public AnimationRenderer(ArrayList<BufferedImage> images, int frameCount) {
        this.images = images;
        this.frameCount = new FrameCounter(frameCount);
    }
    // đếm số khung hình

    @Override
    public void render(Graphics g, GameObject master) {
        // nếu số lượng ảnh lớn hơn  0
        if (images.size() > 0) {
            BufferedImage image = images.get(currentImage);
            // bắt đầu vẽ bức ảnh đầu tiên
            double x = master.position.x - image.getWidth()*master.anchor.x;
            double y = master.position.y - image.getHeight()*master.anchor.y;
            g.drawImage(image, (int)x,(int)y,null);            // nếu số frame > 5 thì tăng ảnh tăng lên
            if (this.frameCount.run()) {
                currentImage++;// ảnh tăng lên
                if (currentImage >= images.size() - 1) {// nếu ảnh hiện tại đã là ảnh cuối
                    currentImage = 0;// gán ảnh hiện tại thành ảnh ban đầu
                }
                this.frameCount.reset();// và khung hình về giá trị ban đầu
            }
        }
    }
}
