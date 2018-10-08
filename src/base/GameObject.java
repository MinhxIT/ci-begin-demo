package base;

import base.physics.Physics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {

    public static ArrayList<GameObject> gameObjects = new ArrayList<>(); // chứa tất cả class gameObject
    public static ArrayList<GameObject> newGameObjects = new ArrayList<>(); // khắc phục lỗi
    public boolean isActive;

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

    public static <E extends GameObject> E recycle(Class<E> childClass) {
        //1. Kiểm tra có gameObject thỏa mãn yêu cầu ko: (isActive == false && go instance chilClass) ko
        for (int i = 0; i < gameObjects.size(); i++) {

            GameObject go = gameObjects.get(i);
            if (!go.isActive && go.getClass().isAssignableFrom(childClass)) { // kiểm tra go có thuộc class này ko
                //
                go.isActive = true;
                return (E) go;
            }
        }
        // có thì dùng lại
        // không có thì tạo mới
        // trả về gameObject
        return create(childClass); // tạo mới
    }

    // va chạm
    public static <E extends GameObject> E intersect(Class<E> childClass, Physics physics) {
        for (int i = 0; i < gameObjects.size(); i++) {

            GameObject go = gameObjects.get(i);
            if (go.isActive && go.getClass().isAssignableFrom(childClass) && go instanceof Physics) {
                Physics physicsGo = (Physics) go;
                boolean intersected = physics.getBoxCollider().intersect(physicsGo, (GameObject) physics);
                if (intersected) {
                    return (E) physicsGo;
                }
            }
        }
        return null;
    }// kiểm tra 1 object truyền vào n có va cham với đồi tượng nào ko

    public static void runAll() { // chạy tất cả game object
        for (int i = 0; i < gameObjects.size(); i++) {

            GameObject go = gameObjects.get(i);
            if (go.isActive) { // nếu gameObject kích họa thì mới run
                go.run();
            }
        }
        //System.out.println(gameObjects.size());
//        for (GameObject go : gameObjects) {
//            go.run();
//        }
    }

    public static void renderAll(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++) {

            GameObject go = gameObjects.get(i);
            if (go.isActive) { // nếu gameObject kích họa thì mới run
                go.render(g);
            }
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public Renderer renderer;// có: ảnh, vị trí x, vị trí y,
    public Vector2D position;

    public GameObject() {
        this.isActive = true;
    }

    public GameObject(BufferedImage image) {
        this.isActive = true;
        this.position = new Vector2D(0, 0);
    }

    public void destroy() { // hủy
        this.isActive = false;
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
