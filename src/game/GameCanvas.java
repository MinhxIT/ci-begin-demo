package game;

import base.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends JPanel {
//    Random random = new Random();
//    int enemyCount = 2+random.nextInt(8);
    Background background;
    Player player;

    public GameCanvas() {
        this.background = GameObject.recycle(Background.class); // nền
        this.player = GameObject.recycle(Player.class); // nhân vật
        Enemy enemy = GameObject.recycle(Enemy.class); // quái
    }

    public void render(Graphics g) {
        GameObject.renderAll(g);
    } // vẽ lên giao diện

    public void run() {
        GameObject.runAll();
    } // chạy logic

    @Override
    protected void paintComponent(Graphics g) {
        this.render(g);
    }
}
  