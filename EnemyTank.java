package TankGame04;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    // 在敌人坦克类，使用 Vector 保存多个 Shot
    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {

            // 这里我们判断如果shots size() = 0, 创建一颗子弹，放入到shots集合，并启动
            // 敌方坦克设置为一次只能发射一枚子弹
            if (isLive && shots.size() == 0) {
                Shot s = null;
                // 判断坦克的方向，创建对应的子弹
                switch (getDirection()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                // 启动
                new Thread(s).start();
            }

            // 根据坦克的方向来继续移动
            switch (getDirection()) {
                case 0: // 向上
                    // 让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        // 休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1: // 向右
                    // 让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        // 休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2: // 向下
                    // 让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        // 休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3: // 向左
                    // 让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        // 休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            // 随机改变坦克方向
            setDirection((int) (Math.random() * 4)); // Math.random()返回的是[0, 1)
            if (!isLive) {
                break; // 退出线程
            }

        }
    }
}
