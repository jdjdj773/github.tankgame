package WarTank;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class wartank_main extends JFrame {
    Panel panel = null;
    public static void main(String[] args) {
        new wartank_main();
    }
    public wartank_main(){
        Scanner myscanner = new Scanner(System.in);
        System.out.print("1.新遊戲 2.繼續遊戲 (請輸入):");
        String key = myscanner.next();
        panel = new Panel(key);
        new Thread(panel).start();
        this.add(panel);
        this.setSize(1400,1100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(panel);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.recordPath();
                System.exit(0);
            }
        });
    }
}
