package base.action;

import base.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionParallel extends Action {
    // chạy các action song song
    ArrayList<Action> actions;

    public ActionParallel(Action... actions) {
        this.actions = new ArrayList<>(Arrays.asList(actions));
    }

    @Override
    public void run(GameObject master) {
        if (this.actions.size() > 0 && !this.isDone) {
            boolean isContinue = true;
            for (Action action : actions) {
                if (!action.isDone){ // nếu chưa done thì chạy
                    action.run(master);
                }else {
                    isContinue = false;
                }

            }// chạy hết các action 1 lượt
            this.isDone = isContinue;
        }
    }

    @Override
    public void reset() {
        for (Action action: actions) {
            action.reset();
        }
        this.isDone = false;
    }
}