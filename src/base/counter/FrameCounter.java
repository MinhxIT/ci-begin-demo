package base.counter;

public class FrameCounter {
    int frameCount;// số frame
    int maxCount; // số frame lớn nhất

    public FrameCounter(int maxCount) {
        this.maxCount = maxCount;
        this.frameCount = 0;
    }

    public boolean run() {// trả ra 1 cái bool, nếu là true, xong vieech đếm, false: chưa xong
        if (frameCount >= maxCount) { // nếu frameCOunt lớn hơn max thì là đếm xong
            return true; // đã đêm xong
        } else {
            frameCount++;
            return false;
        }

    }

    public void reset() {
        this.frameCount = 0;
    }
}
