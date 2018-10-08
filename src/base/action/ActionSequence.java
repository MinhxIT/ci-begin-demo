package base.action;

import base.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionSequence extends Action { // truyền liên tiếp các chuỗi các hành động
    ArrayList<Action> actions; // chuỗi action
    int currentActionIndex; // action hiện tại

    public ActionSequence(Action... actions) { // truyền liên tiếp các action
        this.actions = new ArrayList<>(Arrays.asList(actions)); // mảng truyền vào đang ở dạng thô, để convert mảng này thành mảng dạng arraylist thì dùng câu lệnh
        this.currentActionIndex = 0; // ở thời điểm khởi tạo currentAction = 0
    }

    @Override
    public void run(GameObject master) {
        // chạy từ thằng đầu tiên đến cuối cùng
        if (this.actions.size() > 0 && this.currentActionIndex < this.actions.size() && !this.isDone) {
            // độ lớn chuỗi action > 0, action hiện tại < độ lớn
            Action currentAction = this.actions.get(this.currentActionIndex);
            if (!currentAction.isDone) { // chưa xong thì chạy
                currentAction.run(master);
            } else {
                this.currentActionIndex++; // xong rồi thì nhảy đến action tiếp theo
                if (this.currentActionIndex >= this.actions.size()) { // vướt quá size
                    this.isDone = true; // xong
                }
            }
        }
    }

    @Override
    public void reset() {
        for (Action action : actions) { // tất cả thằng con phảu reset
            action.reset();
        }
        this.currentActionIndex = 0;
        this.isDone = false;

    }
}
