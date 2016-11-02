package Main;

import MenuBar.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public class MainWindow extends JFrame {
    private IMenuBar iMenuBar;
    private IMenu iMenu;
    public MainWindow(){
        InitUI();
    }

    private void InitUI(){
        JFrame frame = new JFrame("MABA IDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,1080);
        frame.setVisible(true);

        this.iMenuBar = new DefaultMenuBar();
        frame.setJMenuBar((JMenuBar) this.iMenuBar);

        DefaultMenu fileMenu= new DefaultMenu("File");
        this.iMenuBar.AddMenu(fileMenu);

        DefaultMenu editMenu= new DefaultMenu("Edit");
        this.iMenuBar.AddMenu(editMenu);

        DefaultMenu compileMenu= new DefaultMenu("Compile");
        this.iMenuBar.AddMenu(compileMenu);
    }

    public static void main(String args[]){
        MainWindow mainWindow = new MainWindow();
    }
}
