package TankGame05;

public class Shot implements Runnable{
    int x; // 子弹 x 坐标
    int y; // 子弹 y 坐标
    int direction = 0; // 子弹方向，默认向上
    int speed = 2; // 子弹速度，默认2
    boolean isLive = true; // 子弹是否还存活

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {

        while (true) {
            // 休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 根据方向来改变 x,y 坐标
            switch (direction){
                case 0: // 向上
                    y -= speed;
                    break;
                case 1: // 向右
                    x += speed;
                    break;
                case 2: // 向下
                    y += speed;
                    break;
                case 3: // 向左
                    x -= speed;
                    break;
            }
//            // 测试语句，用来输出当前 x 和 y 的坐标
//            System.out.println("子弹 x=" + x + " y=" + y);

            // 当子弹移动到面板的边界，就应该销毁（把启动的子弹的线程销毁）
            // 当子弹碰到敌人坦克时，也应该结束线程
            if(!(x >= 0 && x <= 1000 && y >= 0 && y<= 750 && isLive)){
//                System.out.println("射击结束");
                isLive = false;
                break;
            }

        }
    }
}
