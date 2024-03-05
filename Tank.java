package TankGame04;

public class Tank {
    private int x; // 坦克横坐标
    private int y; // 坦克纵坐标
    private int direction = 0; // 坦克方向 0上 1右 2下 3左
    private int speed = 1;
    boolean isLive = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp(){
        if(y > 0 && isLive){
            y -= speed;
        }
    }

    public void moveRight(){
        if(x + 60 < 1000 && isLive){
            x += speed;
        }
    }

    public void moveDown(){
        if(y + 60 < 750 && isLive){
            y += speed;
        }
    }

    public void moveLeft(){
        if(x > 0 && isLive){
            x -= speed;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
