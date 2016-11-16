package Main;

import Commands.*;
import Commands.ShellCommand.Compile;
import Editor.DefaultTextArea;
import FileExplorer.*;
import MenuBar.*;
import ToolBar.DefaultTool;
import ToolBar.DefaultToolBar;
import ToolBar.IToolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public class MainWindow extends JFrame {
    private IMenuBar iMenuBar;
    private IMenu iMenu;
    private IToolBar iToolBar;
    private IMenuItem iMenuItem;

    public MainWindow() throws IOException {
        InitUI();
    }

    private void InitUI() throws IOException {
        JFrame frame = new JFrame("MABA IDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        frame.setIconImage(ImageIO.read(new File("assets/logo.png")));
        UIManager.put("Button.setBorderPainted",BorderFactory.createEmptyBorder());
        DefaultTextArea defaultTextArea = new DefaultTextArea();
        JScrollPane eastPanel = new JScrollPane(defaultTextArea);
        DefaultFileExplorer defaultFileExplorer = new DefaultFileExplorer(".");
        JScrollPane westPanel = new JScrollPane(new FileExplorer("."));
        pack();
        setLocationRelativeTo(null);

        //setContentPane(frame);


        this.iMenuBar = new DefaultMenuBar();
        frame.setJMenuBar((JMenuBar) this.iMenuBar);

        //MENU FILE
        DefaultMenu fileMenu= new DefaultMenu("File");
        this.iMenuBar.AddMenu(fileMenu);

        DefaultMenuItem newMenuItem= new DefaultMenuItem("New File");
        newMenuItem.SetIcon(new ImageIcon("assets/new.png"));
        fileMenu.AddMenuItem(newMenuItem);

        DefaultMenuItem openFileMenuItem= new DefaultMenuItem("Open File");
        OpenFile openFile = new OpenFile(defaultTextArea);
        openFileMenuItem.SetCommand(openFile);
        openFileMenuItem.SetIcon(new ImageIcon("assets/open.png"));
        fileMenu.AddMenuItem(openFileMenuItem);

        DefaultMenuItem openFolderMenuItem= new DefaultMenuItem("Open Folder");
        OpenFolder openFolder = new OpenFolder(defaultFileExplorer);
        openFolderMenuItem.SetCommand(openFolder);
        fileMenu.AddMenuItem(openFolderMenuItem);

        fileMenu.AddSeparator();

        DefaultMenuItem saveMenuItem= new DefaultMenuItem("Save");
        saveMenuItem.SetIcon(new ImageIcon("assets/save.png"));
        fileMenu.AddMenuItem(saveMenuItem);

        DefaultMenuItem saveAsMenuItem= new DefaultMenuItem("Save As");
        fileMenu.AddMenuItem(saveAsMenuItem);

        fileMenu.AddSeparator();

        DefaultMenuItem closeFileMenuItem= new DefaultMenuItem("Close File");
        fileMenu.AddMenuItem(closeFileMenuItem);

        DefaultMenuItem closeAllMenuFile= new DefaultMenuItem("Close All Files");
        fileMenu.AddMenuItem(closeAllMenuFile);

        fileMenu.AddSeparator();

        DefaultMenuItem exitMenuFile= new DefaultMenuItem("Exit");
        Exit exit = new Exit();
        exitMenuFile.SetCommand(exit);
        fileMenu.AddMenuItem(exitMenuFile);

        //MENU EDIT
        DefaultMenu editMenu= new DefaultMenu("Edit");
        this.iMenuBar.AddMenu(editMenu);

        DefaultMenuItem copyMenuItem= new DefaultMenuItem("Copy");
        copyMenuItem.SetIcon(new ImageIcon("assets/copy.png"));
        editMenu.AddMenuItem(copyMenuItem);

        DefaultMenu compileMenu= new DefaultMenu("Compile");
        this.iMenuBar.AddMenu(compileMenu);

        DefaultMenuItem runMenuItem= new DefaultMenuItem("Run");
        compileMenu.AddMenuItem(runMenuItem);

        Compile compile = new Compile();
        DefaultMenuItem compileMenuItem= new DefaultMenuItem("Compile");
        compileMenuItem.SetCommand(compile);
        compileMenu.AddMenuItem(compileMenuItem);

        //Region Toobar

        this.iToolBar = new DefaultToolBar();
        DefaultTool newTool = new DefaultTool("assets/new.png","New file");
        this.iToolBar.AddToolItem(newTool);
        DefaultTool openTool = new DefaultTool("assets/open.png","Open file");
        this.iToolBar.AddToolItem(openTool);
        DefaultTool saveTool = new DefaultTool("assets/save.png","save file");
        this.iToolBar.AddToolItem(saveTool);
        DefaultTool copyTool = new DefaultTool("assets/copy.png","Copy text");
        this.iToolBar.AddToolItem(copyTool);
        DefaultTool undoTool = new DefaultTool("assets/undo.png","Undo");
        this.iToolBar.AddToolItem(undoTool);
        DefaultTool redoTool = new DefaultTool("assets/redo.png","Redo");
        this.iToolBar.AddToolItem(redoTool);

        Container container = frame.getContentPane();
        container.add((Component) this.iToolBar, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, westPanel,eastPanel);
        splitPane.setDividerLocation(148);
        contentPanel.add(splitPane, BorderLayout.CENTER);
        setContentPane(contentPanel);
        frame.add(contentPanel);

        frame.setVisible(true);

    }


    public static void main(String args[]) throws IOException {
        MainWindow mainWindow = new MainWindow();
    }
}
