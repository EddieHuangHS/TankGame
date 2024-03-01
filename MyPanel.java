package chapter16.TankGame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

// 为了监听 键盘事件，实现KeyListener
public class MyPanel extends JPanel implements KeyListener {
    // 定义我的坦克
    Hero hero = null;
    // 定义敌人的坦克，放入到Vector，因为Vector是线程安全的集合
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    public MyPanel(){
        hero = new Hero(100, 300); // 初始化自己坦克的坐标
        hero.setSpeed(5);
        // 初始化敌人的坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirection(2);
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);

        // 画出自己的坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);

        // 画出敌人的坦克，遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
        }
    }

    // 编写方法，画出坦克

    /**
     *
     * @param x 坦克的左上角 x 坐标
     * @param y 坦克的左上角 y 坐标
     * @param g 画笔
     * @param direction 坦克方向（上下左右）
     * @param type 坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type){

        // 根据不同类型，给画笔不同的颜色
        switch (type){
            case 0: // 敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 我们的坦克
                g.setColor(Color.yellow);
                break;
        }

        // 根据坦克的方向，绘制坦克
        // direction 表示方向（0表示向上 1表示向右 2表示向下 3表示向左）
        switch (direction){
            case 0: // 0 表示向上
                g.fill3DRect(x, y, 10, 60, false); // 画出左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false); // 画出右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 画出坦克中间的盖子
                g.fillOval(x + 10, y + 20, 20, 20); // 画出坦克中间的小圆
                g.drawLine(x + 20, y, x + 20, y + 30); // 画出坦克的炮筒
                break;
            case 1: // 0 表示向右
                g.fill3DRect(x, y, 60, 10, false); // 画出左边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false); // 画出右边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 画出坦克中间的盖子
                g.fillOval(x + 20, y + 10, 20, 20); // 画出坦克中间的小圆
                g.drawLine(x + 60, y + 20, x + 30, y + 20); // 画出坦克的炮筒
                break;
            case 2: // 0 表示向下
                g.fill3DRect(x, y, 10, 60, false); // 画出左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false); // 画出右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 画出坦克中间的盖子
                g.fillOval(x + 10, y + 20, 20, 20); // 画出坦克中间的小圆
                g.drawLine(x + 20, y + 60, x + 20, y + 30); // 画出坦克的炮筒
                break;
            case 3: // 0 表示向左
                g.fill3DRect(x, y, 60, 10, false); // 画出左边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false); // 画出右边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // 画出坦克中间的盖子
                g.fillOval(x + 20, y + 10, 20, 20); // 画出坦克中间的小圆
                g.drawLine(x, y + 20, x + 30, y + 20); // 画出坦克的炮筒
                break;
            default:
                System.out.println("暂时没有操作");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 处理wdsa 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            hero.setDirection(0);
            hero.moveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            hero.setDirection(1);
            hero.moveRight();
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            hero.setDirection(2);
            hero.moveDown();
        }else if(e.getKeyCode() == KeyEvent.VK_A){
            hero.setDirection(3);
            hero.moveLeft();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
