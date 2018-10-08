package game;

import base.event.KeyEventPress;
import base.Settings;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    //GameWindow chứa gameLoop -> GameCanvas
    GameCanvas gameCanvas;


    public GameWindow() {
        this.setSize(Settings.SCREEN_WIDHT, Settings.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(GameWindow.EXIT_ON_CLOSE);
        //init game
        this.setupEventListener();
        this.gameCanvas = new GameCanvas();
        this.add(gameCanvas);
        this.setVisible(true);
        this.gameLoop();
    }

    // xử lý sự kiện ấn nút
    private void setupEventListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_W:
                        KeyEventPress.isUpPress = true;
                        break;
                    case KeyEvent.VK_S:
                        KeyEventPress.isDownPress = true;
                        break;
                    case KeyEvent.VK_A:
                        KeyEventPress.isLeftPress = true;
                        break;
                    case KeyEvent.VK_D:
                        KeyEventPress.isRightPress = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        KeyEventPress.isSpacePress = true;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_W:
                        KeyEventPress.isUpPress = false;
                        break;
                    case KeyEvent.VK_S:
                        KeyEventPress.isDownPress = false;
                        break;
                    case KeyEvent.VK_A:
                        KeyEventPress.isLeftPress = false;
                        break;
                    case KeyEvent.VK_D:
                        KeyEventPress.isRightPress = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        KeyEventPress.isSpacePress = false;
                    default:
                        break;
                }
            }

        });
    }

    // vòng lặp game
    public void gameLoop() {
        while (true) {
            try {
                gameCanvas.run();
                this.repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
