package WarTank;

import java.util.Vector;

@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Shot> shots = new Vector<>();
    Shot shot = null;
    public EnemyTank(int x, int y, int direct) {
        super(x, y, direct);
    }

    @Override
    public void run() {
        while (true) {
            if(shots.size()<1) {
                switch (getDirect()) {
                    case 0:
                        shot = new Shot(getX() + 29, getY(), getDirect());
                        break;
                    case 1:
                        shot = new Shot(getX() + 60, getY() + 29, getDirect());
                        break;
                    case 2:
                        shot = new Shot(getX() + 29, getY() + 60, getDirect());
                        break;
                    case 3:
                        shot = new Shot(getX(), getY() + 29, getDirect());
                        break;
                }
                shots.add(shot);
                new Thread(shot).start();
            }
            if(!shot.isAlive){
                shots.remove(shot);
            }
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 25; i++) {
                        Up();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 25; i++) {
                        Right();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 2:
                    for (int i = 0; i < 25; i++) {
                        Down();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 25; i++) {
                        Left();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            setDirect((int) (Math.random() * 4));

        }
    }
}
