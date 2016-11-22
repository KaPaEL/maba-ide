package Main;

import Commands.*;
import Commands.ShellCommand.Compile;
import Editor.DefaultTextArea;
import FileExplorer.DefaultFileExplorer;
import FileExplorer.FileExplorer;
import MenuBar.*;
import ToolBar.DefaultTool;
import ToolBar.DefaultToolBar;
import ToolBar.IToolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
    private static KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlaltS = KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK+ActionEvent.ALT_MASK);
    private static KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlX = KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlW = KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlR = KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlY = KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlA = KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK);
    private static KeyStroke ctrlZ = KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK);
    private static KeyStroke altF4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK);
    private static KeyStroke F9 = KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0);
    private static KeyStroke F10 = KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0);

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
        newMenuItem.SetAcceleration(ctrlN);
        newMenuItem.SetIcon(new ImageIcon("assets/new.png"));
        fileMenu.AddMenuItem(newMenuItem);

        DefaultMenuItem openFileMenuItem= new DefaultMenuItem("Open File");
        OpenFile openFile = new OpenFile(defaultTextArea,defaultFileExplorer);
        openFileMenuItem.SetCommand(openFile);
        openFileMenuItem.SetAcceleration(ctrlO);
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
        saveMenuItem.SetAcceleration(ctrlS);
        saveMenuItem.SetIcon(new ImageIcon("assets/save.png"));
        fileMenu.AddMenuItem(saveMenuItem);

        DefaultMenuItem saveAsMenuItem= new DefaultMenuItem("Save As");
        SaveAs saveAs = new SaveAs(defaultTextArea,defaultFileExplorer);
        saveAsMenuItem.SetCommand(saveAs);
        saveAsMenuItem.SetAcceleration(ctrlaltS);
        saveAsMenuItem.SetIcon(new ImageIcon("assets/save-as.png"));
        fileMenu.AddMenuItem(saveAsMenuItem);

        fileMenu.AddSeparator();

        DefaultMenuItem closeFileMenuItem= new DefaultMenuItem("Close File");
        CloseFile closeFile = new CloseFile(defaultTextArea,defaultFileExplorer);
        closeFileMenuItem.SetCommand(closeFile);
        closeFileMenuItem.SetAcceleration(ctrlW);
        closeFileMenuItem.SetIcon(new ImageIcon("assets/close-file.png"));
        fileMenu.AddMenuItem(closeFileMenuItem);

        DefaultMenuItem closeAllMenuFile= new DefaultMenuItem("Close All Files");
        CloseAllFile closeAllFile = new CloseAllFile(defaultTextArea,defaultFileExplorer);
        closeFileMenuItem.SetCommand(closeAllFile);
        closeAllMenuFile.SetIcon(new ImageIcon("assets/close-all.png"));
        fileMenu.AddMenuItem(closeAllMenuFile);


        fileMenu.AddSeparator();

        DefaultMenuItem exitMenuFile= new DefaultMenuItem("Exit");
        exitMenuFile.SetIcon(new ImageIcon("assets/close.png"));
        Exit exit = new Exit();
        exitMenuFile.SetCommand(exit);
        exitMenuFile.SetAcceleration(altF4);
        fileMenu.AddMenuItem(exitMenuFile);

        //MENU EDIT
        DefaultMenu editMenu= new DefaultMenu("Edit");
        this.iMenuBar.AddMenu(editMenu);

        DefaultMenuItem undoMenuItem= new DefaultMenuItem("Undo");
        undoMenuItem.SetIcon(new ImageIcon("assets/undo.png"));
//        newMenuItem.SetAcceleration(ctrlZ);
        editMenu.AddMenuItem(undoMenuItem);
        DefaultMenuItem redoMenuItem= new DefaultMenuItem("Redo");
        redoMenuItem.SetIcon(new ImageIcon("assets/redo.png"));
//        newMenuItem.SetAcceleration(ctrlY);
        editMenu.AddMenuItem(redoMenuItem);
        editMenu.AddSeparator();

        DefaultMenuItem cutMenuItem= new DefaultMenuItem("Cut");
        Cut cut = new Cut(defaultTextArea);
        cutMenuItem.SetCommand(cut);
        cutMenuItem.SetAcceleration(ctrlX);
        cutMenuItem.SetIcon(new ImageIcon("assets/cut.png"));
        editMenu.AddMenuItem(cutMenuItem);

        DefaultMenuItem copyMenuItem= new DefaultMenuItem("Copy");
        Copy copy = new Copy(defaultTextArea);
        copyMenuItem.SetCommand(copy);
        copyMenuItem.SetAcceleration(ctrlC);
        copyMenuItem.SetIcon(new ImageIcon("assets/copy.png"));
        editMenu.AddMenuItem(copyMenuItem);

        DefaultMenuItem pasteMenuItem= new DefaultMenuItem("Paste");
        Paste paste = new Paste(defaultTextArea);
        pasteMenuItem.SetCommand(paste);
        pasteMenuItem.SetAcceleration(ctrlV);
        pasteMenuItem.SetIcon(new ImageIcon("assets/paste.png"));
        editMenu.AddMenuItem(pasteMenuItem);

        DefaultMenuItem selectAllMenuItem= new DefaultMenuItem("Select All");
        SelectAll selectAll = new SelectAll(defaultTextArea);
        selectAllMenuItem.SetCommand(selectAll);
        selectAllMenuItem.SetAcceleration(ctrlA);
        selectAllMenuItem.SetIcon(new ImageIcon("assets/select-all.png"));
        editMenu.AddMenuItem(selectAllMenuItem);

        //MENU Search
        DefaultMenu searchMenu= new DefaultMenu("Search");
        this.iMenuBar.AddMenu(searchMenu);

        DefaultMenuItem findMenuItem = new DefaultMenuItem("Find");
        findMenuItem.SetIcon(new ImageIcon("assets/find.png"));
