package TankGame05;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class JFrame_ extends JFrame {

    // 定义MyPanel
    TankGame05.MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        JFrame_ jFrame_ = new JFrame_();
    }

    public JFrame_(){
        System.out.println("请输入选择 1：新游戏 2：继续上局");
        String key = scanner.next();
        mp = new TankGame05.MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp); // 把面板加入进框架
        this.addKeyListener(mp); // 让JFrame监听KeyListener事件
        this.setSize(1300, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // 在JFrame中增加响应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
