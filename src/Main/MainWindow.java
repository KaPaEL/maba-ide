package Main;

import javax.swing.*;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public class MainWindow extends JFrame {
    public MainWindow(){
        InitUI();
    }

    private void InitUI(){

    }

    public static void main(String args[]){
        MainWindow mainWindow = new MainWindow();
        JFrame frame = new JFrame("Maba IDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720,640);
        frame.setVisible(true);
    }
}
