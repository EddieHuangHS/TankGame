package TankGame05;

import java.util.Vector;

@SuppressWarnings({"All"})
public class EnemyTank extends TankGame05.Tank implements Runnable {
    // 在敌人坦克类，使用 Vector 保存多个 Shot
    Vector<TankGame05.Shot> shots = new Vector<>();
    // 增加成员，enemyTank可以得到敌人坦克的vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    // 提供一个方法，将MyPanel的成员Vector<EnemyTank> enemyTanks = new Vector<>()设置到 EnemyTank的成员enemyTanks
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    // 编写方法，判断当前的这个敌人坦克，是否和enemyTanks中的其他坦克发生重叠或碰撞
    public boolean isTouchEnemyTank() {
        switch (getDirection()) {
            case 0: // 上
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 从Vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不和自己比较
                    if (enemyTank != this) {
                        // 如果敌人坦克是上/下
                        // 如果敌人坦克是上/下 x的范围[enemyTank.getX(), enemyTank.getX() + 40]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 当前坦克的左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // 当前坦克的右上角坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 如果敌人坦克是右/左
                        // 如果敌人坦克是右/左 x的范围[enemyTank.getX(), enemyTank.getX() + 60]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 当前坦克的左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // 当前坦克的右上角坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: // 右
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 从Vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不和自己比较
                    if (enemyTank != this) {
                        // 如果敌人坦克是上/下
                        // 如果敌人坦克是上/下 x的范围[enemyTank.getX(), enemyTank.getX() + 40]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 当前坦克的右上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // 当前坦克的右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 如果敌人坦克是右/左
                        // 如果敌人坦克是右/左 x的范围[enemyTank.getX(), enemyTank.getX() + 60]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 当前坦克的右上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // 当前坦克的右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: // 下
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 从Vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不和自己比较
                    if (enemyTank != this) {
                        // 如果敌人坦克是上/下
                        // 如果敌人坦克是上/下 x的范围[enemyTank.getX(), enemyTank.getX() + 40]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 当前坦克的左下角坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // 当前坦克的右下角坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 如果敌人坦克是右/左
                        // 如果敌人坦克是右/左 x的范围[enemyTank.getX(), enemyTank.getX() + 60]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 当前坦克的左下角坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // 当前坦克的右下角坐标 [this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: // 左
                for (int i = 0; i < enemyTanks.size(); i++) {
                    // 从Vector中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    // 不和自己比较
                    if (enemyTank != this) {
                        // 如果敌人坦克是上/下
                        // 如果敌人坦克是上/下 x的范围[enemyTank.getX(), enemyTank.getX() + 40]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 当前坦克的左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // 当前坦克的左下角坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // 如果敌人坦克是右/左
                        // 如果敌人坦克是右/左 x的范围[enemyTank.getX(), enemyTank.getX() + 60]
                        //                 y的范围[enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 当前坦克的左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // 当前坦克的左下角坐标 [this.getX(), this.getY() + 40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {

            // 这里我们判断如果shots size() = 0, 创建一颗子弹，放入到shots集合，并启动
            // 敌方坦克设置为一次只能发射一枚子弹
            if (isLive && shots.size() == 0) {
                TankGame05.Shot s = null;
                // 判断坦克的方向，创建对应的子弹
                switch (getDirection()) {
                    case 0:
                        s = new TankGame05.Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new TankGame05.Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new TankGame05.Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new TankGame05.Shot(getX(), getY() + 20, 3);
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
                        if(!isTouchEnemyTank()){
                            moveUp();
                        }
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
                        if(!isTouchEnemyTank()){
                            moveRight();
                        }
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
                        if(!isTouchEnemyTank()){
                            moveDown();
                        }
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
                        if(!isTouchEnemyTank()){
                            moveLeft();
                        }
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
