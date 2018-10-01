package base;

import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    Renderer renderer;// có: ảnh, vị trí x, vị trí y,
    public Vector2D position;
    public static ArrayList<GameObject> gameObjects = new ArrayList<>(); // chứa tất cả class gameObject
    public static ArrayList<GameObject> newGameObjects = new ArrayList<>(); // khắc phục lỗi

    //create(className)
    public static <E extends GameObject> E create(Class<E> childClass) { // hàm tạo
        try {
            GameObject newGameObject = childClass.newInstance();
            newGameObjects.add(newGameObject);
            return (E) newGameObject;
        } catch (Exception e) {
            return null;
        }

    }

    public static void runAll() { // chạy tất cả game object
        for (GameObject go : gameObjects) {
            go.run();
        }
    }

    public static void renderAll(Graphics g) {
        for (GameObject go : gameObjects) {
            go.render(g);
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }


    public GameObject() {

    }

    public GameObject(BufferedImage image) {
        this.position = new Vector2D(0, 0);
    }

    //vẽ
    public void render(Graphics g) {
        if (renderer != null) {
            this.renderer.render(g, this);
        }
    }

    // hàm logic
    public void run() {
    }
}
