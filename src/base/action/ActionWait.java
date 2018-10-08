package base.action;

import base.GameObject;
import base.counter.FrameCounter;

public class ActionWait extends Action { // đợi 1 thời gian
    FrameCounter counter;
    public ActionWait(int frame){ // đơn vị này chờ bao nhiêu frame
        this.isDone = false; // chưa xong
        this.counter = new FrameCounter(frame); // chờ số frame
    }
    @Override
    public void run(GameObject master) {
        if (this.counter.run()){ // đến xong thì done
            this.isDone = true;
        }
    }

    @Override
    public void reset() {
        this.counter.reset();
        this.isDone = false;
    }
}
