package Main;

import MenuBar.*;
import ToolBar.DefaultTool;
import ToolBar.DefaultToolBar;
import ToolBar.IToolBar;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public class MainWindow extends JFrame {
    private IMenuBar iMenuBar;
    private IMenu iMenu;
    private IToolBar iToolBar;

    public MainWindow(){
        InitUI();
    }

    private void InitUI(){
        JFrame frame = new JFrame("MABA IDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024,960);
        frame.setVisible(true);

        this.iMenuBar = new DefaultMenuBar();
        frame.setJMenuBar((JMenuBar) this.iMenuBar);

        DefaultMenu fileMenu= new DefaultMenu("File");
        this.iMenuBar.AddMenu(fileMenu);

        DefaultMenu editMenu= new DefaultMenu("Edit");
        this.iMenuBar.AddMenu(editMenu);

        //Region Toobar
        this.iToolBar = new DefaultToolBar();
        DefaultTool newTool = new DefaultTool("../assets/new.png");
        this.iToolBar.AddToolItem(newTool);
    }

    public static void main(String args[]){
        MainWindow mainWindow = new MainWindow();
    }
}
