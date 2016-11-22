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
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        InitUI();


    }

    private void InitUI() throws IOException {
        JFrame frame = new JFrame("MABA IDE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        frame.setIconImage(ImageIO.read(new File("assets/logo.png")));
        //UIManager.put("Button.setBorderPainted",BorderFactory.createEmptyBorder());

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
        NewFile newFile = new NewFile(defaultTextArea,defaultFileExplorer,this);
        newMenuItem.SetCommand(newFile);
        newMenuItem.SetIcon(new ImageIcon("assets/new.png"));
        fileMenu.AddMenuItem(newMenuItem);

        DefaultMenuItem openFileMenuItem= new DefaultMenuItem("Open File");
        OpenFile openFile = new OpenFile(defaultTextArea,defaultFileExplorer);
        openFileMenuItem.SetCommand(openFile);
        openFileMenuItem.SetIcon(new ImageIcon("assets/open-file.png"));
        fileMenu.AddMenuItem(openFileMenuItem);

        DefaultMenuItem openFolderMenuItem= new DefaultMenuItem("Open Folder");
        OpenFolder openFolder = new OpenFolder(defaultFileExplorer);
        openFolderMenuItem.SetCommand(openFolder);
        openFolderMenuItem.SetIcon(new ImageIcon("assets/open.png"));
        fileMenu.AddMenuItem(openFolderMenuItem);

        fileMenu.AddSeparator();

        DefaultMenuItem saveMenuItem= new DefaultMenuItem("Save");
        Save save = new Save(defaultTextArea,defaultFileExplorer);
        saveMenuItem.SetCommand(save);
        saveMenuItem.SetIcon(new ImageIcon("assets/save.png"));
        fileMenu.AddMenuItem(saveMenuItem);

        DefaultMenuItem saveAsMenuItem= new DefaultMenuItem("Save As");
        SaveAs saveAs = new SaveAs(defaultTextArea,defaultFileExplorer);
        saveAsMenuItem.SetCommand(saveAs);
        saveAsMenuItem.SetIcon(new ImageIcon("assets/save-as.png"));
        fileMenu.AddMenuItem(saveAsMenuItem);

        fileMenu.AddSeparator();

        DefaultMenuItem closeFileMenuItem= new DefaultMenuItem("Close File");
        CloseFile closeFile = new CloseFile(defaultTextArea,defaultFileExplorer);
        closeFileMenuItem.SetCommand(closeFile);
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

        DefaultMenuItem undoMenuItem= new DefaultMenuItem("Undo");
        undoMenuItem.SetIcon(new ImageIcon("assets/undo.png"));
        editMenu.AddMenuItem(undoMenuItem);
        DefaultMenuItem redoMenuItem= new DefaultMenuItem("Redo");
        redoMenuItem.SetIcon(new ImageIcon("assets/redo.png"));
        editMenu.AddMenuItem(redoMenuItem);
        editMenu.AddSeparator();

        DefaultMenuItem cutMenuItem= new DefaultMenuItem("Cut");
        Cut cut = new Cut(defaultTextArea);
        cutMenuItem.SetCommand(cut);
        cutMenuItem.SetIcon(new ImageIcon("assets/cut.png"));
        editMenu.AddMenuItem(cutMenuItem);

        DefaultMenuItem copyMenuItem= new DefaultMenuItem("Copy");
        Copy copy = new Copy(defaultTextArea);
        copyMenuItem.SetCommand(copy);
        copyMenuItem.SetIcon(new ImageIcon("assets/copy.png"));
        editMenu.AddMenuItem(copyMenuItem);

        DefaultMenuItem pasteMenuItem= new DefaultMenuItem("Paste");
        Paste paste = new Paste(defaultTextArea);
        pasteMenuItem.SetCommand(paste);
        pasteMenuItem.SetIcon(new ImageIcon("assets/paste.png"));
        editMenu.AddMenuItem(pasteMenuItem);

        DefaultMenuItem selectAllMenuItem= new DefaultMenuItem("Select All");
        SelectAll selectAll = new SelectAll(defaultTextArea);
        selectAllMenuItem.SetCommand(selectAll);
        editMenu.AddMenuItem(selectAllMenuItem);

        //MENU Search
        DefaultMenu searchMenu= new DefaultMenu("Search");
        this.iMenuBar.AddMenu(searchMenu);

        DefaultMenuItem findMenuItem = new DefaultMenuItem("Find");
        findMenuItem.SetIcon(new ImageIcon("assets/find.png"));
        searchMenu.AddMenuItem(findMenuItem);

        DefaultMenuItem replaceMenuItem = new DefaultMenuItem("Replace");
        replaceMenuItem.SetIcon(new ImageIcon("assets/replace.png"));
        searchMenu.AddMenuItem(replaceMenuItem);


        //Menu Compile
        DefaultMenu compileMenu= new DefaultMenu("Compile");
        this.iMenuBar.AddMenu(compileMenu);

        DefaultMenuItem runMenuItem= new DefaultMenuItem("Run");
        runMenuItem.SetIcon(new ImageIcon("assets/run.png"));
        compileMenu.AddMenuItem(runMenuItem);

        Compile compile = new Compile();
        DefaultMenuItem compileMenuItem= new DefaultMenuItem("Compile");
        compileMenuItem.SetIcon(new ImageIcon("assets/compile.png"));
        compileMenuItem.SetCommand(compile);
        compileMenu.AddMenuItem(compileMenuItem);

        //Region Toobar
        this.iToolBar = new DefaultToolBar();
        DefaultTool newTool = new DefaultTool("assets/new.png","New file");
        this.iToolBar.AddToolItem(newTool);
        DefaultTool openFileTool = new DefaultTool("assets/open-file.png","Open file");
        this.iToolBar.AddToolItem(openFileTool);
        DefaultTool openFolderTool = new DefaultTool("assets/open.png","Open folder");
        this.iToolBar.AddToolItem(openFolderTool);
        Container fileContainer = frame.getContentPane();
        fileContainer.add((Component) this.iToolBar, BorderLayout.WEST);
        DefaultTool saveTool = new DefaultTool("assets/save.png","save file");
        this.iToolBar.AddToolItem(saveTool);
        DefaultTool copyTool = new DefaultTool("assets/copy.png","Copy text");
        this.iToolBar.AddToolItem(copyTool);
        DefaultTool undoTool = new DefaultTool("assets/undo.png","Undo");
        this.iToolBar.AddToolItem(undoTool);
        DefaultTool redoTool = new DefaultTool("assets/redo.png","Redo");
        this.iToolBar.AddToolItem(redoTool);
        DefaultTool runTool = new DefaultTool("assets/run.png","Run");
        this.iToolBar.AddToolItem(runTool);

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
