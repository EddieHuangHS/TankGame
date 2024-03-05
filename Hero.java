package TankGame04;

import java.util.Vector;

public class Hero extends Tank{
    // 定义一个 shot 对象，表示一个射击（线程）
    Shot shot = null;
    // 实现多颗子弹发射功能
    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }

    // 射击
    public void shotEnemyTank(){
        // 限制坦克一次最多发射5颗子弹
        if(shots.size() == 5){
            return;
        }
//        // 每隔0.5秒可以再发射另一枚子弹
//        if(shots.size() >= 1){
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        if(isLive){ // 只有当hero存活时才能发射子弹
            // 创建 Shot 对象，根据当前 Hero 对象的位置和方向来创建 Shot
            switch (getDirection()){
                case 0: //向上
                    shot = new Shot(getX() + 20, getY(), 0);
                    break;
                case 1: // 向右
                    shot = new Shot(getX() + 60, getY() + 20, 1);
                    break;
                case 2: // 向下
                    shot = new Shot(getX() + 20, getY() + 60, 2);
                    break;
                case 3: // 向左
                    shot = new Shot(getX(), getY() + 20, 3);
                    break;
            }
            // 把新创建的shot加入到集合shots里
            shots.add(shot);
            // 启动 Shot 线程
            new Thread(shot).start();
        }
    }
}
