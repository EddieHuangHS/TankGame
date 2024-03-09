package TankGame05;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

@SuppressWarnings({"All"})
// 为了监听 键盘事件，实现KeyListener
public class MyPanel extends JPanel implements KeyListener, Runnable {
    // 定义我的坦克
    TankGame05.Hero hero = null;
    // 定义敌人的坦克，放入到Vector，因为Vector是线程安全的集合
    Vector<EnemyTank> enemyTanks = new Vector<>();
    // 定义一个存放Node，用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();
    // 定义一个Vector，用于存放炸弹
    // 当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<TankGame05.Bomb> bombs = new Vector<>();
    // 设置敌人坦克数量
    int enemyTankSize = 3;

    // 定义3张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        // 先判断记录的文件是否存在
        // 如果存在就正常执行，如果不存在，提示，只能开启新游戏，key = "1"
        File file = new File(Recorder.getRecordFile());
        if(file.exists()){
            nodes = Recorder.getNodesAndEnemyTankRec();
        }else{
            System.out.println("文件不存在，开启新游戏");
            key = "1";
        }

        // 将MyPanel对象的enemyTanks设置给Recorder的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new TankGame05.Hero(100, 200); // 初始化自己坦克的坐标
        hero.setSpeed(5);

        switch (key){
            case "1": // 开启新游戏
                // 初始化存储文件
                Recorder.initialRecord();
                // 初始化敌人的坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    // 创建敌人坦克
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    // 将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    // 设置敌人坦克初始方向
                    enemyTank.setDirection(2);
                    // 启动敌人坦克线程
                    new Thread(enemyTank).start();
                    // 给 enemyTank 加入一颗子弹
                    TankGame05.Shot shot = new TankGame05.Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    // 加入 enemyTank 的 Vector 成员
                    enemyTank.shots.add(shot);
                    // 启动 shot 对象
                    new Thread(shot).start();
                    // 把敌人坦克加入到敌人坦克的集合里
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2": // 继续上局游戏
                // 初始化敌人的坦克
                for (int i = 0; i < nodes.size(); i++) {
                    // 从存储列表里得到一个敌人坦克
                    Node node = nodes.get(i);
                    // 创建敌人坦克
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    // 将enemyTanks设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    // 设置敌人坦克初始方向
                    enemyTank.setDirection(node.getDirection());
                    // 启动敌人坦克线程
                    new Thread(enemyTank).start();
                    // 给 enemyTank 加入一颗子弹
                    TankGame05.Shot shot = new TankGame05.Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    // 加入 enemyTank 的 Vector 成员
                    enemyTank.shots.add(shot);
                    // 启动 shot 对象
                    new Thread(shot).start();
                    // 把敌人坦克加入到敌人坦克的集合里
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("你输入的值有误...");
        }



        // 初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bigBomb.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/mediumBomb.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/smallBomb.png"));

        // 这里播放指定的音乐
        new AePlayWave("src\\111.wav").start();
    }