//        findMenuItem.SetAcceleration(ctrlF);
        searchMenu.AddMenuItem(findMenuItem);

        DefaultMenuItem replaceMenuItem = new DefaultMenuItem("Replace");
        replaceMenuItem.SetIcon(new ImageIcon("assets/replace.png"));
//        replaceMenuItem.SetAcceleration(ctrlR);
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
        DefaultTool newFileTool = new DefaultTool("assets/new.png","New file");
        newFileTool.SetCommand(newFile);
        this.iToolBar.AddToolItem(newFileTool);
        DefaultTool openFileTool = new DefaultTool("assets/open-file.png","Open file");
        openFileTool.SetCommand(openFile);
        this.iToolBar.AddToolItem(openFileTool);
        DefaultTool openFolderTool = new DefaultTool("assets/open.png","Open folder");
        openFolderTool.SetCommand(openFolder);
        this.iToolBar.AddToolItem(openFolderTool);
        DefaultTool saveTool = new DefaultTool("assets/save.png","Save file");
        saveTool.SetCommand(save);
        this.iToolBar.AddToolItem(saveTool);
        DefaultTool saveAsTool = new DefaultTool("assets/save-as.png","Save As file");
        saveAsTool.SetCommand(saveAs);
        this.iToolBar.AddToolItem(saveAsTool);
        DefaultTool closeFileTool = new DefaultTool("assets/close-file.png","Close file");
        closeFileTool.SetCommand(closeFile);
        this.iToolBar.AddToolItem(closeFileTool);
        DefaultTool closeAllFileTool = new DefaultTool("assets/close-all.png","Close All file");
        closeAllFileTool.SetCommand(closeAllFile);
        this.iToolBar.AddToolItem(closeAllFileTool);

        this.iToolBar.AddSeparator();
        DefaultTool undoTool = new DefaultTool("assets/undo.png","Undo");
        this.iToolBar.AddToolItem(undoTool);
        DefaultTool redoTool = new DefaultTool("assets/redo.png","Redo");
        this.iToolBar.AddToolItem(redoTool);
        DefaultTool cutTool = new DefaultTool("assets/cut.png","Cut text");
        cutTool.SetCommand(cut);
        this.iToolBar.AddToolItem(cutTool);
        DefaultTool copyTool = new DefaultTool("assets/copy.png","Copy text");
        copyTool.SetCommand(copy);
        this.iToolBar.AddToolItem(copyTool);
        DefaultTool pasteTool = new DefaultTool("assets/paste.png","Paste text");
        pasteTool.SetCommand(paste);
        this.iToolBar.AddToolItem(pasteTool);
        DefaultTool selectAllTool = new DefaultTool("assets/select-all.png","Select all text");
        selectAllTool.SetCommand(selectAll);
        this.iToolBar.AddToolItem(selectAllTool);

        this.iToolBar.AddSeparator();
        DefaultTool findTool = new DefaultTool("assets/find.png","Find text");
        this.iToolBar.AddToolItem(findTool);
        DefaultTool replaceTool = new DefaultTool("assets/replace.png","Replace text");
        this.iToolBar.AddToolItem(replaceTool);
        this.iToolBar.AddSeparator();
        DefaultTool runTool = new DefaultTool("assets/run.png","Run");
        this.iToolBar.AddToolItem(runTool);
        DefaultTool compileTool = new DefaultTool("assets/compile.png","Compile");
        this.iToolBar.AddToolItem(compileTool);
        Container container = frame.getContentPane();
        container.add((Component) this.iToolBar, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, westPanel,eastPanel);
        splitPane.setDividerLocation(148);
        //printout
        JTextPane terminalText = new JTextPane();
        terminalText.setPreferredSize(new Dimension(800,50));
        terminalText.setEnabled(false);
        JScrollPane terminalPanel = new JScrollPane(terminalText);

        //find panel
        JTextField textField = new JTextField(20);
        JButton textFind = new JButton("Find");
        JPanel findPanel = new JPanel();
        findPanel.add(textField);
        findPanel.add(textFind);
        findPanel.setVisible(true);
        JSplitPane southSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,terminalPanel,findPanel);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,splitPane,southSplit);
        contentPanel.add(mainSplit, BorderLayout.CENTER);
        JLabel statusBar = new JLabel("Staus:");

        contentPanel.add(statusBar, BorderLayout.SOUTH);


        //contentPanel.add(find, BorderLayout.SOUTH);
        setContentPane(contentPanel);
        frame.add(contentPanel);
        frame.setVisible(true);

    }


    public static void main(String args[]) throws IOException {
        MainWindow mainWindow = new MainWindow();
    }
}
