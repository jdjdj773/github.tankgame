package WarTank;

import java.util.Vector;

public class Hero extends Tank {
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();

    public Vector<Shot> getShots() {
        return shots;
    }

    public Hero(int x, int y, int direct) {
        super(x, y, direct);
    }

    public void shootEnemy() {
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 28, getY() - 10, getDirect());
                break;
            case 1:
                shot = new Shot(getX() + 70, getY() + 28, getDirect());
                break;
            case 2:
                shot = new Shot(getX() + 28, getY() + 70, getDirect());
                break;
            case 3:
                shot = new Shot(getX() - 10, getY() + 28, getDirect());
                break;
        }
        int count = 0;
        if(shots.size()<5) {
            shots.add(shot);
        }else{
            for(int i=0;i<shots.size();i++) {
                Shot shot = shots.get(i);
                if(!shot.isAlive){
                    count++;
                }
            }
            if(count==5){
                shots.clear();
            }
        }

        new Thread(shot).start();


    }
}
