package WarTank;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static String keepPath = "/Users/berray/Desktop/Tankgame/src/recordGame.txt";
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static Vector<Node> nodes = new Vector<>();
    private static Hero hero = null;
    private static Vector<EnemyTank> enemyTanks = new Vector<>();

    public static void setHero(Hero hero) {
        Recorder.hero = hero;
    }
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    public static void keepRecord(){
        try {
            br = new BufferedReader(new FileReader(keepPath));
            String line = "";
            while((line = br.readLine()) != null){
                String[] xyd = line.split(" ");
                System.out.println(xyd);
                Node node = new Node(Integer.parseInt(xyd[0]),Integer.parseInt(xyd[1]),Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
            Panel.setNodes(nodes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void recordPath(){
        try {
            bw = new BufferedWriter(new FileWriter(keepPath));
            String heroPath = hero.getX() + " " + hero.getY() + " " + hero.getDirect() + "\r\n";
            bw.write(heroPath);
            for(int i=0;i<enemyTanks.size();i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                String enemyPath = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect() + "\r\n";
                bw.write(enemyPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
