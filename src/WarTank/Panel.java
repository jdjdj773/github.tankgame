package WarTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings({"all"})
public class Panel extends JPanel implements KeyListener, Runnable {
    //存放敵方坦克集合
    Vector<EnemyTank> enemyTanks = new Vector<>();
    ///英雄的集合
    Vector<Hero> heroes = new Vector<>();
    //存放炸彈集合
    Vector<Bomb> bombs = new Vector<>();
    //存放石牆壁集合
    static Vector<RockWall> rockWalls = new Vector<>();
    //存放紅牆壁集合
    static Vector<RedWall> redWalls = new Vector<>();
    static Vector<Node> nodes = new Vector<>();
    //存放子彈集合
    static Vector<Shot> shots = new Vector<>();

    public static void setShots(Vector<Shot> shots) {
        Panel.shots = shots;
    }

    //子彈
    Shot shot = null;
    //敵人坦克
    EnemyTank enemyTank = null;
    //我方坦克
    Hero hero = null;
    //炸彈
    Bomb bomb = null;
    //石牆
    RockWall rockWall = null;
    //磚牆
    RedWall redWall = null;
    int temp = 0;
    int enemySize = 3;
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    Image image5 = null;
    Image image6 = null;
    Image image7 = null;
    Image image8 = null;

    public static void setNodes(Vector<Node> nodes) {
        Panel.nodes = nodes;
    }

    public Panel(String key) {
        switch (key) {
            case "1"://新遊戲
                //將敵方坦克賦予給Recorder
                Recorder.setEnemyTanks(enemyTanks);
                //初始化敵人坦克
                for (int i = 0; i < enemySize; i++) {
                    enemyTank = new EnemyTank(temp += 100, 100, 2);
                    enemyTanks.add(enemyTank);
                    new Thread(enemyTank).start();
                }
                //初始化我方坦克
                hero = new Hero(500, 500, 0);
                heroes.add(hero);
                //將我方坦克賦予給Recorder
                Recorder.setHero(hero);
                for (int x = 0; x <= 420; x += 60) {
                    for (int y = 400; y <= 520; y += 60) {
                        rockWall = new RockWall(x, y);
                        rockWalls.add(rockWall);
                    }
                }
                for (int x = 700; x <= 760; x += 60) {
                    for (int y = 0; y <= 360; y += 60) {
                        redWall = new RedWall(x, y);
                        redWalls.add(redWall);
                    }
                }
                Tank.setRockWalls(rockWalls);
                Tank.setRedWalls(redWalls);
                Shot.setRockWalls(rockWalls);
                Shot.setRedWalls(redWalls);
                image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb01.png"));
                image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb02.png"));
                image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb03.png"));
                image4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb04.png"));
                image5 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb05.png"));
                image6 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/rockwall.png"));
                image7 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/redwall.png"));
                image8 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/gunshot.png"));
                break;
            case "2"://繼續遊戲
                Recorder.keepRecord();
                //將敵方坦克賦予給Recorder
                Recorder.setEnemyTanks(enemyTanks);
                //初始化敵人坦克
                for (int i = 1; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    enemyTank = new EnemyTank(node.getX(), node.getY(), node.getDirect());
                    enemyTanks.add(enemyTank);
                    new Thread(enemyTank).start();
                }
                //初始化我方坦克
                hero = new Hero(nodes.get(0).getX(), nodes.get(0).getY(), nodes.get(0).getDirect());
                //將我方坦克賦予給Recorder
                Recorder.setHero(hero);
                image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb01.png"));
                image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb02.png"));
                image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb03.png"));
                image4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb04.png"));
                image5 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb05.png"));
                image6 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/rockwall.png"));
                image7 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/redwall.png"));
                image8 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/gunshot.png"));
                break;
        }
    }


