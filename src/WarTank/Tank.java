package WarTank;

import java.util.Vector;

@SuppressWarnings({"all"})
public class Tank {
    private int x;
    private int y;
    private int speed = 3;
    private int direct;
    boolean isAlive = true;
    static Vector<RockWall> rockWalls = new Vector<>();
    static Vector<RedWall> redWalls = new Vector<>();

    public static void setRockWalls(Vector<RockWall> rockWalls) {
        Tank.rockWalls = rockWalls;
    }

    public static void setRedWalls(Vector<RedWall> redWalls) {
        Tank.redWalls = redWalls;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public boolean touchRedWall(){
        switch ((direct)) {
            case 0:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y > redWall.getX() && y < redWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y > redWall.getX() && y < redWall.getY() + 60) {
                        return true;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y > redWall.getX() && y < redWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y + 60 > redWall.getX() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y + 60 > redWall.getX() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y + 60 > redWall.getX() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y > redWall.getX() && y < redWall.getY() + 60) {
                        return true;
                    }
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y + 60 > redWall.getX() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public boolean touchWall() {
        switch ((direct)) {
            case 0:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y > redWall.getY() && y < redWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y > redWall.getY() && y < redWall.getY() + 60) {
                        return true;
                    }
                }
                for (int i = 0; i < rockWalls.size(); i++) {
                    RockWall rockWall = rockWalls.get(i);
                    if (x > rockWall.getX() && x < rockWall.getX() + 60
                            && y > rockWall.getY() && y < rockWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > rockWall.getX() && x + 60 < rockWall.getX() + 60
                            && y > rockWall.getY() && y < rockWall.getY() + 60) {
                        return true;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y > redWall.getY() && y < redWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y + 60 > redWall.getY() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                }
                for (int i = 0; i < rockWalls.size(); i++) {
                    RockWall rockWall = rockWalls.get(i);
                    if (x + 60 > rockWall.getX() && x + 60 < rockWall.getX() + 60
                            && y > rockWall.getY() && y < rockWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > rockWall.getX() && x + 60 < rockWall.getX() + 60
                            && y + 60 > rockWall.getY() && y + 60 < rockWall.getY() + 60) {
                        return true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y + 60 > redWall.getY() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > redWall.getX() && x + 60 < redWall.getX() + 60
                            && y + 60 > redWall.getY() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                }
                for (int i = 0; i < rockWalls.size(); i++) {
                    RockWall rockWall = rockWalls.get(i);
                    if (x > rockWall.getX() && x < rockWall.getX() + 60
                            && y + 60 > rockWall.getY() && y + 60 < rockWall.getY() + 60) {
                        return true;
                    }
                    if (x + 60 > rockWall.getX() && x + 60 < rockWall.getX() + 60
                            && y + 60 > rockWall.getY() && y + 60 < rockWall.getY() + 60) {
                        return true;
                    }
                }
                break;
            case 3:
                for (int i = 0; i < redWalls.size(); i++) {
                    RedWall redWall = redWalls.get(i);
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y > redWall.getY() && y < redWall.getY() + 60) {
                        return true;
                    }
                    if (x > redWall.getX() && x < redWall.getX() + 60
                            && y + 60 > redWall.getY() && y + 60 < redWall.getY() + 60) {
                        return true;
                    }
                }
                for (int i = 0; i < rockWalls.size(); i++) {
                    RockWall rockWall = rockWalls.get(i);
                    if (x > rockWall.getX() && x < rockWall.getX() + 60
                            && y > rockWall.getY() && y < rockWall.getY() + 60) {
                        return true;
                    }
                    if (x > rockWall.getX() && x < rockWall.getX() + 60
                            && y + 60 > rockWall.getY() && y + 60 < rockWall.getY() + 60) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public void Up() {
        if (y > 0 && !touchWall()) {
            y -= speed;
        }
    }

    public void Right() {
        if (x < 1200 && !touchWall()) {
            x += speed;
        }
    }

    public void Down() {
        if (y < 700 && !touchWall()) {
            y += speed;
        }
    }

    public void Left() {
        if (x > 0 && !touchWall()){
            x -= speed;
        }
    }

    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
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
}
