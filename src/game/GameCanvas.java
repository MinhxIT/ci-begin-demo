package game;

import base.*;
import base.enemy.Enemy;
import base.enemy.EnemyType1;
import base.player.Player;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
//    Random random = new Random();
//    int enemyCount = 2+random.nextInt(8);
    Background background;
    Player player;

    public GameCanvas() {
        this.background = GameObject.recycle(Background.class); // nền
        this.player = GameObject.recycle(Player.class); // nhân vật
        EnemyType1 enemyType1 = GameObject.recycle(EnemyType1.class); // quái
    }

    public void render() {
        GameObject.renderAllToBackBuffer();
    } // vẽ lên giao diện

    public void run() {
        GameObject.runAll();
    } // chạy logic

    @Override
    protected void paintComponent(Graphics g) {
        GameObject.renderBackBufferToGame(g);
    }
}
  