package game;

import base.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends JPanel {
    Random random = new Random();
    int enemyCount = 2+random.nextInt(8);
    Background background;
    Player player;

    public GameCanvas() {
        this.background = GameObject.create(Background.class); // nền
        this.player = GameObject.create(Player.class); // nhân vật
        Enemy enemy = GameObject.create(Enemy.class);

    }

    public void render(Graphics g) {
        GameObject.renderAll(g);
    }

    public void run() {
        GameObject.runAll();
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.render(g);
    }
}
  