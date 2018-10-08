package base.action;

import base.GameObject;

public abstract class Action {
    public boolean isDone; // đã xong chưa
    public abstract void run(GameObject master); // chủ hàm run
    public abstract void reset();
}
