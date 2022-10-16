package WarTank;

import java.util.Vector;

public class Shot implements Runnable {
    private int x;
    private int y;
    private int direct;
    private int speed = 8;
    boolean isAlive = true;
    static Vector<RockWall> rockWalls = new Vector<>();
    static Vector<RedWall> redWalls = new Vector<>();

    public static void setRockWalls(Vector<RockWall> rockWalls) {
        Shot.rockWalls = rockWalls;
    }

    public static void setRedWalls(Vector<RedWall> redWalls) {
        Shot.redWalls = redWalls;
    }

    public boolean shootWall(){
        for(int i=0;i<rockWalls.size();i++){
            RockWall rockWall = rockWalls.get(i);
            if(x>rockWall.getX() && x< rockWall.getX()+60
                && y>rockWall.getY() && y<rockWall.getY()+60){
                rockWall.isAlive = false;
                rockWalls.remove(rockWall);
                return true;
            }
        }
        for(int i=0;i<redWalls.size();i++){
            RedWall redWall = redWalls.get(i);
            if(x>redWall.getX() && x< redWall.getX()+60
                    && y>redWall.getY() && y<redWall.getY()+60){
                return true;
            }
        }
        return false;
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

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        Shoot();
    }

    public void Shoot() {
        while (isAlive) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0://向上
                    y -= speed;
                    break;
                case 1://向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;
            }
            if (!(x > 0 && x < 1200 && y > 0 && y < 900) || shootWall()) {
                isAlive = false;
            }


        }
    }
}

