package TankGame04;

import javax.swing.*;

public class JFrame_ extends JFrame {

    // 定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        JFrame_ jFrame_ = new JFrame_();
    }

    public JFrame_(){
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp); // 把面板加入进框架
        this.addKeyListener(mp); // 让JFrame监听KeyListener事件
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