    //擊中我方坦克的方法
    public void killHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (shot.getX() > hero.getX() && shot.getX() < hero.getX() + 60
                        && shot.getY() > hero.getY() && shot.getY() < hero.getY() + 60) {
                    hero.isAlive = false;
                    heroes.remove(hero);
                    shot.isAlive = false;
                    bomb = new Bomb(hero.getX(), hero.getY());
                    bombs.add(bomb);
                }
            }
        }
    }

    //擊中敵方坦克的方法
    public void killEnemy() {
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            for (int j = 0; j < enemyTanks.size(); j++) {
                EnemyTank enemyTank = enemyTanks.get(j);
                if (shot.getX() > enemyTank.getX() && shot.getX() < enemyTank.getX() + 60
                        && shot.getY() > enemyTank.getY() && shot.getY() < enemyTank.getY() + 60) {
                    enemyTanks.remove(enemyTank);
                    enemyTank.isAlive = false;
                    shot.isAlive = false;
                    bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
            }
        }
    }
    //補充子彈
    public void restoreShot(){

    }
    //畫我方子彈
    public void drawMyShot(Graphics g){
        if(hero.getShots().size()==0){
            for(int i=1245;i<=1345;i+=25){
                g.drawImage(image8, i, 95, 30, 30, this);
            }
        }else if(hero.getShots().size()==1){
            for(int i=1245;i<=1320;i+=25){
                g.drawImage(image8, i, 95, 30, 30, this);
            }
        }else if(hero.getShots().size()==2){
            for(int i=1245;i<=1295;i+=25){
                g.drawImage(image8, i, 95, 30, 30, this);
            }
        }else if(hero.getShots().size()==3){
            for(int i=1245;i<=1270;i+=25){
                g.drawImage(image8, i, 95, 30, 30, this);
            }
        }else if(hero.getShots().size()==4) {
            for (int i = 1245; i <= 1245; i += 25) {
                g.drawImage(image8, i, 95, 30, 30, this);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(0, 0, 1200, 900);
        //畫出我方坦克
        if (hero.isAlive) {
            drawHero(hero.getX(), hero.getY(), g, hero.getDirect());
        }
        //畫出敵方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isAlive) {
                drawEnemy(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect());
            }
        }
        //畫出敵人子彈
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (shot != null && shot.isAlive) {
                    g.fillOval(shot.getX(), shot.getY(), 5, 5);
                }
            }
        }
        //畫出我方的子彈
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isAlive) {
                g.setColor(Color.YELLOW);
                g.fillOval(shot.getX(), shot.getY(), 5, 5);
            }
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1240,5,140,85);
        drawHero(1280,20,g,0);
        g.setColor(Color.black);
        g.drawRect(1240,5,140,125);
        g.drawRect(1240,90,140,40);
        drawMyShot(g);
        //讓爆炸效果緩衝
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //畫出炸彈效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life() > 20) {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.life() > 15) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.life() > 10) {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.life() > 5) {
                g.drawImage(image4, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(image5, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            bomb.lifeDown();
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
            }
        }
        //畫出石牆壁
        for (int i = 0; i < rockWalls.size(); i++) {
            RockWall rockWall = rockWalls.get(i);
            if (rockWall.isAlive) {
                g.drawImage(image6, rockWall.getX(), rockWall.getY(), 60, 60, this);
                g.setColor(Color.black);
                g.drawRect(rockWall.getX(), rockWall.getY(), 60, 60);
            }
        }
        //畫出磚牆壁
        for (int i = 0; i < redWalls.size(); i++) {
            RedWall redWall = redWalls.get(i);
            g.drawImage(image7, redWall.getX(), redWall.getY(), 60, 60, this);
            g.setColor(Color.black);
            g.drawRect(redWall.getX(), redWall.getY(), 60, 60);
        }

    }

    public void drawEnemy(int x, int y, Graphics g, int direct) {
        g.setColor(Color.cyan);
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 15, 60, false);//坦克左輪
                g.fill3DRect(x + 15, y + 10, 30, 40, false);//坦克車身
                g.fill3DRect(x + 45, y, 15, 60, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x + 29, y, x + 29, y + 30);//砲管
                g.drawLine(x + 30, y, x + 30, y + 30);//
                g.drawLine(x + 31, y, x + 31, y + 30);//
                break;
            case 1:
                g.fill3DRect(x, y, 60, 15, false);//坦克左輪
                g.fill3DRect(x + 10, y + 15, 40, 30, false);//坦克車身
                g.fill3DRect(x, y + 45, 60, 15, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x + 30, y + 29, x + 60, y + 29);//砲管
                g.drawLine(x + 30, y + 30, x + 60, y + 30);//
                g.drawLine(x + 30, y + 31, x + 60, y + 31);//
                break;
            case 2:
                g.fill3DRect(x, y, 15, 60, false);//坦克左輪
                g.fill3DRect(x + 15, y + 10, 30, 40, false);//坦克車身
                g.fill3DRect(x + 45, y, 15, 60, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x + 29, y + 60, x + 29, y + 30);//砲管
                g.drawLine(x + 30, y + 60, x + 30, y + 30);//
                g.drawLine(x + 31, y + 60, x + 31, y + 30);//
                break;
            case 3:
                g.fill3DRect(x, y, 60, 15, false);//坦克左輪
                g.fill3DRect(x + 10, y + 15, 40, 30, false);//坦克車身
                g.fill3DRect(x, y + 45, 60, 15, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x, y + 29, x + 30, y + 29);//砲管
                g.drawLine(x, y + 30, x + 30, y + 30);//
                g.drawLine(x, y + 31, x + 30, y + 31);//
                break;
        }
    }

    public void drawHero(int x, int y, Graphics g, int direct) {//畫我方坦克
        g.setColor(Color.YELLOW);
        switch (direct) {//方向 0上 1右 2下 3左
            case 0://上
                g.fill3DRect(x, y, 15, 60, false);//坦克左輪
                g.fill3DRect(x + 15, y + 10, 30, 40, false);//坦克車身
                g.fill3DRect(x + 45, y, 15, 60, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x + 28, y - 10, x + 28, y + 30);//砲管
                g.drawLine(x + 29, y - 10, x + 29, y + 30);//
                g.drawLine(x + 30, y - 10, x + 30, y + 30);//
                g.drawLine(x + 31, y - 10, x + 31, y + 30);//
                g.drawLine(x + 32, y - 10, x + 32, y + 30);//
                break;
            case 1://右
                g.fill3DRect(x, y, 60, 15, false);//坦克左輪
                g.fill3DRect(x + 10, y + 15, 40, 30, false);//坦克車身
                g.fill3DRect(x, y + 45, 60, 15, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x + 30, y + 28, x + 70, y + 28);//砲管
                g.drawLine(x + 30, y + 29, x + 70, y + 29);//
                g.drawLine(x + 30, y + 30, x + 70, y + 30);//
                g.drawLine(x + 30, y + 31, x + 70, y + 31);//
                g.drawLine(x + 30, y + 32, x + 70, y + 32);//
                break;
            case 2://下
                g.fill3DRect(x, y, 15, 60, false);//坦克左輪
                g.fill3DRect(x + 15, y + 10, 30, 40, false);//坦克車身
                g.fill3DRect(x + 45, y, 15, 60, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x + 28, y + 30, x + 28, y + 70);//砲管
                g.drawLine(x + 29, y + 30, x + 29, y + 70);//
                g.drawLine(x + 30, y + 30, x + 30, y + 70);//
                g.drawLine(x + 31, y + 30, x + 31, y + 70);//
                g.drawLine(x + 32, y + 30, x + 32, y + 70);//
                break;
            case 3://左
                g.fill3DRect(x, y, 60, 15, false);//坦克左輪
                g.fill3DRect(x + 10, y + 15, 40, 30, false);//坦克車身
                g.fill3DRect(x, y + 45, 60, 16, false);//坦克右輪
                g.fillOval(x + 17, y + 17, 25, 25);//坦克車蓋
                g.drawLine(x + 30, y + 28, x - 10, y + 28);//砲管
                g.drawLine(x + 30, y + 29, x - 10, y + 29);//
                g.drawLine(x + 30, y + 30, x - 10, y + 30);//
                g.drawLine(x + 30, y + 31, x - 10, y + 31);//
                g.drawLine(x + 30, y + 32, x - 10, y + 32);//
                break;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (hero.isAlive) {
            if (e.getKeyCode() == KeyEvent.VK_W) {//向上
                hero.setDirect(0);
                hero.Up();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {//右
                hero.setDirect(1);
                hero.Right();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {//下
                hero.setDirect(2);
                hero.Down();
            } else if (e.getKeyCode() == KeyEvent.VK_A) {//左
                hero.setDirect(3);
                hero.Left();
            }
            if (e.getKeyCode() == KeyEvent.VK_J) {
                hero.shootEnemy();
            }
            int count = 0;
            if (e.getKeyCode() == KeyEvent.VK_R) {
                for(int i=0;i<hero.getShots().size();i++) {
                    Shot shot = hero.getShots().get(i);
                    if(!shot.isAlive){
                        hero.getShots().clear();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_1) {
                hero.setSpeed(hero.getSpeed() + 3);
            }
            if (e.getKeyCode() == KeyEvent.VK_2) {
                hero.setSpeed(hero.getSpeed() - 3);
            }
            this.repaint();
        }

    }

    @Override
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            killEnemy();
            killHero();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
