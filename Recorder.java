package TankGame05;

import java.io.*;
import java.util.Vector;

/**
 * 该类用于记录相关信息，和文件交互
 */
public class Recorder {
    // 定义变量，记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;
    // 定义IO对象
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";
    // 定义Vector，指向MyPanel对象的敌人坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;
    // 定义一个 Node 的 Vector，用于保存敌人的信息 Node
    private static Vector<Node> nodes = new Vector<>();


    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    // 增加一个方法，用于读取recordFile，恢复相关信息
    // 该方法在选择继续上局游戏的时候被调用
    public static Vector<Node> getNodesAndEnemyTankRec(){
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            // 循环读取文件，生成 nodes集合
            String line = "";
            while((line = br.readLine()) != null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return nodes;
    }

    public static void initialRecord(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(recordFile));
            allEnemyTankNum = 0;
            bw.write(allEnemyTankNum);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 增加一个方法，当游戏退出时，将allEnemyTankNum保存到recordFile
    // 对keepRecord 进行升级，保存敌人坦克的坐标和方向
    public static void keepRecord(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            // 遍历敌人坦克的Vector，然后根据情况保存即可
            // OOP 定义一个属性，然后通过setXXX得到敌人坦克的Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                // 取出敌人坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                // 判断当前坦克是否存活
                if(enemyTank.isLive){
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                    // 写入到文件
                    bw.write(record + "\r\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    // 当我方坦克击毁一个敌人坦克，就应该allEnemyTankNum++
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