    // 编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g){
        // 画出玩家的总成绩
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累计击毁敌方坦克", 1020, 30);
        this.drawTank(1020, 60, g, 0, 0); // 画出一个敌方坦克
        // 画敌方坦克的时候画笔的颜色改变了
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);
        this.showInfo(g);
        // 画出自己的坦克
        if(hero != null && hero.isLive){
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }

        // 画出 hero 射出的子弹
        // 将hero的子弹集合shots，遍历取出绘制
        for (int i = 0; i < hero.shots.size(); i++) {
            TankGame05.Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive == true) {
                g.draw3DRect(shot.x, shot.y, 2, 2, false);
            }else{ // 如果该shot对象已经无效，就从shots集合中拿掉
                hero.shots.remove(shot);
            }
        }

        // 如果bombs 集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            // 取出炸弹
            TankGame05.Bomb bomb = bombs.get(i);

            // -----------------------
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 根据当前这个bomb对象的life值画出对应的图片
            if(bomb.life > 6){
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            }else if(bomb.life > 3){
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            }else{
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            // 让这个炸弹的生命值减少
            bomb.lifeDown();
            // 如果bomb life 为0，就从bombs的集合中删除
            if(bomb.life == 0){
                bombs.remove(bomb);
            }
        }

        // 画出敌人的坦克，遍历Vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            // 判断敌人坦克是否仍存活
            if (enemyTank.isLive) {
                // 画出坦克
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
                // 画出 enemyTank 所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    // 取出子弹
                    TankGame05.Shot shot = enemyTank.shots.get(j);
                    // 绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
    }

    // 编写方法，画出坦克

    /**
     * @param x         坦克的左上角 x 坐标
     * @param y         坦克的左上角 y 坐标
     * @param g         画笔
     * @param direction 坦克方向（上下左右）
     * @param type      坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {

        // 根据不同类型，给画笔不同的颜色
        switch (type) {
            case 0: // 敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: // 我们的坦克
                g.setColor(Color.yellow);
                break;
        }

        // 根据坦克的方向，绘制坦克
        // direction 表示方向（0表示向上 1表示向右 2表示向下 3表示向左）
        switch (direction) {
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

    // 如果我们的坦克可以发射多颗子弹
    // 在判断我方子弹是否击中敌人坦克时，就需要把我们的子弹集合中的
    // 所有子弹，都取出和敌人的所有坦克进行判断
    public void hitEnemyTank(){
        // 遍历我们的子弹
        for (int j = 0; j < hero.shots.size(); j++) {
            TankGame05.Shot shot = hero.shots.get(j);
            // 判断是否击中了敌人坦克
            if (shot != null && shot.isLive && hero.isLive) { // 当自己的坦克的子弹仍然存活时
                // 遍历敌人的所有坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    // 编写方法，判断地方子弹是否击中我的坦克
    public void hitHero(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            // 取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            // 遍历enemyTank对象的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                // 取出子弹
                TankGame05.Shot shot = enemyTank.shots.get(j);
                // 判断shot是否击中了我的坦克
                if(hero.isLive && shot.isLive && enemyTank.isLive){
                    hitTank(shot, hero);
                }
            }
        }
    }

    public void hitTank(TankGame05.Shot s, Tank enemyTank) {
        // 判断 s 是否击中坦克
        switch (enemyTank.getDirection()) {
            case 0: // 向上的敌人坦克
            case 2: // 向下的敌人坦克
                if (s.x > enemyTank.getX()
                        && s.x < enemyTank.getX() + 40
                        && s.y > enemyTank.getY()
                        && s.y < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    enemyTanks.remove(enemyTank);
                    // 当我方击毁一个敌人坦克时，就对allEnemyTankNum++
                    // 这里的enemyTank也可能是hero
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    TankGame05.Bomb bomb = new TankGame05.Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1: // 向右的敌人坦克
            case 3: // 向左的敌人坦克
                if (s.x > enemyTank.getX()
                        && s.x < enemyTank.getX() + 60
                        && s.y > enemyTank.getY()
                        && s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    enemyTanks.remove(enemyTank);
                    // 当我方击毁一个敌人坦克时，就对allEnemyTankNum++
                    // 这里的enemyTank也可能是hero
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    TankGame05.Bomb bomb = new TankGame05.Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 处理wdsa 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirection(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirection(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirection(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirection(3);
            hero.moveLeft();
        }

        // 如果用户按下的是 J，就放射子弹
        // 子弹的 repaint 和坦克不同，因为坦克是全程被玩家操控的，但是子弹只有在发射时才被操控，因此需要为MyPanel创建线程并重绘画面
        if (e.getKeyCode() == KeyEvent.VK_J) {
            // 只能发射一颗的情况下，判断子弹是否销毁
//            if(hero.shot == null || !hero.shot.isLive){
//                hero.shotEnemyTank();
//            }
            // 发射多颗子弹
            hero.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { // 每隔100毫秒，重绘区域，子弹就移动
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 判断是否击中敌人坦克
            hitEnemyTank();
            // 判断敌人坦克是否击中我
            hitHero();
            this.repaint();
        }
    }
}
