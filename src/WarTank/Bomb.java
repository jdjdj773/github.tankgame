package WarTank;

public class Bomb {
    private int x;
    private int y;
    private int life = 25;
    private boolean isAlive = true;
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int life() {
        return life;
    }

    public void life(int life) {
        this.life = life;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void lifeDown(){
        if(life>0){
            life--;
        }else{
            isAlive = false;
        }
    }
}
