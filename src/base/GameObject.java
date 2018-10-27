package base;

import base.physics.Physics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {

    public static ArrayList<GameObject> gameObjects = new ArrayList<>(); // chứa tất cả class gameObject
    public static ArrayList<GameObject> newGameObjects = new ArrayList<>(); // khắc phục lỗi
    public static BufferedImage backBuffer = new BufferedImage(Settings.SCREEN_WIDHT, Settings.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB); // tham số: chiều ngang
    public static Graphics backBufferGraphics = backBuffer.createGraphics();
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
        for (GameObject go : gameObjects) {
            // hướng giải quyết: render hết ảnh vào ảnh có kích thước giống màn hình
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
        for (GameObject go : gameObjects) {
            if (go.isActive && childClass.isAssignableFrom(go.getClass()) && go instanceof Physics) {
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
        for (GameObject go : gameObjects) {
            if (go.isActive) { // nếu gameObject kích họa thì mới run
                go.run();
            }
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }
    public static void renderAllToBackBuffer(){
        for (GameObject go: gameObjects) {
            if (go.isActive) {
                go.render(backBufferGraphics); // render vào ảnh tạm
            }
        }
    }
    public static void renderBackBufferToGame(Graphics g){
        g.drawImage(backBuffer,0,0,null);
    }

    public Renderer renderer;// có: ảnh, vị trí x, vị trí y,
    public Vector2D position;
    public Vector2D anchor;  // mặc định các gameObject tạo ra tâm đều ở giữa

    public GameObject() {
        this.isActive = true;
        this.anchor = new Vector2D(0.5f,0.5f); // mặc định ở giữa
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
