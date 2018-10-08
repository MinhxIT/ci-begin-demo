package base.action;

import base.GameObject;

public class ActionRepeat extends Action {
    // chạy đi chạy lại 1 thằng liên tục
    // chỉ cần truyền 1 thằng
    Action action;
    public ActionRepeat(Action action){
        this.action = action;
    }
    @Override
    public void run(GameObject master) {
        if (!action.isDone){
            // chưa done thì chạy
            action.run(master);
        }else {
            action.reset(); // quaY LẠI THỜI điểm n chưa chạy
        }
    }

    @Override
    public void reset() { // không cần làm gì
    }
}
